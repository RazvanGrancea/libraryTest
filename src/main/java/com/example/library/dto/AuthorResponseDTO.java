package com.example.library.dto;

import lombok.Data;
import java.util.Set;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private Set<BookResponseDTO> books;

}
