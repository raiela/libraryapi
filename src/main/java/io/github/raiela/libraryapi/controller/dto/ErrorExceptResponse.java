package io.github.raiela.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorExceptResponse(int status, String message, List<ErrorExceptField> errors) {

    public static ErrorExceptResponse defaultResponse(String message){
        return new ErrorExceptResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorExceptResponse conflictError(String message){
        return new ErrorExceptResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
