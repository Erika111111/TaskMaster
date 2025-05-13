package ru.geekbrains.TaskManager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ru.geekbrains.TaskManager.model.Task;
import ru.geekbrains.TaskManager.service.TaskService;

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
    void testGelAllTasks(){
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskService.getAllTasks()).thenReturn(tasks);

        Model model = mock(Model.class);
        String viewName = taskController.getAllTask(model);
        assertEquals("tasks", viewName);
        verify(model).addAttribute(eq("tasks"), eq(tasks));
    }

    @Test
    void testAddTask(){
        Task task = new Task();
        task.setName("New Task");
        when(taskService.addTask(task)).thenReturn(task);
        Task savedTask = taskController.addTask(task);
        assertNotNull(savedTask);
        verify(taskService).addTask(task);
    }
}

