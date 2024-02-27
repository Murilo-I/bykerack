package br.gov.sp.cptm.bykerack.util.exception;

public class EMailAlreadyRegisteredException extends RuntimeException {

    public EMailAlreadyRegisteredException() {
        super("e-mail já cadastrado");
    }
}
