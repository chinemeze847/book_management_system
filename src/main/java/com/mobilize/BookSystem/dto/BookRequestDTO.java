package com.mobilize.BookSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.mobilize.BookSystem.validation.YearConstraint;

/**
 * Data Transfer Object (DTO) representing a request to create a new book.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDTO {

	/*
	Describe The title of the book
	It should not be empty nor null
	 */
	@NotBlank(message = "Title should not be empty")
	@NotNull(message = "Title should not be a null value")
	private String title;

	/*
	Describe The author of the book
	It should not be empty nor null
	 */
	@NotBlank(message = "Author should not be empty")
	@NotNull(message = "Author should not be null")
	private String author;

	/*
	Describe the year in which the book was published
	It should be a valid year
	 */
	@YearConstraint(message = "publication year is not valid")
	private Integer publicationYear;

	/*
	Describes the price of a book
	which should not be below 10$
	 */
	@NotNull
	@DecimalMin(value = "10.0", inclusive = true, message = "price should be above 50$")
	private double price;

	/*
	Describes the Isbn of the book
	which should adhere to the pattern described and
	should not be null nor blank
	 */
	@Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "Isbn number is not valid")
	@NotNull(message = "Isbn should not be null")
	@NotBlank(message = "Isbn should not be empty")
	// ISBN-10 or ISBN-13 format (regular expression)
	private String isbn;
}
