package com.alfa.customer_storage.advice;

import com.alfa.customer_storage.Exception.DataException;
import com.alfa.customer_storage.Exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DataException.class)
    public ResponseEntity storageHandler(DataException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity storageHandler(RequestException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
