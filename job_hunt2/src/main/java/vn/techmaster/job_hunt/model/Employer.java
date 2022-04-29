package vn.techmaster.job_hunt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employer {
  private String id;
  private String name;
  private String logo_path;
  private String website;
  private String email;

  public String getPhotosImagePath() {
    if (logo_path == null || id == null) return null;

    return "/employer-photos/" + id + "/" + logo_path;
  }
}
