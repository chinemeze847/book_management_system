package com.mobilize.BookSystem.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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
class BookServiceImpl implements BookService {

	BookRepository bookRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Object createBook(BookRequestDTO bookRequest) {
		try {
			Book book = Book.builder()
					.author(bookRequest.getAuthor())
					.price(bookRequest.getPrice())
					.title(bookRequest.getTitle())
					.isbn(bookRequest.getIsbn())
					.publicationYear(bookRequest.getPublicationYear())
					.build();
			log.info("Saving book to database");
			return bookRepository.save(book);
		} catch (DataIntegrityViolationException e) {
			throw new BookValidationException("Invalid book data: " + e.getMessage());
		}
	}

	@Override
	public Page<Book> getAllBooks(Pageable pageable) {
		try {
			return bookRepository.findAll(pageable);
		}catch (InvalidDataAccessApiUsageException ex) {
		throw new InvalidSearchParametersException("Invalid search parameters: " + ex.getMessage());}
	}

	public Book getBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
	}

	@Override
	public List<Book> searchBooks(String title, String author) {
		try {
			if (title != null && author != null) {
				return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
			} else if (title != null) {
				return bookRepository.findByTitleContainingIgnoreCase(title);
			} else if (author != null) {
				return bookRepository.findByAuthorContainingIgnoreCase(author);
			} else {
				// If neither title nor author is provided, you can choose to return all books
				return bookRepository.findAll();
			}
		} catch (InvalidDataAccessApiUsageException ex) {
			throw new InvalidSearchParametersException("Invalid search parameters: " + ex.getMessage());
		}
	}

	@Override
	public Book updateBook(Long bookId, BookUpdateDTO updatedBook) {
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException("Book not found with ID: " + bookId);
		}
		updatedBook.setId(bookId);

		Book book = Book.builder()
				.id(bookId)
				.author(updatedBook.getAuthor())
				.price(updatedBook.getPrice())
				.title(updatedBook.getTitle())
				.isbn(updatedBook.getIsbn())
				.publicationYear(updatedBook.getPublicationYear())
				.build();
		log.info("updating book with id : {}", bookId);
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long bookId) {
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException("Book not found with ID: " + bookId);
		}
		log.info("Deleting book with id : {}", bookId);
		bookRepository.deleteById(bookId);
	}
}
