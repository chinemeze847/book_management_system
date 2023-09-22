package com.mobilize.BookSystem.exception;

public class InvalidSearchParametersException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public InvalidSearchParametersException(String message)
	{
		super(message);
	}
}
