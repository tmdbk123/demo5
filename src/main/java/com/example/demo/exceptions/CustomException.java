package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<String>> handleBindException(BindException e) {
        List<String> list = new ArrayList<>();
        for(ObjectError error : e.getBindingResult().getAllErrors()){
            list.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

}
