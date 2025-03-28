package io.github.raiela.libraryapi.repository;

import io.github.raiela.libraryapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {

    User findByLogin(String login);

}
