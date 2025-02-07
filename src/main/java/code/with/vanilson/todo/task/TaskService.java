package code.with.vanilson.todo.task;

import code.with.vanilson.todo.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Page<Task> getTasks(Pageable pageable) {
        log.info("Getting all tasks");
        return taskRepository.findAll(pageable);
    }

    public Task getTaskById(Long id) {
        log.info("Getting task with id {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TASK_WITH_ID + id + NOT_FOUND));
    }

    public Task saveTask(Task task) {
        log.info("Saving task");

        // Save the project if it is not already saved
        if (task.getProject() != null && task.getProject().getId() == null) {
            projectRepository.save(task.getProject());
        }

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        log.info("Updating task with id {}", id);
        return taskRepository.findById(id).map(task -> {
            task.setName(taskDetails.getName());
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.isCompleted());
            task.setDueDate(taskDetails.getDueDate());

            // Save the project if it is not already saved
            if (taskDetails.getProject() != null && taskDetails.getProject().getId() == null) {
                projectRepository.save(taskDetails.getProject());
            }
            task.setProject(taskDetails.getProject());

            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException(TASK_WITH_ID + id + NOT_FOUND));
    }

    public void deleteTask(Long id) {
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