package io.github.raiela.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterBookDTO(@ISBN
                              @NotBlank(message = "Campo obrigatório")
                              String isbn,
                              @NotBlank(message = "Campo obrigatório")
                              String title,
                              @Past(message = "Não pode ser uma data futura")
                              @NotNull(message = "Campo obrigatório")
                              LocalDate publicationDate,
                              String genre,
                              BigDecimal price,
                              @NotNull(message = "Campo obrigatório")
                              UUID idAuthor) {
}
