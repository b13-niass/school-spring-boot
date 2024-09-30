package org.example.odc.web.controller;

import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.response.ApprenantImportDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApprenantController {
    ResponseEntity<ApprenantResponse> create(ApprenantDTORequest apprenant);
    ResponseEntity<ApprenantImportDtoResponse> importApprenants(MultipartFile multipartFile);
}
