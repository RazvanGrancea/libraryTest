package com.example.library.service;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.mapper.BookMapper;
import com.example.library.model.Book;
import com.example.library.model.Author;
import com.example.library.repo.AuthorRepo;
import com.example.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookMapper bookMapper;

    public List<BookResponseDTO> getAllBooks() {
        return bookRepo.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDTO(book);
    }

    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        // Căutăm autorul după ID
        Optional<Author> optionalAuthor = authorRepo.findById(bookRequestDTO.getAuthorId());
        if (optionalAuthor.isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        Author author = optionalAuthor.get();

        // Creăm și salvăm cartea
        Book book = bookMapper.toEntity(bookRequestDTO);
        book.setAuthor(author);

        Book savedBook = bookRepo.save(book);
        return bookMapper.toDTO(savedBook);
    }

    public BookResponseDTO updateBookById(Long id, BookRequestDTO bookRequestDTO) {
        Book existingBook = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Optional<Author> optionalAuthor = authorRepo.findById(bookRequestDTO.getAuthorId());
        if (optionalAuthor.isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        Author author = optionalAuthor.get();

        Book updatedBook = bookMapper.toEntity(bookRequestDTO);
        updatedBook.setId(existingBook.getId());
        updatedBook.setAuthor(author);

        Book savedBook = bookRepo.save(updatedBook);
        return bookMapper.toDTO(savedBook);
    }

    public void deleteBookById(Long id) {
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepo.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepo.deleteAll();
    }
}
