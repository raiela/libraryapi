package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.controller.dto.ErrorExceptResponse;
import io.github.raiela.libraryapi.controller.dto.RegisterBookDTO;
import io.github.raiela.libraryapi.exceptions.DuplicatedRegisterException;
import io.github.raiela.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RegisterBookDTO dto){
        try {

            return ResponseEntity.ok(dto);
        } catch (DuplicatedRegisterException e){
            ErrorExceptResponse errorDto = ErrorExceptResponse.conflictError(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);
        }
    }
}
