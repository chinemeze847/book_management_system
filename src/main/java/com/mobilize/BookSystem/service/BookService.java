package com.mobilize.BookSystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.dto.BookUpdateDTO;
import com.mobilize.BookSystem.model.Book;

public interface BookService {
	Object createBook(BookRequestDTO bookRequest);


	Page<Book> getAllBooks(Pageable pageable);

	Book updateBook(Long id, BookUpdateDTO updatedBook);

	void deleteBook(Long bookId);

	Book getBookById(Long id);

	List<Book> searchBooks(String title, String author);
}
