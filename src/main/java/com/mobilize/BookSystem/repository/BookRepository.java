package com.mobilize.BookSystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mobilize.BookSystem.model.Book;

/**
 * Repository interface for accessing and managing book data in the database.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * Find all books with pagination support.
	 *
	 * @param pageable Pagination information.
	 * @return A page of books.
	 */
	Page<Book> findAll(Pageable pageable);

	/**
	 * Find books by title, ignoring case.
	 *
	 * @param title The title to search for.
	 * @return A list of books matching the title.
	 */
	List<Book> findByTitleContainingIgnoreCase(String title);

	/**
	 * Find books by author, ignoring case.
	 *
	 * @param author The author to search for.
	 * @return A list of books written by the author.
	 */
	List<Book> findByAuthorContainingIgnoreCase(String author);

	/**
	 * Find books by title and author, ignoring case.
	 *
	 * @param title  The title to search for.
	 * @param author The author to search for.
	 * @return A list of books matching both the title and author.
	 */
	List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
}
