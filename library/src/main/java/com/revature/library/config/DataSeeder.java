package com.revature.library.config;

import com.revature.library.model.Book;
import com.revature.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataSeeder(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book("Clean Code", "Robert Martin", "ISBN-001"));
            bookRepository.save(new Book("The Pragmatic Programmer", "David Thomas", "ISBN-002"));
            bookRepository.save(new Book("Design Patterns", "Gang of Four", "ISBN-003"));
            bookRepository.save(new Book("Effective Java", "Joshua Bloch", "ISBN-004"));
            bookRepository.save(new Book("Spring in Action", "Craig Walls", "ISBN-005"));
        }
    }
}
