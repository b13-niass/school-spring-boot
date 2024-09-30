package org.example.odc.service;

import org.example.odc.web.dto.request.ApprenantImportDTORequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ParseApprenantCsvService {
    List<ApprenantImportDTORequest> parseCsvFile(MultipartFile csvFile);
    MultipartFile loadFile(String filePath);
}
