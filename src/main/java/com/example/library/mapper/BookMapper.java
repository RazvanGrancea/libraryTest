package com.example.library.mapper;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.model.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookMapper {

    @Mapping(source = "author.id", target = "authorId")
    BookResponseDTO toDTO(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "authorId", target = "author.id")
    Book toEntity(BookRequestDTO dto);
}
