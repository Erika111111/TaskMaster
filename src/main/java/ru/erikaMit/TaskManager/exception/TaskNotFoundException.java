package ru.erikaMit.TaskManager.exception;

import lombok.Getter;

import java.util.UUID;



@Getter
public class TaskNotFoundException extends RuntimeException {
    private final UUID id;

    public TaskNotFoundException(String message, UUID id) {
        super(message);
        this.id = id;
    }


    public TaskNotFoundException(String message) {
        super(message);
        this.id = null;

    }
}
