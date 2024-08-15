package com.example.library.mapper;

import com.example.library.dto.AuthorRequestDTO;
import com.example.library.dto.AuthorResponseDTO;
import com.example.library.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {

    @Mappings({
            @Mapping(source = "books", target = "books")
    })
    AuthorResponseDTO toDTO(Author author);

    @Mapping(target = "id", ignore = true)
    Author toEntity(AuthorRequestDTO dto);
}
