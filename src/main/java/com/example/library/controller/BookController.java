package com.example.library.controller;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }


    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO book = bookService.addBook(bookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBookById(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO book = bookService.updateBookById(id, bookRequestDTO);
        return ResponseEntity.ok(book);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllBooks() {
        bookService.deleteAllBooks();
        return ResponseEntity.noContent().build();
    }
}
