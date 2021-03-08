package com.herokuapp.remitlytask.ErrorHandlers;

import com.herokuapp.remitlytask.Exceptions.SencondaryApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
@RestController
public class RTResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SencondaryApiException.class)
    public final ResponseEntity<RTErrorDetails> handleSecondaryApiException(SencondaryApiException ex, WebRequest request) {
        RTErrorDetails RTErrorDetails = new RTErrorDetails(new Date(), "Exception occurred due to error in secondary api.",
                request.getDescription(false));
        return new ResponseEntity<>(RTErrorDetails, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<RTErrorDetails> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        RTErrorDetails RTErrorDetails = new RTErrorDetails(new Date(),"Wrong format of GBP input value.",
                request.getDescription(false));
        return new ResponseEntity<>(RTErrorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}