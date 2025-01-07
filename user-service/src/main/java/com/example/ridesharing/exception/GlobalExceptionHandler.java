package com.example.ridesharing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("An unexpected error occurred: " + e.getMessage());
	    }
	 
	  @ExceptionHandler(MissingServletRequestParameterException.class)
	    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
	        String paramName = ex.getParameterName();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Required parameter '" + paramName + "' is missing");
	    }
}
