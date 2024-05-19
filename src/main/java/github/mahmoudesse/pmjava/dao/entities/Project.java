package github.mahmoudesse.pmjava.dao.entities;

import github.mahmoudesse.pmjava.dao.enums.ProjectPriority;
import github.mahmoudesse.pmjava.dao.enums.ProjectStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty
  private String name;
  private String description;
  @Enumerated(EnumType.STRING)
  private ProjectStatus status;
  @Enumerated(EnumType.STRING)
  private ProjectPriority priority;

  @OneToMany(mappedBy = "project")
  private List<Task> tasks;
}
