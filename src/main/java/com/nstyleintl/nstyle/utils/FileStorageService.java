package com.nstyleintl.nstyle.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.storage.image-path}")
    private String imagePath;

    @Value("${file.storage.cv-path}")
    private String cvPath;

    public String saveBase64File(String base64Data, String fileType) throws IOException {
        String directoryPath = "image".equalsIgnoreCase(fileType) ? imagePath : cvPath;
        String fileExtension = "image".equalsIgnoreCase(fileType) ? "jpg" : "pdf";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IOException("Could not create directory: " + directoryPath);
            }
        }

        String fileName = UUID.randomUUID() + "." + fileExtension;
        File file = new File(directoryPath + fileName);

        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }

        return file.getAbsolutePath();
    }
}

