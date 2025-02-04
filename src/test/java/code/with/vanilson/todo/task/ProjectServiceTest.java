package code.with.vanilson.todo.task;

import code.with.vanilson.todo.exception.ResourceNotFoundException;;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private UUID projectId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectId = UUID.randomUUID();
        project = new Project();
        project.setId(projectId);
        project.setName("Test Project");
        project.setDescription("Test Description");
    }

    @Test
    void testGetProjects() {
        when(projectRepository.findAll()).thenReturn(Collections.singletonList(project));

        Iterable<Project> result = projectService.getProjects();

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectById() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(projectId);

        assertNotNull(result);
        assertEquals(projectId, result.getId());
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    void testGetProjectByIdNotFound() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> projectService.getProjectById(projectId));
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    void testCreateProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project result = projectService.createProject(project);

        assertNotNull(result);
        assertEquals(projectId, result.getId());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testUpdateProject() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project updatedProject = new Project();
        updatedProject.setName("Updated Project");
        updatedProject.setDescription("Updated Description");

        Project result = projectService.updateProject(projectId, updatedProject);

        assertNotNull(result);
        assertEquals("Updated Project", result.getName());
        assertEquals("Updated Description", result.getDescription());
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testUpdateProjectNotFound() {
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        Project updatedProject = new Project();
        updatedProject.setName("Updated Project");
        updatedProject.setDescription("Updated Description");

        assertThrows(ResourceNotFoundException.class, () -> projectService.updateProject(projectId, updatedProject));
        verify(projectRepository, times(1)).findById(projectId);
        verify(projectRepository, times(0)).save(any(Project.class));
    }

    @Test
    void testDeleteProject() {
        when(projectRepository.existsById(projectId)).thenReturn(true);

        projectService.deleteProject(projectId);

        verify(projectRepository, times(1)).existsById(projectId);
        verify(projectRepository, times(1)).deleteById(projectId);
    }

    @Test
    void testDeleteProjectNotFound() {
        when(projectRepository.existsById(projectId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> projectService.deleteProject(projectId));
        verify(projectRepository, times(1)).existsById(projectId);
        verify(projectRepository, times(0)).deleteById(projectId);
    }
}