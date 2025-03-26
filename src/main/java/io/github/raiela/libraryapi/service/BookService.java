package io.github.raiela.libraryapi.service;

import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.model.BookGenre;
import io.github.raiela.libraryapi.repository.BookRepository;
import io.github.raiela.libraryapi.repository.specs.BookSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(UUID id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public List<Book> search(String isbn, String title, String authorName, BookGenre genre, Integer publicationYear) {

//        Specification<Book> specs = Specification
//                .where(BookSpecs.isbnEqual(isbn))
//                .and(BookSpecs.titleLike(title))
//                .and(BookSpecs.genreEqual(genre));

        Specification<Book> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (isbn != null)
            specs = specs.and(BookSpecs.isbnEqual(isbn));

        if (title != null)
            specs = specs.and(BookSpecs.titleLike(title));

        return bookRepository.findAll(BookSpecs.isbnEqual(isbn));
    }
}
