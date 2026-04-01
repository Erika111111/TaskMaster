package ru.erikaMit.TaskManager.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class IllegalArgumentException extends RuntimeException {

    private final UUID id;


    public IllegalArgumentException(String message, UUID id) {
        super(message);
        this.id = id;

    }
    public IllegalArgumentException(String message) {
        super(message);
        this.id = null;

    }

}
