package com.kapsch.demo.com.kapsch.exception;

public class CinemaNotFoundException extends RuntimeException {

    CinemaNotFoundException(Long id) {
        super("Could not find cinema with id" + id);
    }
}
