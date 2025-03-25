package io.github.raiela.libraryapi.service;

import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }

    public Optional<Author> findById(UUID id){
        return authorRepository.findById(id);
    }

}
