package github.mahmoudesse.pmjava.presentation.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class HomeController {

  @GetMapping("/")
  public String listProjects(Model model) {

    return "index";
  }
}
