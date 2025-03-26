package io.github.raiela.libraryapi.repository.specs;

import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.model.BookGenre;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn){
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title){
        return (root, query, cb) -> cb.like(cb.upper(root.get("title")), "%"+title.toUpperCase()+"%");
    }

    public static Specification<Book> genreEqual(BookGenre genre){
        return (root, query, cb) -> cb.equal(root.get("genre"), genre);
    }
}
