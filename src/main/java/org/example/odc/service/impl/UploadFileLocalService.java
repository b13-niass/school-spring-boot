package org.example.odc.service.impl;

import org.example.odc.service.UploadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Primary
public class UploadFileLocalService implements UploadFileService {
    @Value("${file.upload-dir}")
    private String baseStoragePath;

    @Override
    public String saveFile(MultipartFile file, String folder) {
        try {
            File directory = new File(baseStoragePath + "/"+folder);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String newFilename = originalFilename + "_" + timestamp + extension;

            String filePath = directory.getAbsolutePath() + File.separator + newFilename;
            File destFile = new File(filePath);

            file.transferTo(destFile);

            return folder+"/"+newFilename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
