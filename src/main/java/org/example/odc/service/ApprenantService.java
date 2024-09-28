package org.example.odc.service;

import org.example.odc.data.entity.Apprenant;
import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;

public interface ApprenantService {
    ApprenantResponse create(ApprenantDTORequest apprenant);
}
