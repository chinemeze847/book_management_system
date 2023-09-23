package com.mobilize.BookSystem.exception;

import java.util.Date;

/**
 * Represents an error message to be returned as part of a response in case of exceptions.
 */
public class ErrorMessage {
	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

	/**
	 * Constructs a new ErrorMessage with the provided details.
	 *
	 * @param statusCode  The HTTP status code associated with the error.
	 * @param timestamp   The timestamp indicating when the error occurred.
	 * @param message     A description of the error message.
	 * @param description Additional information or details about the error.
	 */
	public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

	/**
	 * Get the HTTP status code associated with the error.
	 *
	 * @return The HTTP status code.
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Get the timestamp indicating when the error occurred.
	 *
	 * @return The timestamp.
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Get the description of the error message.
	 *
	 * @return The error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Get additional information or details about the error.
	 *
	 * @return The error description.
	 */
	public String getDescription() {
		return description;
	}
}