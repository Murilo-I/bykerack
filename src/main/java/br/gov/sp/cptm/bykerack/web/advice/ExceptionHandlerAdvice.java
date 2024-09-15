package br.gov.sp.cptm.bykerack.web.advice;

import br.gov.sp.cptm.bykerack.util.exception.*;
import br.gov.sp.cptm.bykerack.web.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({
            UserNotFoundException.class,
            DocTypeNotFoundException.class,
            BikeRackNotFoundException.class,
            NoVacanciesAvailableException.class,
            EMailAlreadyRegisteredException.class
    })
    public ResponseEntity<ErrorMessage> handleClientException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
