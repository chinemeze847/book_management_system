package com.mobilize.BookSystem.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mobilize.BookSystem.model.Book;

/**
 * Represents the response object for book search operations.
 * This class encapsulates the search results, including a message and a list of matching books.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchResponse {

	/**
	 * A message describing the result of the search.
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message;

	/**
	 * A list of books that match the search criteria.
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<Book> matchingBooks;
}
