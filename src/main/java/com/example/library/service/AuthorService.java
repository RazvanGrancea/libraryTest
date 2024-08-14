package com.example.library.service;

import com.example.library.dto.AuthorRequestDTO;
import com.example.library.dto.AuthorResponseDTO;
import com.example.library.mapper.AuthorMapper;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repo.AuthorRepo;
import com.example.library.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepo.findAll().stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return authorMapper.toDTO(author);
    }

    public AuthorResponseDTO addAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = authorMapper.toEntity(authorRequestDTO);
        Author savedAuthor = authorRepo.save(author);
        return authorMapper.toDTO(savedAuthor);
    }

    public AuthorResponseDTO updateAuthorById(Long id, AuthorRequestDTO authorRequestDTO) {
        Author existingAuthor = authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Author updatedAuthor = authorMapper.toEntity(authorRequestDTO);
        updatedAuthor.setId(existingAuthor.getId());

        Author savedAuthor = authorRepo.save(updatedAuthor);
        return authorMapper.toDTO(savedAuthor);
    }

    public void deleteAuthorById(Long id) {
        if (!authorRepo.existsById(id)) {
            throw new RuntimeException("Author not found");
        }
        authorRepo.deleteById(id);
    }

    public void deleteAllAuthors() {
        authorRepo.deleteAll();
    }
}
