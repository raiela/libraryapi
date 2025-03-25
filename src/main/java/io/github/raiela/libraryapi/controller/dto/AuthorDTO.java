package io.github.raiela.libraryapi.controller.dto;

import io.github.raiela.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(UUID id,
                        @NotBlank(message = "Campo obrigatório")
                        String name,
                        @NotNull(message = "Campo obrigatório")
                        LocalDate birthDate,
                        @NotBlank(message = "Campo obrigatório")
                        String nationality) {

    public Author mapperToAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setNationality(this.nationality);
        author.setBirthDate(this.birthDate);

        return author;
    }
}
