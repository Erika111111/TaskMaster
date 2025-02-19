package ru.geekbrains.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.TaskManager.model.Task;
import ru.geekbrains.TaskManager.model.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(@Param("status") TaskStatus status);

}

