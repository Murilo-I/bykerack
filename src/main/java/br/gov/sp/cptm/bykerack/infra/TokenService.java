package br.gov.sp.cptm.bykerack.infra;

import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.web.dto.TokenDTO;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.security.PublicKey;
import java.util.Date;

@Service
public class TokenService {

    private static final String ISSUER = "BYKERACK-CPTM";
    public static final String BEARER = "Bearer";

    @Value("${cptm.jwt.expiration}")
    String expiration;
    @Autowired
    KeyStore.PrivateKeyEntry keyEntry;

    public TokenDTO generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + Long.parseLong(expiration));

        String token = Jwts.builder().issuer(ISSUER)
                .subject(user.getUserId().toString())
                .issuedAt(issuedDate).expiration(expirationDate)
                .signWith(keyEntry.getPrivateKey(), Jwts.SIG.RS512)
                .compact();

        return new TokenDTO(token, BEARER, user.getUserId(), issuedDate, expirationDate);
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().verifyWith(getPublicKey()).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getSubject(String token) {
        var subject = Jwts.parser().verifyWith(getPublicKey()).build().parseSignedClaims(token)
                .getPayload().getSubject();
        return Long.parseLong(subject);
    }

    private PublicKey getPublicKey() {
        return keyEntry.getCertificate().getPublicKey();
    }
}
