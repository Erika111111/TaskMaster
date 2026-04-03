package ru.erikaMit.TaskManager.dto;

import org.mapstruct.Mapper;
import ru.erikaMit.TaskManager.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskRequest taskRequest);
    TaskResponse toDTO(Task task);
}
