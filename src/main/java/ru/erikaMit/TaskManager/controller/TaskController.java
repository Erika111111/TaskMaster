package ru.erikaMit.TaskManager.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.erikaMit.TaskManager.dto.TaskRequest;
import ru.erikaMit.TaskManager.dto.TaskResponse;
import ru.erikaMit.TaskManager.model.TaskStatus;
import ru.erikaMit.TaskManager.service.TaskService;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;


    /**
     * метод просмотра всех задач
     *
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getAllTask() {
        log.info("GET all tasks");
        final List<TaskResponse> responseList = taskService.getAllTasks();
        log.debug("Found {} tasks", responseList.size());
        return responseList;
    }

    /**
     * метод добавления задачи в список
     * @param taskRequest
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        log.info("POST create task");
        final TaskResponse taskResponse = taskService.addTask(taskRequest);
        log.debug("Create {} task", taskResponse.getName());
        return taskResponse;

    }

    /**
     * метод просмотра задачи по статусу
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getTasksByStatus(@PathVariable TaskStatus status) {
        log.info("GET tasks by {} status", status);
        final List<TaskResponse> responseList = taskService.getTasksByStatus(status);
        log.debug("Found {} tasks by {} status", responseList.size(), status);
        return responseList;

    }

    /**
     * метод изменения статуса задачи
     * @param id
     * @param status
     * @return
     */
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTaskStatus(@PathVariable UUID id, @RequestParam TaskStatus status) {
        log.info("PATCH update task status-id {}, new status: {}", id, status);
        final TaskResponse taskResponse = taskService.updateTaskStatus(id, status);
        log.debug("Task {} status updated", id);
        return taskResponse;

    }

    /**
     * метод изменения задачи
     * @param id
     * @param request
     * @return
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTask(@PathVariable UUID id, @Valid @RequestBody TaskRequest request) {
        log.info("PATCH task with {} id", id);
        final TaskResponse taskResponse = taskService.updateTask(id, request);
        log.debug("Task {} update",  taskResponse.getName());
        return taskResponse;

    }

    /**
     * метод поиска задачи по id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse getTaskById(@PathVariable UUID id) {
        log.info("GET task by id");
        final TaskResponse taskResponse = taskService.getTaskById(id);
        log.debug("Task {} found", id);
        return taskResponse;

    }

    /**
     * метод удаления задачи
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable UUID id) {
        log.info("DELETE task");
        taskService.deleteTask(id);
        log.debug("Delete task {} id", id);
    }
}

