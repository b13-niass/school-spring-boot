package org.example.odc.web.controller.impl;

import jakarta.annotation.Nullable;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.service.ReferentielService;
import org.example.odc.web.controller.ReferentielController;
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

    public ReferentielControllerImpl(ReferentielService service) {
        this.service = service;
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
    public ReferentielDtoResponse save(Referentiel referentiel, @Nullable Collection<Competence> competences, @Nullable Collection<Module> modules) {
        return null;
    }

    @PatchMapping("/{id}")
    @Override
    public ReferentielDtoResponse update(Referentiel referentiel) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<String>  delete(@PathVariable long id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }
}
