package com.mobilize.BookSystem.exception;

/**
 * An exception to indicate that invalid search parameters were provided.
 * This exception is typically thrown when an attempt to search for books
 * using invalid or unsupported search parameters is made.
 */
public class InvalidSearchParametersException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new InvalidSearchParametersException with the provided message.
	 *
	 * @param message A description of the error message.
	 */
	public InvalidSearchParametersException(String message) {
		super(message);
	}
}