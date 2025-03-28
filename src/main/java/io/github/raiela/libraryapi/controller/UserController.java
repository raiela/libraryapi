package io.github.raiela.libraryapi.controller;

import io.github.raiela.libraryapi.controller.dto.UserDTO;
import io.github.raiela.libraryapi.controller.mappers.UserMapper;
import io.github.raiela.libraryapi.model.User;
import io.github.raiela.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UserDTO dto) {
        User user = mapper.toEntity(dto);
        userService.save(user);
    }


}
