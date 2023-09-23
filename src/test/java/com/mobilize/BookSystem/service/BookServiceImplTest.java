package com.mobilize.BookSystem.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepository bookRepository;

	private List<Book> expectedBooks;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		// Create test data
		String title = "The king of Ishtar";
		String author = "Jerry King";
		expectedBooks = new ArrayList<>();
		expectedBooks.add(new Book(1L,title, author,2024, "9898765456", 29.99));
	}


	@Test
	void shouldCreateBook() {
		// Request to create book
		BookRequestDTO bookRequest = new BookRequestDTO();
		bookRequest.setAuthor("james");
		bookRequest.setPrice(300.03);
		bookRequest.setTitle("Journey to France");
		bookRequest.setIsbn("9897456434");
		bookRequest.setPublicationYear(2011);

		//what is actually saved on the repository
		Book createdBook = new Book();
		createdBook.setId(1L);
		createdBook.setAuthor("james");
		createdBook.setPrice(300.03);
		createdBook.setTitle("Journey to France");
		createdBook.setIsbn("9897456434");
		createdBook.setPublicationYear(2011);

		//what the repository should return when the save method is called
		when(bookRepository.save(any(Book.class))).thenReturn(createdBook);

		//The service method to create the book
		Object result = bookService.createBook(bookRequest);

		// Verify that the repository's save method was called with the correct book object
		verify(bookRepository, times(1)).save(any(Book.class));

		// Verify that the result is the created book
		assertEquals(createdBook, result);

	}

	@Test
	void shouldGetAllBooks() {
	}

	@Test
	void ShouldGetBookById() {
	}

	@Test
	void ShouldUpdateBook() {
	}

	@Test
	void ShouldDeleteBook() {
	}

	@Test
	public void shouldSearchBooksByAuthor() {

		String author = "Jerry King";
		// Mock the repository method to return the expected books
		when(bookRepository.findByAuthorContainingIgnoreCase(author))
				.thenReturn(expectedBooks);

		// Call the service method
		List<Book> result = bookService.searchBooks(null, author);

		// Assert the result
		Assert.assertEquals(expectedBooks, result);
	}

	@Test
	public void shouldSearchBooksByTitle() {
		// Create test data
		String title = "The king of Ishtar";

		// Mock the repository method to return the expected books
		when(bookRepository.findByTitleContainingIgnoreCase(title))
				.thenReturn(expectedBooks);

		// Call the service method
		List<Book> result = bookService.searchBooks(title, null);

		// Assert the result
		Assert.assertEquals(expectedBooks, result);
	}
}