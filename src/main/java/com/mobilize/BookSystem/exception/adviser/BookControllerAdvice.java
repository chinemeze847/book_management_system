package com.mobilize.BookSystem.exception.adviser;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.mobilize.BookSystem.exception.*;

/**
 * Controller advice class to handle exceptions globally for the BookController.
 */
@ControllerAdvice
public class BookControllerAdvice {

	/**
	 * Exception handler for BookNotFoundException.
	 * @param ex The BookNotFoundException instance.
	 * @param request The WebRequest.
	 * @return ResponseEntity containing error details and HTTP status NOT_FOUND.
	 */
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorMessage> bookNotFoundException(BookNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				new Date(),
				"Book Not Found",
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	/**
	 * Exception handler for BookValidationException.
	 * @param ex The BookValidationException instance.
	 * @param request The WebRequest.
	 * @return ResponseEntity containing error details and HTTP status BAD_REQUEST.
	 */
	@ExceptionHandler(BookValidationException.class)
	public ResponseEntity<ErrorMessage> bookValidationException(BookValidationException ex, WebRequest request) {

		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				ex.getErrorMessages(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handler for InvalidSearchParametersException.
	 * @param ex The InvalidSearchParametersException instance.
	 * @param request The WebRequest.
	 * @return ResponseEntity containing error details and HTTP status BAD_REQUEST.
	 */
	@ExceptionHandler(InvalidSearchParametersException.class)
	public ResponseEntity<ErrorMessage> invalidSearchParametersException(InvalidSearchParametersException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				"Invalid Search Parameters",
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handler for handling the InvalidYearException.
	 *
	 * @param ex The InvalidYearException instance.
	 * @return A ResponseEntity containing the error message and HTTP status BAD_REQUEST.
	 */
	@ExceptionHandler(InvalidYearException.class)
	public ResponseEntity<String> handleInvalidYearException(InvalidYearException ex) {
		// Message to be sent
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handler for generic exceptions.
	 * @param ex The Exception instance.
	 * @param request The WebRequest.
	 * @return ResponseEntity containing error details and HTTP status INTERNAL_SERVER_ERROR.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new Date(),
				"Internal server error",
				request.getDescription(false));

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
