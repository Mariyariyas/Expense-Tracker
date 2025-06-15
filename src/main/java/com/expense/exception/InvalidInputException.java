package com.expense.exception;

public class InvalidInputException extends RuntimeException{

	public InvalidInputException (String message) {
		super(message);
	}

	public InvalidInputException(String message, Long id) {
		// TODO Auto-generated constructor stub
		super(message+""+id);
	}
}
