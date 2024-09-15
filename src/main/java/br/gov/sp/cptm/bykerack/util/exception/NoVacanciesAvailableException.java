package br.gov.sp.cptm.bykerack.util.exception;

public class NoVacanciesAvailableException extends RuntimeException {

    public NoVacanciesAvailableException() {
        super("No Vacancies Available");
    }
}
