package github.mahmoudesse.pmjava.service.implementation;

import github.mahmoudesse.pmjava.dao.entities.Task;
import github.mahmoudesse.pmjava.dao.repositories.ITaskRepository;
import github.mahmoudesse.pmjava.service.interfaces.ITaskService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Data
@Transactional
@Service
public class TaskServiceImplementation implements ITaskService {

  private final ITaskRepository tr;

  @Override
  public void add(Task t) {
    tr.save(t);
  }

  @Override
  public void delete(Integer id) {
    tr.deleteById(id);
  }

  @Override
  public void update(Task t) {
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
  public List<Task> getAll() {
    return tr.findAll();
  }

  @Override
  public Page<Task> getPage(int pageNum) {
    return tr.findAll(PageRequest.of(pageNum, 2));
  }
}
