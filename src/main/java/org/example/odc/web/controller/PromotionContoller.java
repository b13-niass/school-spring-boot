package org.example.odc.web.controller;

import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;
import org.example.odc.web.dto.request.PromoUpdateRefDTORequest;
import org.example.odc.web.dto.request.PromoUpdateStatusDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface PromotionContoller {
    ResponseEntity<PromoDtoResponse> save(PromoDTORequest request);
    ResponseEntity<PromoDtoResponse> update(PromoUpdateDTORequest request,Long id);
    ResponseEntity<PromoDtoResponse> updateReferentiel(PromoUpdateRefDTORequest request, Long id);
    ResponseEntity<PromoDtoResponse> updateStatus(PromoUpdateStatusDTORequest request, Long id);
    ResponseEntity<Collection<PromoDtoResponse>> getAll();
    ResponseEntity<PromoDtoResponse> getEncours();
    ResponseEntity<Collection<ReferentielDtoResponse>> getReferentiels(Long id);
}
