package code.with.vanilson.todo.task;

import code.with.vanilson.todo.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * TaskService
 *
 * @version 1.0
 * @since 2025-02-03
 */
@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    public static final String TASK_WITH_ID = "Task with id ";
    public static final String NOT_FOUND = " not found";
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> getTasks(Pageable pageable) {
        log.info("Getting all tasks");
        return taskRepository.findAll(pageable);
    }

    public Task getTaskById(UUID id) {
        log.info("Getting task with id {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TASK_WITH_ID + id + NOT_FOUND));
    }

    public Task saveTask(Task task) {
        log.info("Saving task");
        return taskRepository.save(task);
    }

    public Task updateTask(UUID id, Task taskDetails) {
        log.info("Updating task with id {}", id);
        return taskRepository.findById(id).map(task -> {
            task.setName(taskDetails.getName());
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.isCompleted());
            task.setUpdatedAt(LocalDateTime.now());
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException(TASK_WITH_ID + id + NOT_FOUND));
    }

    public void deleteTask(UUID id) {
        log.info("Deleting task with id {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TASK_WITH_ID + id + NOT_FOUND));
        taskRepository.delete(task);
    }

    public Page<Task> getFilteredTasks(String name, Boolean completed, Pageable pageable) {
        log.info("Getting filtered tasks");
        if (name != null && completed != null) {
            return taskRepository.findByNameContainingAndCompleted(name, completed, pageable);
        } else if (name != null) {
            return taskRepository.findByNameContaining(name, pageable);
        } else if (completed != null) {
            return taskRepository.findByCompleted(completed, pageable);
        } else {
            return taskRepository.findAll(pageable);
        }
    }
}