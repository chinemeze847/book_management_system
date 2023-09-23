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

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {

	BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping()
	public ResponseEntity<?> createBook(@Valid @RequestBody BookRequestDTO bookRequest){
		return new ResponseEntity<>(bookService.createBook(bookRequest), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<Page<Book>> getAllBooks(
			@RequestParam(defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(defaultValue = "0", required = false) Integer page)
	{
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Book> books = bookService.getAllBooks(pageable);

		return ResponseEntity.ok(books);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
		Book book = bookService.getBookById(bookId);
		return ResponseEntity.ok(book);
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Long bookId,@Valid @RequestBody BookUpdateDTO bookUpdate){
		Book book = bookService.updateBook(bookId,bookUpdate);
		return ResponseEntity.ok(book);
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Book> deleteBook(@PathVariable Long bookId){
		bookService.deleteBook(bookId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String author
	) {
		List<Book> matchingBooks = bookService.searchBooks(title, author);
		if (matchingBooks.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(matchingBooks);
	}
}
