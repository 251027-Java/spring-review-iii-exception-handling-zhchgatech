package com.revature.library.controller;

import com.revature.library.exception.BookNotFoundException;
import com.revature.library.model.Book;
import com.revature.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    // Constructor injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /api/books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET /api/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id " + id));
        return ResponseEntity.ok(book);
    }

    // POST /api/books
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    // PUT /api/books/{id}/checkout
    @PutMapping("/{id}/checkout")
    public ResponseEntity<Book> checkoutBook(@PathVariable Long id) {
        Book book = bookService.checkoutBook(id);
        return ResponseEntity.ok(book);
    }

    // PUT /api/books/{id}/return
    @PutMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long id) {
        Book book = bookService.returnBook(id);
        return ResponseEntity.ok(book);
    }
}

