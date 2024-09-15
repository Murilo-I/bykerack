package br.gov.sp.cptm.bykerack.util.exception;

public class BikeRackNotFoundException extends RuntimeException {

    public BikeRackNotFoundException() {
        super("BikeRack not Found");
    }
}
