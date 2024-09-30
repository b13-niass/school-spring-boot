package org.example.odc.service;

import org.example.odc.data.entity.Apprenant;
import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.request.ApprenantImportDTORequest;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.example.odc.web.dto.response.ApprenantImportDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApprenantService {
    ApprenantResponse create(ApprenantDTORequest apprenant);
    ApprenantImportDtoResponse importApprenants(List<ApprenantImportDTORequest> apprenantImportDTORequest);
}
