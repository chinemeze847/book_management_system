package com.mobilize.BookSystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;


import lombok.*;


/**
 * Represents a book entity in the system.
 */
@Entity
@Table(name = "books")
@Builder
@Data
public class Book {
	/**
	 * Unique identifier for the book.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * The title of the book. Cannot be null.
	 */
	@Column(name = "title", nullable = false)
	private String title;

	/**
	 * The author of the book. Cannot be null.
	 */
	@Column(name = "author", nullable = false)
	private String author;

	/**
	 * The publication year of the book.
	 */
	@Column(name = "publication_year")
	private int publicationYear;

	/**
	 * The ISBN (International Standard Book Number) of the book. Must be unique and cannot be null.
	 */
	@Column(name = "isbn", unique = true, nullable = false)
	private String isbn;

	/**
	 * The price of the book.
	 */
	@Column(name = "price")
	private double price;

	/**
	 * Default constructor for the Book class.
	 */
	public Book() {
	}

	/**
	 * Constructor to create a Book object with provided attributes.
	 *
	 * @param title           The title of the book.
	 * @param author          The author of the book.
	 * @param publicationYear The publication year of the book.
	 * @param isbn            The ISBN of the book.
	 * @param price           The price of the book.
	 */
	public Book(String title, String author, int publicationYear, String isbn, double price) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
	}

	/**
	 * Constructor to create a Book object with provided attributes.
	 *
	 * @param id              The unique identifier for the book.
	 * @param title           The title of the book.
	 * @param author          The author of the book.
	 * @param publicationYear The publication year of the book.
	 * @param isbn            The ISBN of the book.
	 * @param price           The price of the book.
	 */
	public Book(Long id, String title, String author, int publicationYear, String isbn, double price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
	}
}


