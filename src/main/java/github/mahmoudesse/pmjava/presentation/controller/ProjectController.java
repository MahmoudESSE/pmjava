package github.mahmoudesse.pmjava.presentation.controller;

import github.mahmoudesse.pmjava.dao.entities.Project;
import github.mahmoudesse.pmjava.service.interfaces.IProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/projects")
@Controller
public class ProjectController {
  private final IProjectService ps;

  @GetMapping("/listProjects")
  public String listProjects(Model model) {
    List<Project> projects = ps.getAll();

    model.addAttribute("projects", projects);

    return "projects/index";
  }

  @GetMapping("/createProjectForm")
  public String createProjectForm(Model model) {
    model.addAttribute("project", new Project());
    return "projects/createProjectForm";
  }

  @PostMapping("/createProject")
  public String createProject(Model model, @Valid Project p, BindingResult br) {
    if (br.hasErrors()) {
      return "projects/createProjectForm";
    }

    ps.create(p);

    return "redirect:/projects/listProjects";
  }

  @GetMapping("/deleteProject")
  public String deleteProject(@RequestParam Integer id) {
    ps.delete(id);
    return "redirect:/projects/listProjects";
  }

  @GetMapping("/updateProjectForm")
  public String upadteProjectForm(Model model, @RequestParam Integer id)
      throws Exception {
    Project p = ps.findById(id);
    model.addAttribute("project", p);
    return "projects/updateProjectForm";
  }

  @PostMapping("/updateProject")
  public String updateProject(Model model, Project p, BindingResult br,
                              @RequestParam Integer id) {
    if (br.hasErrors()) {
      return "projects/updateProjectForm";
    }

    p.setId(id);
    ps.update(p);
    return "redirect:/projects/listProjects";
  }

  @GetMapping("/searchProject")
  public String searchProject(@RequestParam Integer projectId, Model model) {
    try {
      Project task = ps.findById(projectId);
      if (task != null) {
        List<Project> tasks = new ArrayList<>();
        tasks.add(task);
        model.addAttribute("projects", tasks);
      } else {
        model.addAttribute("errorMessage", "Project Not Found");
      }
    } catch (Exception e) {
      model.addAttribute("errorMessage", "Error");
    }
    return "projects/index";
  }
}
