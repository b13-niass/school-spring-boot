package org.example.odc.web.controller;

import jakarta.annotation.Nullable;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.web.dto.request.ReferentielDTORequest;
import org.example.odc.web.dto.response.RefOnlyDtoResponse;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface ReferentielController {
    Collection<RefOnlyDtoResponse> getAll(ReferentielStatusEnum filter);
    ResponseEntity<?> getById(long id, String filter);
    Referentiel save(ReferentielDTORequest request);
    ReferentielDtoResponse update(Referentiel referentiel);
    ResponseEntity<String> delete(long id);
}
