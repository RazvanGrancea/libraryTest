package com.example.library.dto;

import lombok.Data;

@Data
public class BookRequestDTO {

    private String title;

    private Long authorId;

    private String description;
}
