package com.mobilize.BookSystem.dto;


import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.mobilize.BookSystem.validation.YearConstraint;

/**
 * Data Transfer Object (DTO) representing a request to update a book.
 */
@Data
@NoArgsConstructor
@Builder
public class BookUpdateDTO {

	private Long id;

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
	@NotNull(message = "publication year should not be null")
	private Integer publicationYear;

	/*
	Describes the price of a book
	which should not be below 10$
	 */
	@NotNull
	@DecimalMin(value = "10.0", inclusive = true, message = "price should be above 10$")
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

	/**
	 * Constructs a new BookUpdateDTO with the provided book update information.
	 *
	 * @param title           The updated title of the book.
	 * @param author          The updated author of the book.
	 * @param publicationYear The updated publication year of the book.
	 * @param price           The updated price of the book.
	 * @param isbn            The updated ISBN of the book.
	 */
	public BookUpdateDTO(String title, String author, int publicationYear, double price, String isbn) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.price = price;
		this.isbn = isbn;
	}

	/**
	 * Constructs a new BookUpdateDTO with the provided book update information including the book's ID.
	 *
	 * @param id              The ID of the book to update.
	 * @param title           The updated title of the book.
	 * @param author          The updated author of the book.
	 * @param publicationYear The updated publication year of the book.
	 * @param price           The updated price of the book.
	 * @param isbn            The updated ISBN of the book.
	 */

	public BookUpdateDTO(Long id, String title, String author, int publicationYear, double price, String isbn) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.price = price;
		this.isbn = isbn;
	}
}
