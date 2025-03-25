package io.github.raiela.libraryapi.exceptions;

public class DuplicatedRegisterException extends RuntimeException{
    public DuplicatedRegisterException(String message) {
        super(message);
    }
}
