package br.gov.sp.cptm.bykerack.infra;

import br.gov.sp.cptm.bykerack.util.exception.LoadKeyStoreException;
import br.gov.sp.cptm.bykerack.util.exception.RetrievePrivateKeyEntryException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

@Configuration
public class SingletonTokenBean {

    private static final String PKCS_12 = "pkcs12";

    @Value("${cptm.jwk.jks-file}")
    String jksFile;
    @Value("${cptm.jwk.password.key-store}")
    String keyStorePass;
    @Value("${cptm.jwk.password.key-pass}")
    String keyPass;
    @Value("${cptm.jwk.alias}")
    String alias;

    @Bean
    public KeyStore.PrivateKeyEntry getPrivateKeyEntry() {
        try {
            return (KeyStore.PrivateKeyEntry) getKeyStore().getEntry(
                    alias, new KeyStore.PasswordProtection(keyStorePass.toCharArray())
            );
        } catch (NoSuchAlgorithmException | UnrecoverableEntryException | KeyStoreException e) {
            throw new RetrievePrivateKeyEntryException(e);
        }
    }

    private KeyStore getKeyStore() {
        try (var jksInputStream = new FileInputStream(jksFile)) {
            var keyStore = KeyStore.getInstance(PKCS_12);
            keyStore.load(jksInputStream, keyStorePass.toCharArray());
            return keyStore;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new LoadKeyStoreException(e);
        }
    }
}
