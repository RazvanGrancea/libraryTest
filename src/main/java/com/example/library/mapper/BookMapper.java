package com.example.library.mapper;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "description", target = "description")
    BookResponseDTO toDTO(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "description", target = "description")
    Book toEntity(BookRequestDTO dto);
}
