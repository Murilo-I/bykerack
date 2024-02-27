package br.gov.sp.cptm.bykerack.util.exception;

public class DocTypeNotFoundException extends RuntimeException {

    public DocTypeNotFoundException() {
        super("Document Type not Found");
    }
}
