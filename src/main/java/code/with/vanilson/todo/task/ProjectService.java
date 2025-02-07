package code.with.vanilson.todo.task;

import code.with.vanilson.todo.exception.ResourceNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * ProjectService
 *
 * @author vamuhong
 * @version 1.0
 * @since 2025-02-03
 */
@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    public static final String PROJECT_WITH_ID = "Project with id ";
    public static final String NOT_FOUND = " not found";
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Iterable<Project> getProjects() {
        log.info("Getting all projects");
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        log.info("Getting project with id {}", id);
        return projectRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PROJECT_WITH_ID + id + NOT_FOUND));
    }

    public Project createProject(@NotNull Project project) {
        log.info("Creating project with name {}", project.getName());
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, @NotNull Project project) {
        Project existingProject = projectRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PROJECT_WITH_ID + id + NOT_FOUND));
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        log.info("Updating project with id {}", id);
        return projectRepository.save(existingProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            log.error("Project with id {} not found", id);
            throw new ResourceNotFoundException(PROJECT_WITH_ID + id + NOT_FOUND);
        }
        log.info("Deleting project with id {}", id);
        projectRepository.deleteById(id);

    }

}