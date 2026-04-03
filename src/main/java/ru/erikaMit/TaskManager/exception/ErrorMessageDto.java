package ru.erikaMit.TaskManager.exception;

import java.time.Instant;
import java.util.UUID;

public record ErrorMessageDto(
    UUID id,
    String errorType,
    String message,
    Instant timestamp
) { }



