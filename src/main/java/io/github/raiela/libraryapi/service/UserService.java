package io.github.raiela.libraryapi.service;

import io.github.raiela.libraryapi.model.User;
import io.github.raiela.libraryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void save(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

}
