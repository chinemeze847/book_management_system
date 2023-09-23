package com.mobilize.BookSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating validation errors related to book data.
 * This exception is mapped to HTTP status code BAD_REQUEST (400).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new BookValidationException with the specified detail message.
	 *
	 * @param message The detail message describing the validation error.
	 */
	public BookValidationException(String message) {
		super(message);
	}
}
