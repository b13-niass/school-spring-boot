package org.example.odc.web.controller.impl;

import jakarta.validation.Valid;
import org.example.odc.data.entity.Apprenant;
import org.example.odc.service.ApprenantService;
import org.example.odc.web.controller.ApprenantController;
import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apprenants")
public class ApprenantControllerImpl implements ApprenantController {

    private ApprenantService apprenantService;

    public ApprenantControllerImpl(ApprenantService apprenantService) {
        this.apprenantService = apprenantService;
    }

    @Override
    @PostMapping(consumes =MediaType.MULTIPART_FORM_DATA_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApprenantResponse> create(@RequestBody @Valid @ModelAttribute ApprenantDTORequest apprenant) {
        return ResponseEntity.ok(apprenantService.create(apprenant));
    }
}
