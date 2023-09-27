package com.mobilize.BookSystem.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for indicating validation errors related to book data.
 * This exception is mapped to HTTP status code BAD_REQUEST (400).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private List<String> errorMessages;

	/**
	 * Constructs a new BookValidationException with the specified detail message.
	 *
	 * @param errorMessages The detail message describing the validation error.
	 */
	public BookValidationException(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}


	public List<String> getErrorMessages() {
		return errorMessages;
	}
}
