package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.controller.dto.RegisterBookDTO;
import io.github.raiela.libraryapi.controller.dto.ResultFindBookDTO;
import io.github.raiela.libraryapi.controller.mappers.BookMapper;
import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
