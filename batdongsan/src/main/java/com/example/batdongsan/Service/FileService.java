package com.example.batdongsan.Service;

import com.example.batdongsan.Entity.User;
import com.example.batdongsan.Repository.UserRepository;
import com.example.batdongsan.exception.BadRequestException;
import com.example.batdongsan.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final Path rootDir = Paths.get("user-photos");

    @Autowired
    private UserRepository userRepository;

    public FileService() {
        createFolder(rootDir.toString());
    }

    public void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            // Tạo ra folder
            folder.mkdirs();
        }
    }


    public void uploadFile(int id, MultipartFile file) {

        String fileName = file.getOriginalFilename();
        // Check file
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên File không hợp lệ");
        }
        //Kiểm tra type file
        String fileExtension = getFileExtension(fileName);
        if (checkFileExtension(fileExtension) == false) {
            throw new BadRequestException("File không hợp lệ");
        }
        if ((double) file.getSize() / 1_000_000 > 2) {
            throw new BadRequestException("File không vượt quá 2MB");
        }

        // Tạo folder tương ứng vs user id
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        String genarateFileName = UUID.randomUUID() + "." + fileExtension;
        // Tạo path tương ứng
        File serverFile = new File(userDir.toString() + "/" + genarateFileName);

        try {
            // sử buffed để lưu trữ tạm thời
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

            //Ghi từ bộ đệm
            stream.write(file.getBytes());
            stream.close();
            // Set lại avatar
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }

    }

    // TODO : checksize
    private String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf + 1);
    }

    private boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = Arrays.asList("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }

}
