package ru.erikaMit.TaskManager.exception;

import lombok.Getter;

import java.util.UUID;


@Getter
public class TaskValidationException extends RuntimeException {

    private final UUID id;


    public TaskValidationException(String message, UUID id) {
        super(message);
        this.id = id;

    }
    public TaskValidationException(String message) {
        super(message);
        this.id = null;

    }

}
