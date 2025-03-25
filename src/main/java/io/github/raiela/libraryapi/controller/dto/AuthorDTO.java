package io.github.raiela.libraryapi.controller.dto;

import io.github.raiela.libraryapi.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(UUID id,
                        String name,
                        LocalDate birthDate,
                        String nationality) {

    public Author mapperToAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setNationality(this.nationality);
        author.setBirthDate(this.birthDate);

        return author;
    }
}
