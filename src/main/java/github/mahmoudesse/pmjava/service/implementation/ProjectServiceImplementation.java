package github.mahmoudesse.pmjava.service.implementation;

import github.mahmoudesse.pmjava.dao.entities.Project;
import github.mahmoudesse.pmjava.dao.repositories.IProjectRepository;
import github.mahmoudesse.pmjava.service.interfaces.IProjectService;
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
public class ProjectServiceImplementation implements IProjectService {

  private final IProjectRepository pr;

  @Override
  public void create(Project p) {
    pr.save(p);
  }

  @Override
  public void delete(Integer id) {
    pr.deleteById(id);
  }

  @Override
  public void update(Project p) {
    pr.save(p);
  }

  @Override
  public Project findById(Integer id) throws Exception {
    Optional<Project> op = pr.findById(id);
    if (op.isPresent()) {
      return op.get();
    }
    throw new Exception("ProjectNotFound");
  }

  @Override
  public List<Project> getAll() {
    return pr.findAll();
  }

  @Override
  public Page<Project> getPage(int pageNum) {
    return pr.findAll(PageRequest.of(pageNum, 2));
  }
}
