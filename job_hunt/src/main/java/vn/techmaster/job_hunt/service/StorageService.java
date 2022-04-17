package vn.techmaster.job_hunt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.job_hunt.exception.StorageException;
import vn.techmaster.job_hunt.model.Employer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {
    @Value("${upload.path}")
    private String path;

    // id của EmployerID
    public void uploadFile(MultipartFile file,Employer employer) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        String extension = getFileExtension(file.getOriginalFilename());
        String newFileName = path + employer.getId() + "." + extension;
        employer.setLogo_path(employer.getId() + "." + extension);
        try {
            var is = file.getInputStream();
            Files.copy(is, Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            var msg = String.format("Failed to store file %s", newFileName);
            throw new StorageException(msg, e);
        }
    }
    private String getFileExtension(String filename){
        int postOfDot = filename.lastIndexOf(".");
        if(postOfDot >= 0){
            return  filename.substring(filename.lastIndexOf(".") + 1);
        }else{
            return  null;
        }
    }
}