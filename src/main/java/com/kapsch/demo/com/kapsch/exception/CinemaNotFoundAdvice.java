package com.kapsch.demo.com.kapsch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CinemaNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CinemaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cinemaNotFoundHandler(CinemaNotFoundException ex) {
        return ex.getMessage();
    }
}
