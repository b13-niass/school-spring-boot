package org.example.odc.service;

import jakarta.annotation.Nullable;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.web.dto.response.CompetenceDtoResponse;
import org.example.odc.web.dto.response.ModuleDtoResponse;
import org.example.odc.web.dto.response.RefOnlyDtoResponse;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

public interface ReferentielService {
    Collection<RefOnlyDtoResponse> getAll(@Nullable ReferentielStatusEnum filter);
    ResponseEntity<?> getById(Long id,@Nullable String filter);
    ReferentielDtoResponse save(Referentiel referentiel);
    ReferentielDtoResponse update(Referentiel referentiel);
    String delete(Long referentielId);
    Collection<ModuleDtoResponse> getModulesByReferentiel(Long referentielId);
    Collection<CompetenceDtoResponse> getCompetencesByReferentiel(Long referentielId);
}
