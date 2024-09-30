package org.example.odc.web.controller.impl;

import jakarta.validation.Valid;
import org.example.odc.service.ApprenantService;
import org.example.odc.service.ParseApprenantCsvService;
import org.example.odc.web.controller.ApprenantController;
import org.example.odc.web.dto.request.ApprenantDTORequest;
import org.example.odc.web.dto.request.ApprenantImportDTORequest;
import org.example.odc.web.dto.response.ApprenantDtoResponse;
import org.example.odc.web.dto.response.ApprenantImportDtoResponse;
import org.example.odc.web.dto.response.ApprenantResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/apprenants")
public class ApprenantControllerImpl implements ApprenantController {

    private final ApprenantService apprenantService;
    private final ParseApprenantCsvService parseCsvService;

    public ApprenantControllerImpl(ApprenantService apprenantService,@Qualifier("apprenantCsv") ParseApprenantCsvService parseCsvService) {
        this.apprenantService = apprenantService;
        this.parseCsvService = parseCsvService;
    }

    @Override
    @PostMapping(consumes =MediaType.MULTIPART_FORM_DATA_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApprenantResponse> create(@RequestBody @Valid @ModelAttribute ApprenantDTORequest apprenant) {
        return ResponseEntity.ok(apprenantService.create(apprenant));
    }

    @Override
    @PostMapping(value = "/import",consumes =MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApprenantImportDtoResponse> importApprenants(@RequestParam("apprenants") MultipartFile multipartFile) {
        List<ApprenantImportDTORequest> appDTORequests = parseCsvService.parseCsvFile(multipartFile);
        ApprenantImportDtoResponse appDTOResponses = this.apprenantService.importApprenants(appDTORequests);
        return ResponseEntity.ok(appDTOResponses);
    }


}
