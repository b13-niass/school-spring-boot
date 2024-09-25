package org.example.odc.web.controller.impl;

import jakarta.validation.Valid;
import org.example.odc.service.PromoService;
import org.example.odc.web.controller.PromotionContoller;
import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;
import org.example.odc.web.dto.request.PromoUpdateRefDTORequest;
import org.example.odc.web.dto.request.PromoUpdateStatusDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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
    public ResponseEntity<PromoDtoResponse> update(@RequestBody @Valid PromoUpdateDTORequest request, @PathVariable Long id) {
        PromoDtoResponse promoDtoResponse = this.promoService.update(request, id);
        return ResponseEntity.ok(promoDtoResponse);
    }

    @Override
    @PatchMapping("/{id}/referentiels")
    public ResponseEntity<PromoDtoResponse> updateReferentiel(@RequestBody @Valid PromoUpdateRefDTORequest request, @PathVariable Long id) {
        request.setPromoId(id);
        PromoDtoResponse promoDtoResponse = this.promoService.updateReferentiel(request);

        return ResponseEntity.ok(promoDtoResponse);
    }

    @Override
    @PatchMapping("{id}/etat")
    public ResponseEntity<PromoDtoResponse> updateStatus(@RequestBody @Valid PromoUpdateStatusDTORequest request, Long id) {
        request.setPromoId(id);
        PromoDtoResponse promoDtoResponse = this.promoService.updateStatus(request);
        return ResponseEntity.ok(promoDtoResponse);
    }

    @GetMapping("")
    public ResponseEntity<Collection<PromoDtoResponse>> getAll() {
        Collection<PromoDtoResponse>  promos= this.promoService.getAll();
        return ResponseEntity.ok(promos);
    }

    @GetMapping("encours")
    public ResponseEntity<PromoDtoResponse> getEncours() {
        return ResponseEntity.ok(this.promoService.getEncours());
    }
}
