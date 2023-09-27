package com.mobilize.BookSystem.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.dto.BookUpdateDTO;
import com.mobilize.BookSystem.model.Book;

/**
 * Service interface for managing book-related operations.
 */
public interface BookService {

	/**
	 * Create a new book.
	 *
	 * @param bookRequest The data for the new book.
	 * @return The created book.
	 */
	Object createBook(BookRequestDTO bookRequest);

	/**
	 * Retrieve a paginated list of all books.
	 *
	 * @param pageable Pagination information.
	 * @return A page of books.
	 */
	Page<Book> getAllBooks(Pageable pageable);

	/**
	 * Update an existing book.
	 *
	 * @param id          The ID of the book to update.
	 * @param updatedBook The updated book data.
	 * @return The updated book.
	 */
	Book updateBook(Long id, BookUpdateDTO updatedBook);

	/**
	 * Delete a book by its ID.
	 *
	 * @param bookId The ID of the book to delete.
	 */
	void deleteBook(Long bookId);

	/**
	 * Retrieve a book by its ID.
	 *
	 * @param id The ID of the book to retrieve.
	 * @return The book with the specified ID.
	 */
	Book getBookById(Long id);

	/**
	 * Search for books by title and/or author, ignoring case.
	 *
	 * @param title  The title to search for (can be null).
	 * @param author The author to search for (can be null).
	 * @return A list of books matching the search criteria.
	 */
	List<Book> searchBooks(String title, String author);

	/**
	 * Validates a book based on the binding result.
	 *
	 * @param bindingResult The result of the validation.
	 *                      It contains information about validation errors, if any.
	 */
	void validateBook(BindingResult bindingResult);
}
