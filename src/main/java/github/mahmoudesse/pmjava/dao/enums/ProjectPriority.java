package github.mahmoudesse.pmjava.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProjectPriority {
  LOW("LOW"),
  MEDIUM("MEDIUM"),
  HIGH("HIGH");

  private final String priority;
}
