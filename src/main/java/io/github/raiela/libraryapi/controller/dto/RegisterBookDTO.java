package io.github.raiela.libraryapi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterBookDTO(String isbn,
                              String title,
                              LocalDate publicationDate,
                              String genre,
                              BigDecimal price,
                              UUID idAuthor) {
}
