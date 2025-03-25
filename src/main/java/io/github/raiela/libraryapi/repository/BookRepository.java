package io.github.raiela.libraryapi.repository;

import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByAuthor(Author author);

    List<Book> findByTitle(String title);

    List<Book> findByIsbn(String isbn);

    List<Book> findByTitleAndPrice(String title, BigDecimal price);

    List<Book> findByTitleOrIsbn(String title, String isbn);

    List<Book> findByPublicationDateBetween(LocalDate start, LocalDate end);

    boolean existsByAuthor(Author author);
}
