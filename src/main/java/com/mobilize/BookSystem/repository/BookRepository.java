package com.mobilize.BookSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobilize.BookSystem.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}
