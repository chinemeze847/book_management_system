package com.mobilize.BookSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating that a book was not found.
 * This exception is mapped to HTTP status code NOT_FOUND (404).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new BookNotFoundException with the specified detail message.
	 *
	 * @param message The detail message describing the exception.
	 */
	public BookNotFoundException(String message) {
		super(message);
	}
}
