package br.gov.sp.cptm.bykerack.util.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not Found");
    }
}
