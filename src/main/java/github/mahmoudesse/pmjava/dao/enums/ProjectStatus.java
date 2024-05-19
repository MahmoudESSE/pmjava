package github.mahmoudesse.pmjava.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProjectStatus {
  PLANNED("PLANNED"),
  IN_PROGRESS("IN_PROGRESS"),
  COMPLETED("COMPLETED"),
  ON_HOLD("ON_HOLD");

  private final String status;
}
