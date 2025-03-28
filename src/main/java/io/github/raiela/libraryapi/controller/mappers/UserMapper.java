package io.github.raiela.libraryapi.controller.mappers;

import io.github.raiela.libraryapi.controller.dto.UserDTO;
import io.github.raiela.libraryapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDTO(User user);

}
