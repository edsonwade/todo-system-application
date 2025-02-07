package code.with.vanilson.todo.task;

import code.with.vanilson.todo.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private Long taskId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskId = 1L;
        task = new Task();
        task.setId(taskId);
        task.setName("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);
        task.setDueDate(LocalDate.now());
    }

    @Test
    void testGetTasks() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Task> taskPage = new PageImpl<>(Collections.singletonList(task));
        when(taskRepository.findAll(pageable)).thenReturn(taskPage);

        Page<Task> result = taskService.getTasks(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(taskRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetTaskById() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(taskId);

        assertNotNull(result);
        assertEquals(taskId, result.getId());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testGetTaskByIdNotFound() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(taskId));
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void testSaveTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.saveTask(task);

        assertNotNull(result);
        assertEquals(taskId, result.getId());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testUpdateTask() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = new Task();
        updatedTask.setName("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setCompleted(true);

        Task result = taskService.updateTask(taskId, updatedTask);

        assertNotNull(result);
        assertEquals("Updated Task", result.getName());
        assertEquals("Updated Description", result.getDescription());
        assertTrue(result.isCompleted());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testUpdateTaskNotFound() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        Task updatedTask = new Task();
        updatedTask.setName("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setCompleted(true);

        assertThrows(ResourceNotFoundException.class, () -> taskService.updateTask(taskId, updatedTask));
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(0)).save(any(Task.class));
    }

    @Test
    void testDeleteTask() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void testDeleteTaskNotFound() {
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taskService.deleteTask(taskId));
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(0)).delete(any(Task.class));
    }

    @Test
    void testGetFilteredTasks() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Task> taskPage = new PageImpl<>(Collections.singletonList(task));
        when(taskRepository.findByNameContainingAndCompleted(anyString(), anyBoolean(),
                any(Pageable.class))).thenReturn(taskPage);

        Page<Task> result = taskService.getFilteredTasks("Test", true, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(taskRepository, times(1)).findByNameContainingAndCompleted(anyString(), anyBoolean(),
                any(Pageable.class));
    }
}