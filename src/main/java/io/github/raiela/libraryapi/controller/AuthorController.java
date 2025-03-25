package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.exceptions.DuplicatedRegisterException;
import io.github.raiela.libraryapi.controller.dto.AuthorDTO;
import io.github.raiela.libraryapi.controller.dto.ErrorResponse;
import io.github.raiela.libraryapi.exceptions.NotAllowedActionException;
import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO author){
        try {
            Author authorEntity = author.mapperToAuthor();
            authorService.saveAuthor(authorEntity);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(authorEntity.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (DuplicatedRegisterException e) {
            ErrorResponse errorResponse = ErrorResponse.conflictError(e.getMessage());
            return ResponseEntity.status(errorResponse.status()).body(errorResponse);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") String id){
        UUID idAuthor = UUID.fromString(id);
        Optional<Author> authorGet = authorService.findById(idAuthor);
        if(authorGet.isPresent()){
            Author author = authorGet.get();
            AuthorDTO dto = new AuthorDTO(author.getId(), author.getName(), author.getBirthDate(), author.getNationality());
            return  ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        try {
            UUID idAuthor = UUID.fromString(id);
            Optional<Author> authorGet = authorService.findById(idAuthor);

            if (authorGet.isEmpty())
                return ResponseEntity.notFound().build();

            authorService.deleteAuthor(authorGet.get());
            return ResponseEntity.noContent().build();
        } catch (NotAllowedActionException e){
            ErrorResponse errorResponse = ErrorResponse.defaultResponse(e.getMessage());
            return ResponseEntity.status(errorResponse.status()).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAuthor(@RequestParam(value = "nome", required = false) String name,
                                                      @RequestParam(value = "nacionalidade", required = false) String nationality){

        List<Author> listAuthors = authorService.filterAuthor(name, nationality);

        List<AuthorDTO> dtos = listAuthors.stream()
                .map(author -> new AuthorDTO(author.getId(),
                                                author.getName(),
                                                author.getBirthDate(),
                                                author.getNationality())
                ).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody AuthorDTO dto){
        try {
            UUID idAuthor = UUID.fromString(id);
            Optional<Author> authorGet = authorService.findById(idAuthor);

            if (authorGet.isEmpty())
                return ResponseEntity.notFound().build();

            Author author = authorGet.get();
            author.setName(dto.name());
            author.setNationality(dto.nationality());
            author.setNationality(dto.nationality());

            authorService.updateAuthor(author);

            return ResponseEntity.noContent().build();
        } catch (DuplicatedRegisterException e) {
            ErrorResponse errorResponse = ErrorResponse.conflictError(e.getMessage());
            return ResponseEntity.status(errorResponse.status()).body(errorResponse);
        }
    }
}
