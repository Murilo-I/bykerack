package br.gov.sp.cptm.bykerack.util.exception;

public class BicycleNotFoundException extends RuntimeException {

    public BicycleNotFoundException() {
        super("Bicycle not Found");
    }
}
