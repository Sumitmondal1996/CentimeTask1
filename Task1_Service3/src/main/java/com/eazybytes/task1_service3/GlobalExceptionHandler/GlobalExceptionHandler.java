package com.eazybytes.task1_service3.GlobalExceptionHandler;

import com.eazybytes.task1_service3.Exceptions.JsonParsingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonParsingException.class)

    public ResponseEntity<String>  handleJsonParsingException(JsonParsingException jsonParsingException) {
        System.out.println("Result  " + jsonParsingException.getAttr()==null?1:0);
        String message = ( jsonParsingException.getAttr() + " value can't be null");
        System.out.println(message);
        return ResponseEntity.badRequest().body(message);

    }
}
