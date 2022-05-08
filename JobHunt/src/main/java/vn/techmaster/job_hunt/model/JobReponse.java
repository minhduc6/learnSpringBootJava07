package vn.techmaster.job_hunt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobReponse {
    private List<Job> jobs;
    private int totalPage;
}
