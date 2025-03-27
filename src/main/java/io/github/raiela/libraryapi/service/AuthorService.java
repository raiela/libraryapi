package io.github.raiela.libraryapi.service;

import io.github.raiela.libraryapi.exceptions.NotAllowedActionException;
import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.repository.AuthorRepository;
import io.github.raiela.libraryapi.repository.BookRepository;
import io.github.raiela.libraryapi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;
    private final BookRepository bookRepository;

    public Author saveAuthor(Author author){
        authorValidator.validate(author);
        return authorRepository.save(author);
    }

    public void updateAuthor(Author author){
        if(author.getId() == null)
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja na base");
        authorValidator.validate(author);
        authorRepository.save(author);
    }

    public Optional<Author> findById(UUID id){
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Author author) {
        if(hasBooks(author))
            throw new NotAllowedActionException("Não é permitido excluir autor que possuir livros cadastrados");
        authorRepository.delete(author);
    }

    public List<Author> filterAuthor(String name, String nationality){
        if(name != null && nationality != null)
            return authorRepository.findByNameAndNationality(name, nationality);

        if(name != null)
            return  authorRepository.findByName(name);

        if(nationality != null)
            return authorRepository.findByNationality(nationality);

        return authorRepository.findAll();
    }

    public List<Author> filterByAuthorWithExample(String name, String nationality){
        Author author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Author> authorExample = Example.of(author, matcher);

        return authorRepository.findAll(authorExample);
    }

    public boolean hasBooks(Author author){
        return bookRepository.existsByAuthor(author);
    }
}
