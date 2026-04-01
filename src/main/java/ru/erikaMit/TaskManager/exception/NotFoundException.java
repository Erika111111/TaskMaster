package ru.erikaMit.TaskManager.exception;

import lombok.Getter;

import java.util.UUID;


@Getter
public class NotFoundException extends RuntimeException {
    private final UUID id;

    public NotFoundException(String message, UUID id) {
        super(message);
        this.id = id;
    }


    public NotFoundException(String message) {
        super(message);
        this.id = null;

    }
}
