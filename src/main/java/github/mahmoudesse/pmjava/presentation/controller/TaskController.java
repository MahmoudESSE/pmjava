package github.mahmoudesse.pmjava.presentation.controller;

import github.mahmoudesse.pmjava.dao.entities.Task;
import github.mahmoudesse.pmjava.dao.enums.TaskStatus;
import github.mahmoudesse.pmjava.service.interfaces.ITaskService;
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
@RequestMapping("/tasks")
@Controller
public class TaskController {
  private final ITaskService ts;

  @GetMapping("/listTasks")
  public String listTasks(Model model, @RequestParam Integer projectId) {
    List<Task> tasks = ts.getAllByProject(projectId);
    model.addAttribute("projectId", projectId);
    model.addAttribute("tasks", tasks);
    model.addAttribute("taskStatuses", TaskStatus.values());
    return "tasks/index";
  }

  @GetMapping("/createTaskForm")
  public String createTaskForm(Model model, @RequestParam Integer projectId) {
    model.addAttribute("projectId", projectId);
    model.addAttribute("task", new Task());
    return "tasks/createTaskForm";
  }

  @PostMapping("/createTask")
  public String createTask(@Valid Task t, @RequestParam Integer projectId, BindingResult br) {
    if (br.hasErrors()) {
      return "tasks/createTaskForm";
    }
    ts.add(t, projectId);

    return "redirect:/tasks/listTasks?projectId=" + projectId;
  }

  @GetMapping("/deleteTask")
  public String deleteTask(@RequestParam Integer taskId, @RequestParam Integer projectId) {
    ts.delete(taskId);
    return "redirect:/tasks/listTasks?projectId=" + projectId;
  }

  @GetMapping("/updateTaskForm")
  public String updateTaskForm(Model model, @RequestParam Integer taskId, @RequestParam Integer projectId) throws Exception {
    Task t = ts.findById(taskId);
    model.addAttribute("projectId", projectId);
    model.addAttribute("task", t);
    return "tasks/updateTaskForm";
  }

  @PostMapping("/updateTask")
  public String updateTaskForm(@Valid Task t, BindingResult br, @RequestParam Integer taskId, @RequestParam Integer projectId) {
    if (br.hasErrors()) {
      return "tasks/updateTaskForm";
    }
    t.setId(taskId);
    ts.update(t, projectId);
    return "redirect:/tasks/listTasks?projectId=" + projectId;
  }

  @GetMapping("/searchTask")
  public String searchTask(@RequestParam Integer taskId, Model model) {
    try {
      Task task = ts.findById(taskId);
      if (task != null) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        model.addAttribute("tasks", tasks);
      } else {
        model.addAttribute("errorMessage", "Task Not Found");
      }
    } catch (Exception e) {
      model.addAttribute("errorMessage", "Error");
    }
    return "tasks/index";
  }

  @GetMapping("/changeStatus")
  public String saveStatusChanges(@RequestParam Integer taskId, @RequestParam TaskStatus status) throws Exception {
    ts.changeStatus(taskId, status);
    Task task = ts.findById(taskId);
    return "redirect:/tasks/listTasks?projectId=" + task.getProject().getId();
  }
}
