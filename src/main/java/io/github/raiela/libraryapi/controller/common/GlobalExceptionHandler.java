package io.github.raiela.libraryapi.controller.common;

import io.github.raiela.libraryapi.controller.dto.ErrorExceptField;
import io.github.raiela.libraryapi.controller.dto.ErrorExceptResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorExceptResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorExceptField> errorsList = fieldErrors.stream()
                .map(fe -> new ErrorExceptField(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorExceptResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação!", errorsList);
    }
}
