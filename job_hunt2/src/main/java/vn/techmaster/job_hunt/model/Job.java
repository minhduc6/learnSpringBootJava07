package vn.techmaster.job_hunt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
  private String id;
  private String emp_id;
  private String title;
  private String description;
  private City city;
}
