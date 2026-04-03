package ru.erikaMit.TaskManager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.erikaMit.TaskManager.dto.TaskMapper;
import ru.erikaMit.TaskManager.dto.TaskRequest;
import ru.erikaMit.TaskManager.dto.TaskResponse;
import ru.erikaMit.TaskManager.exception.TaskValidationException;
import ru.erikaMit.TaskManager.exception.TaskNotFoundException;
import ru.erikaMit.TaskManager.model.Task;
import ru.erikaMit.TaskManager.model.TaskStatus;
import ru.erikaMit.TaskManager.repository.TaskRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper taskMapper;


    /**
     * метод просмотра всех задач
     * @return
     */
    public List<TaskResponse> getAllTasks() {
        return repository.findAll()
                .stream().map(taskMapper::toDTO).collect(Collectors.toList());

    }

    /**
     * метод поиска задачи по id
     * @param id
     * @return
     */
    public TaskResponse getTaskById(UUID id) {
        if (id == null) {
            throw new TaskValidationException("Task ID cannot be null");
        }
        final Task task = repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id, id));
        return taskMapper.toDTO(task);
    }

    /**
     * метод добавления задачи в список задач
     * @param taskRequest задача
     * @return
     */
    public TaskResponse addTask(TaskRequest taskRequest) {
        if (taskRequest == null) {
            throw new TaskValidationException("Task cannot be null");
        }
        if (taskRequest.getName() == null || taskRequest.getName().isBlank()) {
            throw new TaskValidationException("Task name cannot be empty");

        }
        final Task task = taskMapper.toEntity(taskRequest);
        task.setStatus(TaskStatus.NOT_STARTED);
        final Task savedTask = repository.save(task);
        return taskMapper.toDTO(savedTask);

    }

    /**
     * метод просмотра задачи по статусу
     * @return задача
     */
    public List<TaskResponse> getTasksByStatus(TaskStatus taskStatus) {
        return repository.findByStatus(taskStatus)
                .stream().map(taskMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * метод изменения статуса задачи
     * @param id идентификатор задачи
     * @param newStatus новый статус
     * @return обновленнвя задача
     */
    public TaskResponse updateTaskStatus(UUID id, TaskStatus newStatus) {
        final Task curreent = repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id, id));
        curreent.setStatus(newStatus);
        final Task updatedTask = repository.save(curreent);
        return taskMapper.toDTO(updatedTask);


    }

    /**
     * метод удаления задачи
     * @param id идентификатор задачи
     */
    public void deleteTask(UUID id) {

        final Task task = repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Deletion is impossible. Task not found with id:" + id, id));
        repository.deleteById(id);
    }


    /**
     * метод для обновления задачи
     * @param id идентификатор задачи
     * @param request DTO с новыми данными
     * @return обновленная задача в виде DTO
     */

    public TaskResponse updateTask(UUID id, TaskRequest request) {
        if (id == null) {
            throw new TaskValidationException("Task ID cannot be null");
        }
        final Task update = repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        update.setName(request.getName());
        update.setDescription(request.getDescription());
        return taskMapper.toDTO(update);
    }
}

