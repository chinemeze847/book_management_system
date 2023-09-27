package com.mobilize.BookSystem.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents the response object for successful operations.
 * This class encapsulates the success response, including a status code, timestamp, and an optional message.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {

	/**
	 * The HTTP status code indicating the success of the operation.
	 */
	private int statusCode;

	/**
	 * The timestamp indicating when the operation was successful.
	 */
	private Date timestamp;

	/**
	 * An optional message providing additional information about the success.
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message;
}