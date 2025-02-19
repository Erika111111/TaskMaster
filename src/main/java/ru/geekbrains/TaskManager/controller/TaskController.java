package ru.geekbrains.TaskManager.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.TaskManager.model.Task;
import ru.geekbrains.TaskManager.model.TaskStatus;
import ru.geekbrains.TaskManager.service.TaskService;

import java.util.List;

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
    public String getAllTask(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());
    //public List<Task> getAllTask(){
        return "tasks";
    }

    /**
     * метод добавления задачи в список
     * @param task
     * @return
     */
    @PostMapping

    public Task addTask(@RequestBody Task task){

        return taskService.addTask(task);
    }

    /**
     * метод просмотра задачи по статусу
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    @ResponseBody
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
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
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        TaskStatus newTaskStatus = task.getStatus();
        return taskService.updateTaskStatus(id);
    }

    /**
     * метод удаления задачи
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}

