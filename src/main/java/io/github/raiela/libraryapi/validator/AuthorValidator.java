package io.github.raiela.libraryapi.validator;

import io.github.raiela.libraryapi.exceptions.DuplicatedRegisterException;
import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository authorRepository;

    public AuthorValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void validar(Author author){
        if(existsAuthor(author))
            throw new DuplicatedRegisterException("Autor já cadastrado");

    }

    private boolean existsAuthor(Author author){
        Optional<Author> authorGet = authorRepository
                .findByNameAndBirthDateAndNationality(
                        author.getName(),
                        author.getBirthDate(),
                        author.getNationality());

        if(author.getId() == null)
            return authorGet.isPresent();

        return authorGet.isPresent() && !author.getId().equals(authorGet.get().getId());
    }

}
