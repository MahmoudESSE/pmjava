package github.mahmoudesse.pmjava.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
  TODO("TODO"),
  IN_PROGRESS("IN_PROGRESS"),
  COMPLETED("COMPLETED");

  private final String status;
}
