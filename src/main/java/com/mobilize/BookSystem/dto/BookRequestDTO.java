package com.mobilize.BookSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDTO {

	@NotBlank
	@Size(max = 255)
	private String title;

	@NotBlank
	@Size(max = 100)
	private String author;

	@NotNull
	private int publicationDate;

	@NotNull
	@DecimalMin(value = "50.0", inclusive = true)
	private double price;

	@Pattern(regexp = "^(\\d{10}|\\d{13})$") // ISBN-10 or ISBN-13 format
	private String isbn;
}
