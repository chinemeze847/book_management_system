package com.mobilize.BookSystem.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.dto.BookUpdateDTO;
import com.mobilize.BookSystem.exception.BookNotFoundException;
import com.mobilize.BookSystem.exception.BookValidationException;
import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepository bookRepository;

	private List<Book> expectedBooks;

	private BookRequestDTO validBookRequest;
	private BookUpdateDTO validBookUpdate;
	private Book validBook;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		// Create test data
		String title = "The king of Ishtar";
		String author = "Jerry King";
		expectedBooks = new ArrayList<>();
		expectedBooks.add(new Book(1L,title, author,2024, "9898765456", 29.99));

		validBookRequest = new BookRequestDTO("The Knights", "Charles James",  2022, 19.99,"5867656786");
		validBookUpdate = new BookUpdateDTO("The Sanders", "James Cahil",  2006, 889.56,"54657654323");
		validBook = new Book(1L, "The rook plays", "Chess Man",  2022,  "5967453454", 987.56);
	}


	@Test
	void shouldCreateBookWithValidCredentials() {
		when(bookRepository.save(any(Book.class))).thenReturn(validBook);

		Book createdBook = (Book) bookService.createBook(validBookRequest);

		assertNotNull(createdBook);
		assertEquals(validBook, createdBook);

	}

	@Test
	public void shouldThrowExceptionWithInvalidRequest() {
		when(bookRepository.save(any(Book.class))).thenThrow(new DataIntegrityViolationException("Data integrity violation"));

		assertThrows(BookValidationException.class, () -> bookService.createBook(validBookRequest));
	}


	@Test
	public void shouldGetAllBooks() {
		Page<Book> expectedPage = Mockito.mock(Page.class);
		when(bookRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

		Page<Book> resultPage = bookService.getAllBooks(Pageable.unpaged());

		assertNotNull(resultPage);
		assertEquals(expectedPage, resultPage);
	}

	@Test
	public void shouldGetBookWithValidID() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(validBook));

		Book resultBook = bookService.getBookById(1L);

		assertNotNull(resultBook);
		assertEquals(validBook, resultBook);
	}

	@Test
	public void shouldThrowBookNotFoundExceptionWithInvalidId() {
		when(bookRepository.findById(2L)).thenReturn(Optional.empty());

		assertThrows(BookNotFoundException.class, () -> bookService.getBookById(2L));
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

	@Test
	public void shouldSearchBooksByTitleAndAuthor() {
		when(bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase("The rook plays", "Chess Man"))
				.thenReturn(Collections.singletonList(validBook));

		List<Book> resultBooks = bookService.searchBooks("The rook plays", "Chess Man");

		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(1, resultBooks.size());
		assertEquals(validBook, resultBooks.get(0));
	}

	@Test
	public void shouldSearchBooksNoParameters() {
		when(bookRepository.findAll()).thenReturn(Collections.singletonList(validBook));

		List<Book> resultBooks = bookService.searchBooks(null, null);

		assertNotNull(resultBooks);
		assertFalse(resultBooks.isEmpty());
		assertEquals(1, resultBooks.size());
		assertEquals(validBook, resultBooks.get(0));
	}
}