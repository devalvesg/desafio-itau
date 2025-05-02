package com.devalvesg.desafio_itau.infrastructure.api;

import com.devalvesg.desafio_itau.infrastructure.api.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            logger.error(String.format("Validation error - Field: %s, Message: %s", error.getField(), error.getDefaultMessage()));
        });

        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        logger.warn(String.format("Business error - Message: %s, StrackTrace: %s", ex.getMessage(), Arrays.toString(ex.getStackTrace())));

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex) {
        logger.error(String.format("Unexpected error - Message: %s, StrackTrace: %s", ex.getMessage(), Arrays.toString(ex.getStackTrace())));

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
