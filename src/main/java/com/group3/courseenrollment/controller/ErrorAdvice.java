package com.group3.courseenrollment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@link ErrorAdvice} Controller to handle any 500 errors
 */
@Slf4j
@ControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exception(final Throwable throwable){
       // log.info("Exception during execution of application",throwable);
        return ResponseEntity.badRequest().body("Unable to process request at this time");
    }
}
