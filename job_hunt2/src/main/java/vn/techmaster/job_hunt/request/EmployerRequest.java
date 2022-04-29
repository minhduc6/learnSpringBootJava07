package vn.techmaster.job_hunt.request;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

public record EmployerRequest(
    String id,

    String name,

    String website,

    String email) {
}