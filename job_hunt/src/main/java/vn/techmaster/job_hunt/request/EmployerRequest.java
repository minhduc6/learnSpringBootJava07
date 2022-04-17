package vn.techmaster.job_hunt.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerRequest {
    private String name;
    private MultipartFile logo_path;
    private String website;
    private String email;
}
