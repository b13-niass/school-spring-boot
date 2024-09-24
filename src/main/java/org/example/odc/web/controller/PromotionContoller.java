package org.example.odc.web.controller;

import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;
import org.example.odc.web.dto.request.PromoUpdateRefDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.springframework.http.ResponseEntity;

public interface PromotionContoller {
    ResponseEntity<PromoDtoResponse> save(PromoDTORequest request);
    ResponseEntity<PromoDtoResponse> update(PromoUpdateDTORequest request,Long id);
    ResponseEntity<PromoUpdateRefDTORequest> updateReferentiel(PromoUpdateRefDTORequest request, Long id);

}
