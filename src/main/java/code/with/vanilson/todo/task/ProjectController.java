package code.with.vanilson.todo.task;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * ProjectController
 *
 * @author vamuhong
 * @version 1.0
 * @since 2025-02-04
 */
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
// Allow requests from the Angular app running on localhost port 4200 to access the API endpoints in this controller
// class (ProjectController) in the backend application (TodoApp).
// The allowedHeaders attribute is set to * to allow any headers to be sent in the request.
@RestController
@RequestMapping(path = "/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Add the methods for the RESTful API endpoints here
    @GetMapping
    public ResponseEntity<Iterable<Project>> getProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID id) {
        var project = Optional.ofNullable(projectService.getProjectById(id));
        return project
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody @Valid Project project) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable UUID id, @RequestBody @Valid Project project) {
        var projects = Optional.ofNullable(projectService.getProjectById(id));
        return projects.map(value -> ResponseEntity
                        .ok(projectService.updateProject(value.getId(), project)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}