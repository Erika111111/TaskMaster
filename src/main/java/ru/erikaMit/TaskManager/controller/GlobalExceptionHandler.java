package ru.erikaMit.TaskManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.erikaMit.TaskManager.exception.ErrorMessageDto;
import ru.erikaMit.TaskManager.exception.IllegalArgumentException;
import ru.erikaMit.TaskManager.exception.NotFoundException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
 public ErrorMessageDto handleNotFoundException(NotFoundException ex) {
        return new ErrorMessageDto(ex.getId(), ex.getMessage(), Instant.now());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
 public ErrorMessageDto handleIllegalArgumentException (IllegalArgumentException ex) {
        return new ErrorMessageDto(ex.getId(), ex.getMessage(), Instant.now());

    }
}
