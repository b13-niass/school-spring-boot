package org.example.odc.web.controller.impl;

import jakarta.validation.Valid;
import org.example.odc.service.PromoService;
import org.example.odc.web.controller.PromotionContoller;
import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;
import org.example.odc.web.dto.request.PromoUpdateRefDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
public class PromotionContollerImpl implements PromotionContoller {

    private final PromoService promoService;

    public PromotionContollerImpl(PromoService promoService) {
        this.promoService = promoService;
    }

    @Override
    @PostMapping
    public ResponseEntity<PromoDtoResponse> save(@RequestBody @Valid PromoDTORequest request) {
        PromoDtoResponse promoDtoResponse = this.promoService.save(request);

        return ResponseEntity.ok(promoDtoResponse);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<PromoDtoResponse> update(@RequestBody @Valid PromoUpdateDTORequest request,@PathVariable Long id) {
        PromoDtoResponse promoDtoResponse = this.promoService.update(request, id);
        return ResponseEntity.ok(promoDtoResponse);
    }

    @Override
    @PatchMapping("/{id}/referentiels")
    public ResponseEntity<PromoUpdateRefDTORequest> updateReferentiel(@RequestBody @Valid PromoUpdateRefDTORequest request, @PathVariable Long id) {
//        request.setPromoId(id);
        return ResponseEntity.ok(request);
    }
}
