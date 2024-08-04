package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repo.BookRepo;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bookList")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> bookOptional = bookService.getBookById(id);
        return bookOptional
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping
    @Tag(name = "Add Book")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData){
         Optional<Book>  updatedBook = bookService.updateBookById(id, newBookData);
        return updatedBook
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());



    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookByID(@PathVariable Long id){
        if (bookService.getBookById(id).isPresent()){
            bookService.deleteBookById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllBooks(){
        bookService.deleteAllBooks();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
