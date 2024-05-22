package github.mahmoudesse.pmjava.service.implementation;

import github.mahmoudesse.pmjava.dao.entities.Task;
import github.mahmoudesse.pmjava.dao.enums.TaskStatus;
import github.mahmoudesse.pmjava.dao.repositories.IProjectRepository;
import github.mahmoudesse.pmjava.dao.repositories.ITaskRepository;
import github.mahmoudesse.pmjava.service.interfaces.ITaskService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Transactional
@Service
public class TaskServiceImplementation implements ITaskService {

  private final ITaskRepository tr;
  private final IProjectRepository pr;

  @Override
  public void add(Task t, Integer projectId) {
    t.setProject(pr.findById(projectId).get());
    tr.save(t);
  }

  @Override
  public void delete(Integer id) {
    tr.deleteById(id);
  }

  @Override
  public void update(Task t, Integer projectId) {
    t.setProject(pr.findById(projectId).get());
    tr.save(t);
  }

  @Override
  public Task findById(Integer id) throws Exception {
    Optional<Task> ot = tr.findById(id);
    if (ot.isPresent()) {
      return ot.get();
    }
    throw new Exception("TaskNotFound");
  }

  @Override
  public List<Task> getAllByProject(Integer projectId) {
    Example<Task> taskExample = Example.of(new Task(null, null, null, null, pr.findById(projectId).get()));
    return tr.findAll(taskExample);
  }

  @Override
  public Page<Task> getPage(int pageNum) {
    return tr.findAll(PageRequest.of(pageNum, 2));
  }

  @Override
  public void changeStatus(Integer id, TaskStatus status) {
    Optional<Task> ot = tr.findById(id);
    Task t = new Task();
    if (ot.isPresent()) {
      t = ot.get();
    }
    t.setStatus(status);
    tr.save(t);
  }
}
