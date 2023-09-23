package com.mobilize.BookSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data Transfer Object (DTO) representing a request to update a book.
 */
@Data
@NoArgsConstructor
@Builder
public class BookUpdateDTO {

	private Long id;

	@NotBlank
	@Size(max = 255)
	private String title;

	@NotBlank
	@Size(max = 100)
	private String author;

	@NotNull
	private int publicationYear;

	@NotNull
	@DecimalMin(value = "50.0", inclusive = true)
	private double price;

	@Pattern(regexp = "^(\\d{10}|\\d{13})$") // ISBN-10 or ISBN-13 format
	private String isbn;

	public BookUpdateDTO(String title, String author, int publicationYear, double price, String isbn) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.price = price;
		this.isbn = isbn;
	}

	public BookUpdateDTO(Long id, String title, String author, int publicationYear, double price, String isbn) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.price = price;
		this.isbn = isbn;
	}
}
