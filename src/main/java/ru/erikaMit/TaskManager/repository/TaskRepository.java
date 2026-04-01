package ru.erikaMit.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.erikaMit.TaskManager.model.Task;
import ru.erikaMit.TaskManager.model.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByStatus(@Param("status") TaskStatus status);

}

