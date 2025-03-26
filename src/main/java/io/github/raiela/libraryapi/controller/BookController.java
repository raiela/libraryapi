package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.controller.dto.ErrorExceptResponse;
import io.github.raiela.libraryapi.controller.dto.RegisterBookDTO;
import io.github.raiela.libraryapi.controller.mappers.BookMapper;
import io.github.raiela.libraryapi.exceptions.DuplicatedRegisterException;
import io.github.raiela.libraryapi.model.Book;
import io.github.raiela.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RegisterBookDTO dto){
        try {

            Book book = bookMapper.toEntity(dto);
            bookService.saveBook(book);

            URI location = generateLocationHeader(book.getId());

            return ResponseEntity.created(location).build();
        } catch (DuplicatedRegisterException e){
            ErrorExceptResponse errorDto = ErrorExceptResponse.conflictError(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
