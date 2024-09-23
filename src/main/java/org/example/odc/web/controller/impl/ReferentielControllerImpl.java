package org.example.odc.web.controller.impl;

import jakarta.validation.Valid;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.service.ReferentielService;
import org.example.odc.web.controller.ReferentielController;
import org.example.odc.web.dto.request.ReferentielDTORequest;
import org.example.odc.web.dto.request.ReferentielUpdateDTORequest;
import org.example.odc.web.dto.request.mapper.ReferentielRequestMapper;
import org.example.odc.web.dto.request.mapper.ReferentielUpdateRequestMapper;
import org.example.odc.web.dto.response.RefOnlyDtoResponse;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/referentiels")
public class ReferentielControllerImpl  implements ReferentielController {

    private final ReferentielService service;
    private final ReferentielRequestMapper referentielRequestMapper;
    private final ReferentielUpdateRequestMapper updateRequestMapper;

    public ReferentielControllerImpl(ReferentielService service, ReferentielRequestMapper referentielRequestMapper,
     ReferentielUpdateRequestMapper updateRequestMapper) {
        this.service = service;
        this.referentielRequestMapper = referentielRequestMapper;
        this.updateRequestMapper = updateRequestMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id, @RequestParam(required = false) String filter) {
       return this.service.getById(id, filter);
    }

    @GetMapping("")
    @Override
    public Collection<RefOnlyDtoResponse> getAll(@RequestParam(required = false) ReferentielStatusEnum filter) {
        return this.service.getAll(filter);
    }

    @PostMapping("")
    @Override
    public ReferentielDtoResponse save(@RequestBody @Valid ReferentielDTORequest request) {
        Referentiel referentiel = referentielRequestMapper.toEntity(request);

        return this.service.save(referentiel);
    }

    @PatchMapping("/{id}")
    @Override
    public ReferentielDtoResponse update(@RequestBody @Valid ReferentielUpdateDTORequest referentiel, @PathVariable long id) {
        Referentiel request =  updateRequestMapper.toEntity(referentiel);
        request.setId(id);
        return this.service.update(request);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String>  delete(@PathVariable long id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }
}
