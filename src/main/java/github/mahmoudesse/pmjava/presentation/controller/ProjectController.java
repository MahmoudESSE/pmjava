package github.mahmoudesse.pmjava.presentation.controller;

import github.mahmoudesse.pmjava.dao.entities.Project;
import github.mahmoudesse.pmjava.service.interfaces.IProjectService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class ProjectController {
  private final IProjectService ps;

  @GetMapping("/project/list")
  public String listProjects(Model model) {
    List<Project> projects = ps.getAll();

    model.addAttribute("projects", projects);

    return "project/index";
  }

  @GetMapping("/project/createForm")
  public String createForm(Model model) {
    model.addAttribute("project", new Project());
    return "project/createForm";
  }

  @PostMapping("/project/create")
  public String create(Model model, @Valid Project p, BindingResult br) {
    if (br.hasErrors()) {
      return "project/createForm";
    }

    ps.create(p);

    return "redirect:/project/list";
  }

  @GetMapping("/project/delete")
  public String delete(@RequestParam Integer id) {
    ps.delete(id);
    return "redirect:/project:list";
  }

  @GetMapping("/project/updateForm")
  public String upadteForm(Model model, @RequestParam Integer id)
      throws Exception {
    Project p = ps.findById(id);
    model.addAttribute("project", p);
    return "project/updateForm";
  }

  @PostMapping("/project/update")
  public String update(Model model, Project p, BindingResult br,
      @RequestParam Integer id) {
    if (br.hasErrors()) {
      return "project/updateForm";
    }

    p.setId(id);
    ps.update(p);
    return "redirect:/project/list";
  }
}
