package org.example.odc.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    String saveFile(MultipartFile file, String folder);
}
