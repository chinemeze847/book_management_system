package com.mobilize.BookSystem.service;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.exception.BookValidationException;
import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.repository.BookRepository;

@Service
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

	@Override
	public Book updateBook(BookRequestDTO book) {
		return null;
	}

	@Override
	public Book deleteBook(Long bookId) {
		return null;
	}
}
