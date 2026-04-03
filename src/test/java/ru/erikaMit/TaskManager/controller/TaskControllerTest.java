package ru.erikaMit.TaskManager.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.erikaMit.TaskManager.dto.TaskRequest;
import ru.erikaMit.TaskManager.dto.TaskResponse;
import ru.erikaMit.TaskManager.service.TaskService;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TaskControllerTest {
    @Mock
    private TaskService taskService;
    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        // Подготовка
        TaskResponse task1 = new TaskResponse();
        task1.setName("Task 1");
        TaskResponse task2 = new TaskResponse();
        task2.setName("Task 2");
        List<TaskResponse> tasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(tasks);

        // Вызов
        List<TaskResponse> result = taskController.getAllTask();

        // Проверка
        assertEquals(2, result.size());
        verify(taskService).getAllTasks();
    }



    @Test
    void testAddTask(){
        TaskRequest request = new TaskRequest();
        request.setName("New Task");
        request.setDescription("Description");

        TaskResponse response = new TaskResponse();
        response.setName("New Task");
        response.setDescription("Description");

        when(taskService.addTask(request)).thenReturn(response);

        TaskResponse savedTask = taskController.createTask(request);

        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getName());
        verify(taskService).addTask(request);
    }
}

