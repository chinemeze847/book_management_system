package com.mobilize.BookSystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mobilize.BookSystem.model.Book;
import com.mobilize.BookSystem.repository.BookRepository;

@SpringBootApplication
public class BookSystemApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository; // Your repository for the Book entity
	public static void main(String[] args) {
		SpringApplication.run(BookSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializeData();
	}

	public void initializeData() {
		// Add code here to seed your initial data
		Book book1 = new Book("love", "eze", 2022,"87898764443" ,100.99);
		Book book2 = new Book("The Jungle", "Alisa", 2021,"5488484848", 19.99);
		Book book3 = new Book("days in school", "Charles", 2019,"54884845857", 134.99);
		Book book4 = new Book("The last mile", "Elysee", 2003,"548848494848", 148.99);
		Book book5 = new Book("kill that frog", "Anthony Henry", 2019,"54884800088", 133.92);
		Book book6 = new Book("Enjoy yourself", "Charles", 2015,"54884843223", 198.57);

		bookRepository.saveAll(Arrays.asList(book1, book2,book3,book4,book5,book6));
	}
}
