package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.controller.dto.RegisterBookDTO;
import io.github.raiela.libraryapi.controller.dto.ResultFindBookDTO;
import io.github.raiela.libraryapi.controller.mappers.BookMapper;
import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.model.BookGenre;
import io.github.raiela.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RegisterBookDTO dto) {

        Book book = bookMapper.toEntity(dto);
        bookService.saveBook(book);
        URI location = generateLocationHeader(book.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultFindBookDTO> getDetails(@PathVariable("id") String id){
        UUID idAuthor = UUID.fromString(id);
        return bookService.findById(idAuthor).map(book -> {
            ResultFindBookDTO dto = bookMapper.toDTO(book);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") String id){
        UUID idAuthor = UUID.fromString(id);
        return bookService.findById(idAuthor).map(book -> {
            bookService.deleteBook(book);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<ResultFindBookDTO>> pesquisa(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "title", required = false)
            String title,
            @RequestParam(value = "authorName", required = false)
            String authorName,
            @RequestParam(value = "genero", required = false)
            BookGenre genre,
            @RequestParam(value = "publicationYear", required = false)
            Integer publicationYear,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "pageLengh", defaultValue = "10")
            Integer pageLengh
    ){
        Page<Book> resultPage = bookService.search(
                isbn, title, authorName, genre, publicationYear, page, pageLengh);

        Page<ResultFindBookDTO> result = resultPage.map(bookMapper::toDTO);

        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") String id, @RequestBody @Valid RegisterBookDTO dto){
        UUID idAuthor = UUID.fromString(id);
        return bookService.findById(idAuthor).map(book -> {
            Book auxBook = bookMapper.toEntity(dto);
            book.setPublicationDate(auxBook.getPublicationDate());
            book.setIsbn(auxBook.getIsbn());
            book.setPrice(auxBook.getPrice());
            book.setGenre(auxBook.getGenre());
            book.setTitle(auxBook.getTitle());
            book.setAuthor(auxBook.getAuthor());
            bookService.updateBook(book);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
