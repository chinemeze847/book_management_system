package com.mobilize.BookSystem.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.dto.BookUpdateDTO;
import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.service.BookService;

/**
 * The Book Controller that intercepts every request from the user
 */
@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {

	BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * This method creates a book upon request from the user
	 * @param bookRequest this is the request object from the user
	 * @return A book json object is returned
	 */
	@PostMapping()
	public ResponseEntity<?> createBook(@Valid @RequestBody BookRequestDTO bookRequest){
		return new ResponseEntity<>(bookService.createBook(bookRequest), HttpStatus.CREATED);
	}

	/**
	 * Returns all books
	 * @param pageSize is the number of books per page to be returned
	 * @param page the page number that is being returned
	 * @return all books within the range of 0 to 5 per page
	 */
	@GetMapping()
	public ResponseEntity<Page<Book>> getAllBooks(
			@RequestParam(defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(defaultValue = "0", required = false) Integer page)
	{
		//creates an object of Pageable
		Pageable pageable = PageRequest.of(page, pageSize);

		//All books within the page number and page size are returned
		Page<Book> books = bookService.getAllBooks(pageable);

		return ResponseEntity.ok(books);
	}

	/**
	 * It gets a book as specified by the book id passed in the path parameter
	 * @param bookId the id of the book to be retrieved
	 * @return the book with specific book
	 */
	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {

		//call the service method that finds the book by Id
		Book book = bookService.getBookById(bookId);

		//returns the book that matches the id
		return ResponseEntity.ok(book);
	}

	/**
	 * updates a book using the id of the book
	 * @param bookId is the id of the book to be updated
	 * @param bookUpdate the request object to be used to update the book
	 * @return the updated book as json
	 */
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Long bookId,@Valid @RequestBody BookUpdateDTO bookUpdate){

		//Gets the updated book
		Book book = bookService.updateBook(bookId,bookUpdate);

		//returns the json object of the updated book
		return ResponseEntity.ok(book);
	}

	/**
	 * Deletes a book with specified id
	 * @param bookId the id of the book to be deleted
	 * @return  no content status code
	 */
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Book> deleteBook(@PathVariable Long bookId){

		//call the deleteBook method on the service to delete a book
		bookService.deleteBook(bookId);

		//returns 204 status code
		return ResponseEntity.noContent().build();
	}

	/**
	 * searches for a book given the title and author name
	 * @param title The title of the name to be retrieved
	 * @param author The author of the book to be retrieved
	 * @return returns all matching books as json
	 */
	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author
	) {
		List<Book> matchingBooks = bookService.searchBooks(title, author);

		//checks if the matchingBooks variable is empty
		if (matchingBooks.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		//returns all matching books are
		return ResponseEntity.ok(matchingBooks);
	}
}
