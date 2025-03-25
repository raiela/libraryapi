package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.controller.dto.AuthorDTO;
import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO author){
        Author authorEntity = author.mapperToAuthor();
        authorService.saveAuthor(authorEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(authorEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") String id){
        UUID idAuthor = UUID.fromString(id);
        Optional<Author> authorGet = authorService.findById(idAuthor);
        if(authorGet.isPresent()){
            Author author = authorGet.get();
            AuthorDTO dto = new AuthorDTO(author.getName(), author.getBirthDate(), author.getNationality());
            return  ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        UUID idAuthor = UUID.fromString(id);
        Optional<Author> authorGet = authorService.findById(idAuthor);

        if(authorGet.isEmpty())
            return ResponseEntity.notFound().build();

        authorService.deleteAuthor(authorGet.get());
        return ResponseEntity.noContent().build();
    }
}
