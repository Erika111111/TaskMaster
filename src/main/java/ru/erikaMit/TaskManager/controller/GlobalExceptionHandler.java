package ru.erikaMit.TaskManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.erikaMit.TaskManager.exception.ErrorMessageDto;
import ru.erikaMit.TaskManager.exception.TaskValidationException;
import ru.erikaMit.TaskManager.exception.TaskNotFoundException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
 public ErrorMessageDto handleNotFoundException(TaskNotFoundException ex) {
        return new ErrorMessageDto(
                ex.getId(),
                "NOT_FOUND",
                ex.getMessage(),
                Instant.now());
    }

    @ExceptionHandler(TaskValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
 public ErrorMessageDto handleIllegalArgumentException (TaskValidationException ex) {
        return new ErrorMessageDto(
                ex.getId(),
                "ILLEGAL_ARGUMENT",
                ex.getMessage(),
                Instant.now());

    }
}
