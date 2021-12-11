package com.edwardjones.reminder.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Log log = LogFactory.getLog(GlobalExceptionHandler.class);
	
	/*
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(com.twilio.exception.ApiException.class)
    public void handleConflict() {
         log.info("Phone number not found.");
         
    }
    */
}
