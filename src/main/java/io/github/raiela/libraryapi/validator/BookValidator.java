package io.github.raiela.libraryapi.validator;

import io.github.raiela.libraryapi.exceptions.DuplicatedRegisterException;
import io.github.raiela.libraryapi.exceptions.GenericBusinessException;
import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private static final int REQUIRED_YEAR_PRICE = 2020;
    private final BookRepository bookRepository;

    public void validate(Book book) {
        if (existsBook(book))
            throw new DuplicatedRegisterException("ISBN já cadastrado");

        if (isPriceRequiredNull(book))
            throw new GenericBusinessException("preço", "Para livros com ano de publicação a partir de 2020, preço é obrigatório ");
    }

    private boolean isPriceRequiredNull(Book book) {
        return book.getPrice() == null && book.getPublicationDate().getYear() >= REQUIRED_YEAR_PRICE;
    }

    private boolean existsBook(Book book) {
        Optional<Book> bookGet = bookRepository.findByIsbn(book.getIsbn());

        if (book.getId() == null)
            return bookGet.isPresent();

        return bookGet.isPresent() && !bookGet.get().getId().equals(bookGet.get().getId());
    }
}
