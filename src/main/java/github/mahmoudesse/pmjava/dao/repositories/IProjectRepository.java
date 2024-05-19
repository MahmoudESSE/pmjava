package github.mahmoudesse.pmjava.dao.repositories;

import github.mahmoudesse.pmjava.dao.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Integer> {
}
