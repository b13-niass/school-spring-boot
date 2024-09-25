package org.example.odc.service;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;
import org.example.odc.web.dto.request.PromoUpdateRefDTORequest;
import org.example.odc.web.dto.request.PromoUpdateStatusDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.example.odc.web.dto.response.PromoStatsDTOResponse;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface PromoService {
    PromoDtoResponse save(PromoDTORequest request);
    PromoDtoResponse update(PromoUpdateDTORequest request, Long id);
    PromoDtoResponse updateReferentiel(PromoUpdateRefDTORequest request);
    PromoDtoResponse updateStatus(PromoUpdateStatusDTORequest request);
    List<PromoDtoResponse> getAll();
    PromoDtoResponse getEncours();
    List<ReferentielDtoResponse> getActiveReferentielsForPromo(Long promoId);
    PromoStatsDTOResponse getPromoStats(Long promoId);
}
