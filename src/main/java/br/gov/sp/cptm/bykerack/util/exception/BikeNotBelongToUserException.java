package br.gov.sp.cptm.bykerack.util.exception;

public class BikeNotBelongToUserException extends RuntimeException {

    public BikeNotBelongToUserException() {
        super("This bicycleId does not belong to this user.");
    }
}
