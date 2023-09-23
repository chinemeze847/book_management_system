package com.mobilize.BookSystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Builder
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "publication_year")
	private int publicationYear;

	@Column(name = "isbn", unique = true, nullable = false)
	private String isbn;

	@Column(name = "price")
	private double price;

	public Book(String title, String author, int publicationYear, String isbn, double price) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
	}

	public Book(Long id, String title, String author, int publicationYear, String isbn, double price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
	}
}