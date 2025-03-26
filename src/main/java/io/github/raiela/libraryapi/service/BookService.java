package io.github.raiela.libraryapi.service;

import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.model.BookGenre;
import io.github.raiela.libraryapi.repository.BookRepository;
import io.github.raiela.libraryapi.repository.specs.BookSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.raiela.libraryapi.repository.specs.BookSpecs.*;

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

    public Page<Book> search(String isbn, String title, String authorName, BookGenre genre, Integer publicationYear, Integer page, Integer lenghPage) {

//        Specification<Book> specs = Specification
//                .where(BookSpecs.isbnEqual(isbn))
//                .and(BookSpecs.titleLike(title))
//                .and(BookSpecs.genreEqual(genre));

        Specification<Book> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(isbn != null){
            // query = query and isbn = :isbn
            specs = specs.and(isbnEqual(isbn));
        }

        if(title != null){
            specs = specs.and(titleLike(title));
        }

        if(genre != null){
            specs = specs.and(genreEqual(genre));
        }

        if(publicationYear != null){
            specs = specs.and(publicationYearEqual(publicationYear));
        }

        if(authorName != null){
            specs = specs.and(authorNameLike(authorName));
        }

        Pageable pageRequest = PageRequest.of(page, lenghPage);

        return bookRepository.findAll(specs, pageRequest);
    }
}
