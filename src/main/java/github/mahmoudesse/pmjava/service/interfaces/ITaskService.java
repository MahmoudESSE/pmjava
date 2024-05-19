package github.mahmoudesse.pmjava.service.interfaces;

import github.mahmoudesse.pmjava.dao.entities.Task;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ITaskService {
  public void add(Task t);

  public void delete(Integer id);

  public void update(Task t);

  public Task findById(Integer id) throws Exception;

  public List<Task> getAll();

  public Page<Task> getPage(int pageNum);
}
