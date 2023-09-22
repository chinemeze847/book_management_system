package com.mobilize.BookSystem.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.dto.BookUpdateDTO;
import com.mobilize.BookSystem.exception.BookNotFoundException;
import com.mobilize.BookSystem.exception.BookValidationException;
import com.mobilize.BookSystem.exception.InvalidSearchParametersException;
import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.repository.BookRepository;

@Service
@Slf4j
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

	BookRepository bookRepository;
	@Override
	public Object createBook(BookRequestDTO bookRequest) {
		try {
			Book book = Book.builder()
					.author(bookRequest.getAuthor())
					.price(bookRequest.getPrice())
					.title(bookRequest.getTitle())
					.isbn(bookRequest.getIsbn())
					.publicationYear(bookRequest.getPublicationDate())
					.build();
			return bookRepository.save(book);
		} catch (DataIntegrityViolationException e) {
			throw new BookValidationException("Invalid book data: " + e.getMessage());
		}
	}

	@Override
	public Page<Book> getAllBooks(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	public Book getBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
	}

	@Override
	public List<Book> searchBooks(String title, String author) {
		try {
			return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(title, author);
		} catch (InvalidDataAccessApiUsageException ex) {
			throw new InvalidSearchParametersException("Invalid search parameters: " + ex.getMessage());
		}
	}

	@Override
	public Book updateBook(Long id, BookUpdateDTO updatedBook) {
		if (!bookRepository.existsById(id)) {
			throw new BookNotFoundException("Book not found with ID: " + id);
		}
		updatedBook.setId(id);

		Book book = Book.builder()
				.id(id)
				.author(updatedBook.getAuthor())
				.price(updatedBook.getPrice())
				.title(updatedBook.getTitle())
				.isbn(updatedBook.getIsbn())
				.publicationYear(updatedBook.getPublicationDate())
				.build();

		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException("Book not found with ID: " + bookId);
		}
		bookRepository.deleteById(bookId);
	}
}
