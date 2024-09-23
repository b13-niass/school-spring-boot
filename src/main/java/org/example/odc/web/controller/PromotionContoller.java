package org.example.odc.web.controller;

import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.springframework.http.ResponseEntity;

public interface PromotionContoller {
    ResponseEntity<PromoDtoResponse> save(PromoDTORequest request);
}
