package ru.erikaMit.TaskManager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.erikaMit.TaskManager.exception.IllegalArgumentException;
import ru.erikaMit.TaskManager.exception.NotFoundException;
import ru.erikaMit.TaskManager.model.Task;
import ru.erikaMit.TaskManager.model.TaskStatus;
import ru.erikaMit.TaskManager.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class TaskService {
    private final TaskRepository repository;

    /**
     * метод просмотра всех задач
     * @return
     */
    public List<Task> getAllTasks() {
        return repository.findAll();

    }

    /**
     * метод поиска задачи по id
     * @param id
     * @return
     */
    public Task getTaskById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Task not found with id: " + id, id));
    }

    /**
     * метод добавления задачи в список задач
     * @param task задача
     * @return
     */
    public Task addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        task.setStatus(TaskStatus.NOT_STARTED);
        return repository.save(task);
    }

    /**
     * метод просмотра задачи по статусу
     * @return задача
     */
    public List<Task> getTasksByStatus(TaskStatus taskStatus) {
        return repository.findByStatus(taskStatus);
    }

    /**
     * метод изменения статуса задачи
     * @param id идентификатор задачи
     * @param newStatus новый статус
     * @return обновленнвя задача
     */
    public Task updateTaskStatus(UUID id, TaskStatus newStatus) {
        final Task curreent = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Task not found with id: " + id));
        curreent.setStatus(newStatus);
        return repository.save(curreent);


    }

    /**
     * метод удаления задачи
     * @param id
     */
    public void deleteTask(UUID id) {

        final Task task = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Deletion is impossible. Task not found with id:" + id, id));
        repository.deleteById(id);
    }

}

