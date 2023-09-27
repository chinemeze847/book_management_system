package com.mobilize.BookSystem.service;

import java.util.List;
import java.util.stream.Collectors;



import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.dto.BookUpdateDTO;
import com.mobilize.BookSystem.exception.BookNotFoundException;
import com.mobilize.BookSystem.exception.BookValidationException;
import com.mobilize.BookSystem.exception.InvalidSearchParametersException;
import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.repository.BookRepository;

/**
 * The service class that handles the businees logic
 */
@Service
@Slf4j
class BookServiceImpl implements BookService {

	BookRepository bookRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * Handles the creation of books
	 * @param bookRequest the request object that contains the book to create
	 * @return it returns the created book
	 */
	@Override
	public Object createBook(BookRequestDTO bookRequest) {

		Book book = Book.builder()
				.author(bookRequest.getAuthor())
				.price(bookRequest.getPrice())
				.title(bookRequest.getTitle())
				.isbn(bookRequest.getIsbn())
				.publicationYear(bookRequest.getPublicationYear())
				.build();
		log.info("Saving book to database");

		//returns the created book
		return bookRepository.save(book);

	}

	/**
	 * Retrieves all the books
	 * @param pageable object that contains the page size and page number
	 * @return the books with page size and page number constraints
	 */
	@Override
	public Page<Book> getAllBooks(Pageable pageable)
	{
		try
		{
			//retrieves all books and sends to controller method
			return bookRepository.findAll(pageable);
		}catch (InvalidDataAccessApiUsageException ex)
		{
			//throws an exception if the parameters are invalid
			throw new InvalidSearchParametersException("Invalid search parameters: " + ex.getMessage());
		}
	}

	/**
	 * Retrieves a book with specified id and throws a
	 * bookNotFoundException if the book is not found
	 * @param id is the id of the book to be retrieved
	 * @return the book if the id is found
	 */
	public Book getBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
	}

	/**
	 * It allows us to search for a book given the title or author name or both
	 * and throws an exception if the search parameters are invalid
	 * @param title of the book to search
	 * @param author ot the book
	 * @return the books that match the title or author
	 */
	@Override
	public List<Book> searchBooks(String title, String author) {
		try {
			if (title != null && author != null) {

				//if both title and authors are provided
				return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
			} else if (title != null) {

				//if title is provided
				return bookRepository.findByTitleContainingIgnoreCase(title);
			} else if (author != null) {

				//if author is provided it returns this
				return bookRepository.findByAuthorContainingIgnoreCase(author);
			} else {
				// If neither title nor author is provided, you can choose to return all books
				return bookRepository.findAll();
			}
		} catch (InvalidDataAccessApiUsageException ex) {
			//throws invalid search parameter if wrong parameters are provided
			throw new InvalidSearchParametersException("Invalid search parameters: " + ex.getMessage());
		}
	}

	/**
	 * It updates the
	 * @param bookId is the id of the book to search
	 * @param updatedBook the request object to update the book
	 * @return the updated book
	 */
	@Override
	public Book updateBook(Long bookId, BookUpdateDTO updatedBook) {
		if (!bookRepository.existsById(bookId)) {
			//throws book not found exception if the book with specific id is not found
			throw new BookNotFoundException("Book not found with ID: " + bookId);
		}

		//set the id of the updated book
		updatedBook.setId(bookId);

		//Build the book object to be updated
		Book book = Book.builder()
				.id(bookId)
				.author(updatedBook.getAuthor())
				.price(updatedBook.getPrice())
				.title(updatedBook.getTitle())
				.isbn(updatedBook.getIsbn())
				.publicationYear(updatedBook.getPublicationYear())
				.build();
		log.info("updating book with id : {}", bookId);

		//returns saved book
		return bookRepository.save(book);
	}

	/**
	 * Deletes book with specific id and throws
	 * book not found exception if the book is not found
	 * @param bookId the id of the book to be deleted
	 */
	@Override
	public void deleteBook(Long bookId) {
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException("Book not found with ID: " + bookId);
		}
		log.info("Deleting book with id : {}", bookId);
		bookRepository.deleteById(bookId);
	}

	/**
	 * Validates a book request DTO.
	 *
	 * @param bindingResult  the result of the validation
	 */
	public void validateBook( BindingResult bindingResult) {

		// Check if validation has failed
		if (bindingResult.hasErrors()) {
			// Handle validation errors

			// Extract error messages from the BindingResult
			List<String> errorMessages = bindingResult.getFieldErrors()
					.stream()
					.map(error -> error.getDefaultMessage())
					.collect(Collectors.toList());

			// Throw a custom BookValidationException with the error messages
			throw new BookValidationException(errorMessages);
		}
	}
}
