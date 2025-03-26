package io.github.raiela.libraryapi.validator;

import io.github.raiela.libraryapi.exceptions.DuplicatedRegisterException;
import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository bookRepository;

    public void validate(Book book) {
        if (existsBook(book))
            throw new DuplicatedRegisterException("ISBN já cadastrado");
    }

    private boolean existsBook(Book book) {
        Optional<Book> bookGet = bookRepository.findByIsbn(book.getIsbn());

        if(book.getId() == null)
            return bookGet.isPresent();

        return bookGet.isPresent() && !bookGet.get().getId().equals(bookGet.get().getId());
    }
}
