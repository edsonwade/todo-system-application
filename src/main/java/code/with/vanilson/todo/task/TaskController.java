package code.with.vanilson.todo.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * TaskController
 *
 * @author vamuhong
 * @version 1.0
 * @since 2025-02-04
 */
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
// Allow requests from the Angular app running on localhost port 4200 to access the API endpoints in this controller
// class (TaskController) in the backend application (TodoApp).
// The allowedHeaders attribute is set to * to allow any headers to be sent in the request.
@RestController
@RequestMapping(path = "/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Add the methods for the RESTful API endpoints here
    @GetMapping
    public ResponseEntity<Page<Task>> getTasks(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasks(pageable));
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<Page<Task>> getTasks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean completed,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(taskService.getFilteredTasks(name, completed, pageable));
    }

    @GetMapping(value = "/id")
    public ResponseEntity<Task> getTaskById(UUID id) {
        var task = Optional.ofNullable(taskService.getTaskById(id));
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create-task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(task));
    }

    @PutMapping(value = "/update-task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task task) {
        var tasks = Optional.ofNullable(taskService.getTaskById(id));
        return tasks.map(value -> ResponseEntity.ok(taskService.updateTask(value.getId(), task)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/delete-task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}