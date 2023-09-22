package com.mobilize.BookSystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	private LocalDate publicationYear;

	@Column(name = "isbn", unique = true, nullable = false)
	private String isbn;

	@Column(name = "price")
	private BigDecimal price;

}