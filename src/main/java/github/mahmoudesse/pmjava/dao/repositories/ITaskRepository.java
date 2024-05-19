package github.mahmoudesse.pmjava.dao.repositories;

import github.mahmoudesse.pmjava.dao.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Integer> {
}
