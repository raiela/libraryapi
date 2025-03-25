package io.github.raiela.libraryapi.repository;

import io.github.raiela.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
