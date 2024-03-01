package br.gov.sp.cptm.bykerack.web.advice;

import br.gov.sp.cptm.bykerack.util.exception.DocTypeNotFoundException;
import br.gov.sp.cptm.bykerack.util.exception.EMailAlreadyRegisteredException;
import br.gov.sp.cptm.bykerack.util.exception.UserNotFoundException;
import br.gov.sp.cptm.bykerack.web.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({
            EMailAlreadyRegisteredException.class,
            UserNotFoundException.class,
            DocTypeNotFoundException.class
    })
    public ResponseEntity<ErrorMessage> handleClientException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
