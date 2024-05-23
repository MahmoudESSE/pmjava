package github.mahmoudesse.pmjava.service.interfaces;

import github.mahmoudesse.pmjava.dao.entities.Task;
import github.mahmoudesse.pmjava.dao.enums.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITaskService {
  void add(Task t, Integer projectId);

  void delete(Integer id);

  void update(Task t, Integer projectId);

  Task findById(Integer id) throws Exception;

  List<Task> getAllByProject(Integer projectId) throws Exception;

  List<Task> getAllByStatus(Integer projectId, TaskStatus status) throws Exception;

  Page<Task> getPage(int pageNum);

  void changeStatus(Integer id, TaskStatus status);
}
