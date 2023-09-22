package com.mobilize.BookSystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mobilize.BookSystem.dto.BookRequestDTO;
import com.mobilize.BookSystem.model.Book;

public interface BookService {
	Object createBook(BookRequestDTO bookRequest);


	Page<Book> getAllBooks(Pageable pageable);

	Book updateBook(BookRequestDTO book);

	Book deleteBook(Long bookId);
}
