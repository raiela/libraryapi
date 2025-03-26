package io.github.raiela.libraryapi.controller.mappers;

import io.github.raiela.libraryapi.controller.dto.RegisterBookDTO;
import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    protected AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java( getAuthor(dto.idAuthor()) )")
    public abstract Book toEntity(RegisterBookDTO dto);

    protected Author getAuthor(UUID authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }
}
