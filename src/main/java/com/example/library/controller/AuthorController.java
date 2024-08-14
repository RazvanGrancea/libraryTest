package com.example.library.controller;

import com.example.library.dto.AuthorRequestDTO;
import com.example.library.dto.AuthorResponseDTO;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        AuthorResponseDTO author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }


    @PostMapping
    public ResponseEntity<AuthorResponseDTO> addAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO author = authorService.addAuthor(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthorById(@PathVariable Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO author = authorService.updateAuthorById(id, authorRequestDTO);
        return ResponseEntity.ok(author);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAllAuthors() {
        authorService.deleteAllAuthors();
        return ResponseEntity.noContent().build();
    }
}
