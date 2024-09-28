package org.example.odc.service.impl;

import org.example.odc.service.UploadFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadFileCloudinaryService implements UploadFileService {
    @Value("${file.upload-dir}")
    private String baseStoragePath;

    @Override
    public String saveFile(MultipartFile file, String folder) {
//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
