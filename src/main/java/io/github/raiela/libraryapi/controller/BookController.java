package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

}
