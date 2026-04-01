package ru.erikaMit.TaskManager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.erikaMit.TaskManager.model.Task;
import ru.erikaMit.TaskManager.model.TaskStatus;
import ru.erikaMit.TaskManager.repository.TaskRepository;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class TaskServiceTest {

   @Mock
    private TaskRepository taskRepository;

   @InjectMocks
   private TaskService taskService;

   @BeforeEach
   void setUp(){
       MockitoAnnotations.openMocks(this);
   }


   @Test
   void testGetAllTasks(){
       List<Task> expectedTasks = Arrays.asList(new Task(), new Task());
       when(taskRepository.findAll()).thenReturn(expectedTasks);

       List<Task>actualTasks = taskService.getAllTasks();
       assertEquals(expectedTasks.size(), actualTasks.size());
       verify(taskRepository).findAll();
   }

   @Test
   void testAddTask(){
       Task task = new Task();
       task.setName("Test Task");
       task.setDescription("Test Description");

       when(taskRepository.save(any(Task.class))).thenReturn(task);

       Task savedTask = taskService.addTask(task);
       assertNotNull(savedTask);

       assertEquals(TaskStatus.NOT_STARTED, savedTask.getStatus());
       verify(taskRepository).save(task);
   }

//  @Test
//   void testDeleteTask(){
//       UUID taskId = UUID.randomUUID();
//       when(taskRepository).deleteById(taskId);
//       taskService.deleteTask(taskId);
//       verify(taskRepository).deleteById(taskId);
//  }


}
