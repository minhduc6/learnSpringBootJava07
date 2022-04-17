package vn.techmasterr.jobhunt.request;

import lombok.*;
import vn.techmasterr.jobhunt.model.Job;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApplicantRequest {
    private String name;
    private String email;
    private String phone;
    private String skills;
    private List<Job> listJob;
}
