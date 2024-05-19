package github.mahmoudesse.pmjava.service.interfaces;

import github.mahmoudesse.pmjava.dao.entities.Project;
import java.util.List;
import org.springframework.data.domain.Page;

public interface IProjectService {
  public void create(Project p);

  public void delete(Integer id);

  public void update(Project p);

  public Project findById(Integer id) throws Exception;

  public List<Project> getAll();

  public Page<Project> getPage(int pageNum);
}
