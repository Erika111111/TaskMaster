package ru.erikaMit.TaskManager.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.erikaMit.TaskManager.model.Task;
import ru.erikaMit.TaskManager.model.TaskStatus;
import ru.erikaMit.TaskManager.service.TaskService;

import java.util.List;
import java.util.UUID;

@Controller
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
    public String getAllTask(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    /**
     * метод добавления задачи в список
     * @param task
     * @return
     */
    @PostMapping

    public Task addTask(@Valid @RequestBody Task task) {

        return taskService.addTask(task);
    }

    /**
     * метод просмотра задачи по статусу
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    @ResponseBody
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    /**
     * метод изменения статуса задачи
     * @param id
     * @param task
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public Task updateTaskStatus(@PathVariable UUID id, @Valid @RequestBody Task task) {
        return taskService.updateTaskStatus(id, task.getStatus());
    }

    /**
     * метод удаления задачи
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}

