package app.cybergalaxy.safir_gallery.service.impl;

import app.cybergalaxy.safir_gallery.exception.MyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    private final String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new MyException("File not selected!");
        }

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileExtension = getFileExtension(file.getOriginalFilename());
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            String filePath = uploadDir + uniqueFileName;

            file.transferTo(new File(filePath));

            return uniqueFileName;
        } catch (IOException e) {
            throw new MyException("There is a problem in upload operation: " + e.getMessage());
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex);
        }
        return "";
    }
}