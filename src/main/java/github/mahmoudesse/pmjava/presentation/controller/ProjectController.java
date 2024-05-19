package github.mahmoudesse.pmjava.presentation.controller;

import github.mahmoudesse.pmjava.dao.entities.Project;
import github.mahmoudesse.pmjava.service.interfaces.IProjectService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RequestMapping("/project")
@Controller
public class ProjectController {
  private final IProjectService ps;

  @GetMapping("/")
  public String indexProject() {
    return "projects/index";
  }

  @GetMapping("/listProjects")
  public String listProjects(Model model) {
    List<Project> projects = ps.getAll();

    model.addAttribute("projects", projects);

    return "index";
  }

  @GetMapping("/createProjectForm")
  public String createProjectForm(Model model) {
    model.addAttribute("project", new Project());
    return "createProjectForm";
  }

  @PostMapping("/createProject")
  public String createProject(Model model, @Valid Project p, BindingResult br) {
    if (br.hasErrors()) {
      return "createProjectForm";
    }

    ps.create(p);

    return "redirect:/listProjects";
  }

  @GetMapping("/deleteProject")
  public String deleteProject(@RequestParam Integer id) {
    ps.delete(id);
    return "redirect:/listProjects";
  }

  @GetMapping("/updateProjectForm")
  public String upadteProjectForm(Model model, @RequestParam Integer id)
      throws Exception {
    Project p = ps.findById(id);
    model.addAttribute("project", p);
    return "updateProjectForm";
  }

  @PostMapping("/updateProject")
  public String updateProject(Model model, Project p, BindingResult br,
      @RequestParam Integer id) {
    if (br.hasErrors()) {
      return "updateProjectForm";
    }

    p.setId(id);
    ps.update(p);
    return "redirect:/listProjects";
  }
}
