package ru.geekbrains.TaskManager.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.TaskManager.model.Task;
import ru.geekbrains.TaskManager.model.TaskStatus;
import ru.geekbrains.TaskManager.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class TaskService {
    private final TaskRepository repository;

    /**
     * метод просмотра всех задач
     * @return
     */
    public List<Task> getAllTasks(){
        return repository.findAll();

    }

    /**
     * метод добавления задачи в список задач
     * @param task задача
     * @return
     */
    public Task addTask(Task task){
        task.setStatus(TaskStatus.NOT_STARTED);

        return repository.save(task);
    }

    /**
     * метод просмотра задачи по статусу
     * @return задача
     */
    public List<Task> getTasksByStatus(TaskStatus taskStatus){

        return repository.findByStatus(taskStatus);
    }

    /**
     * метод изменения статуса задачи
     * @param id
     * @param
     * @return
     */
    public Task updateTaskStatus(@PathVariable Long id){
        Task curreent = repository.findById(id).orElse(null);
        if(curreent != null){
            int currentStatusNumber = curreent.getStatus().ordinal();
            if (curreent.getStatus() != TaskStatus.COMPLETED){
                currentStatusNumber++;
                curreent.setStatus(TaskStatus.values()[currentStatusNumber]);
            }
        }
        return repository.save(curreent);
    }

    /**
     * метод удаления задачи
     * @param id
     */
    public void deleteTask(@PathVariable Long id){
        repository.deleteById(id);
    }

}

