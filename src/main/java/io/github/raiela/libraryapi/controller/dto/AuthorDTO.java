package io.github.raiela.libraryapi.controller.dto;

import io.github.raiela.libraryapi.model.Author;

import java.time.LocalDate;

public record AuthorDTO(String name,
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
