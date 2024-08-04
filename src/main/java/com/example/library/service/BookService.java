package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private BookRepo bookRepo;


    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookRepo.findAll().forEach(bookList::add);
        return bookList;
    }

    public Optional<Book> getBookById(Long id) {
        for (Book book : bookRepo.findAll())
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        return Optional.empty();
    }

    public Book addBook(Book book) {
        bookRepo.save(book);
        return book;
    }

    public Optional<Book> updateBookById(Long id, Book newBookData)  {
        Optional<Book> oldBookData = bookRepo.findById(id);
        if (oldBookData.isPresent()) {
            Book updatedBookData = oldBookData.get();
            updatedBookData.setTitle(newBookData.getTitle());
            updatedBookData.setAuthor(newBookData.getAuthor());
            bookRepo.save(updatedBookData);
            return Optional.of(updatedBookData);
        }
        return Optional.empty();
    }
    public Optional<Book> deleteBookById(Long id){
        Optional<Book> optBook = getBookById(id);
        optBook.ifPresent(bookRepo::delete);
        return optBook;

    }
    public void deleteAllBooks(){
        bookRepo.deleteAll();
    }

}