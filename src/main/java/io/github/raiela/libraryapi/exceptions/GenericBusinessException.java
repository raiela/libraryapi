package io.github.raiela.libraryapi.exceptions;

import lombok.Getter;

public class GenericBusinessException extends RuntimeException{

    @Getter
    private String field;

    public GenericBusinessException(String field, String message) {
        super(message);
        this.field = field;
    }
}
