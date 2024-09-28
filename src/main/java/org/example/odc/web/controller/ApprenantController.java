package org.example.odc.web.controller;

import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.springframework.http.ResponseEntity;

public interface ApprenantController {
    ResponseEntity<ApprenantResponse> create(ApprenantDTORequest apprenant);
}
