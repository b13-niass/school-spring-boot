package org.example.odc.service;

import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;

public interface PromoService {
    PromoDtoResponse save(PromoDTORequest request);
    PromoDtoResponse update(PromoUpdateDTORequest request, Long id);
}
