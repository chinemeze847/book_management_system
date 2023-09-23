package com.mobilize.BookSystem.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepository bookRepository;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldCreateBook() {
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
		// Create test data
		String title = "The king of Ishtar";
		String author = "Jerry King";
		List<Book> expectedBooks = new ArrayList<>();
		expectedBooks.add(new Book(1L,title, author,2024, "9898765456", 29.99));

		// Mock the repository method to return the expected books
		Mockito.when(bookRepository.findByAuthorContainingIgnoreCase(author))
				.thenReturn(expectedBooks);

		// Call the service method
		List<Book> result = bookService.searchBooks(null, author);

		// Assert the result
		Assert.assertEquals(expectedBooks, result);
	}

	@Test
	public void shouldSearchBooksByTitle() {
		// Create test data
		String title = "The Mountain Top";
		String author = "Jerry King";
		List<Book> expectedBooks = new ArrayList<>();
		expectedBooks.add(new Book(1L,title, author,2018, "9898765456", 100.00));

		// Mock the repository method to return the expected books
		Mockito.when(bookRepository.findByTitleContainingIgnoreCase(title))
				.thenReturn(expectedBooks);

		// Call the service method
		List<Book> result = bookService.searchBooks(title, null);

		// Assert the result
		Assert.assertEquals(expectedBooks, result);
	}
}