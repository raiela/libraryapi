package io.github.raiela.libraryapi.service;

import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
