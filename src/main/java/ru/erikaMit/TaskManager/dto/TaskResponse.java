package ru.erikaMit.TaskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
