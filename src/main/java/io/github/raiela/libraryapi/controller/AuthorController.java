package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.controller.dto.AuthorDTO;
import io.github.raiela.libraryapi.controller.mappers.AuthorMapper;
import io.github.raiela.libraryapi.model.Author;
import io.github.raiela.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AuthorController implements GenericController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO dto) {

        Author author = authorMapper.toEntity(dto);
        authorService.saveAuthor(author);
        URI location = generateLocationHeader(author.getId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") String id) {
        UUID idAuthor = UUID.fromString(id);
        Optional<Author> authorGet = authorService.findById(idAuthor);

        return authorService
                .findById(idAuthor)
                .map(author -> {
                    AuthorDTO dto = authorMapper.toDTO(author);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());

//        if(authorGet.isPresent()){
//            Author author = authorGet.get();
//            AuthorDTO dto = authorMapper.toDTO(author);
//            return  ResponseEntity.ok(dto);
//        }
//        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {

        UUID idAuthor = UUID.fromString(id);
        Optional<Author> authorGet = authorService.findById(idAuthor);

        if (authorGet.isEmpty())
            return ResponseEntity.notFound().build();

        authorService.deleteAuthor(authorGet.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAuthor(@RequestParam(value = "nome", required = false) String name,
                                                      @RequestParam(value = "nacionalidade", required = false) String nationality) {

//        List<Author> listAuthors = authorService.filterAuthor(name, nationality);
        List<Author> listAuthors = authorService.filterByAuthorWithExample(name, nationality);

        List<AuthorDTO> dtos = listAuthors.stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());

//        List<AuthorDTO> dtos = listAuthors.stream()
//                .map(author -> new AuthorDTO(author.getId(),
//                                                author.getName(),
//                                                author.getBirthDate(),
//                                                author.getNationality())
//                ).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody AuthorDTO dto) {

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
    }
}
