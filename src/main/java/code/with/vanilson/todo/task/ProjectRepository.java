package code.with.vanilson.todo.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * TaskRepository
 *
 * @author vamuhong
 * @version 1.0
 * @since 2025-02-03
 */
public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
