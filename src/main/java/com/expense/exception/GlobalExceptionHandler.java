package com.expense.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Map<String, String>> getInvalidInput(InvalidInputException ex){
		
		return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("error", ex.getMessage()));
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String,String>> httpMessageNotReadableException(HttpMessageNotReadableException ex){
		String errorMessage=ex.getMessage();
		if(ex.getMessage().contains("JSON parse error") && ex.getMessage().contains("com.expense.entity.Category")) {
			errorMessage = "Please enter valid values for Category, Valid values are OTHER, TRANSPORT, UTILITIES, FOOD, ENTERTAINMENT";
		}
		return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("error", errorMessage));
	}
}
