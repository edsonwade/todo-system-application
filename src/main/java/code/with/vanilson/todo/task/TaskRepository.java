package code.with.vanilson.todo.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * TaskRepository
 *
 * @author vamuhong
 * @version 1.0
 * @since 2025-02-03
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByNameContaining(String name, Pageable pageable);
    Page<Task> findByCompleted(Boolean completed, Pageable pageable);
    Page<Task> findByNameContainingAndCompleted(String name, Boolean completed, Pageable pageable);
}
