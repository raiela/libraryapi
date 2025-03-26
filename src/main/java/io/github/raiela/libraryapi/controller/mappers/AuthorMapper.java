package io.github.raiela.libraryapi.controller.mappers;

import io.github.raiela.libraryapi.controller.dto.AuthorDTO;
import io.github.raiela.libraryapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "name", target = "name")
    Author toEntity(AuthorDTO dto);

    AuthorDTO toDTO(Author author);
}
