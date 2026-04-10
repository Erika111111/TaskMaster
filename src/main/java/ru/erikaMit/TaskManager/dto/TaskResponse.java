package ru.erikaMit.TaskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ответ для клиента
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskResponse {
    private String name;
    private String description;
    LocalDateTime createdAt;
}
