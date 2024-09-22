package org.example.odc.service.impl;

import jakarta.annotation.Nullable;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.exception.ReferentielException;
import org.example.odc.service.ReferentielService;
import org.example.odc.web.dto.response.CompetenceDtoResponse;
import org.example.odc.web.dto.response.ModuleDtoResponse;
import org.example.odc.web.dto.response.RefOnlyDtoResponse;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.example.odc.web.dto.response.mapper.CompetenceResponseMapper;
import org.example.odc.web.dto.response.mapper.ModuleResponseMapper;
import org.example.odc.web.dto.response.mapper.RefOnlyMapper;
import org.example.odc.web.dto.response.mapper.ReferentielResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ReferentielServiceImpl implements ReferentielService {

    public final ReferentielRepository repository;
    public ReferentielResponseMapper responseMapper;
    public RefOnlyMapper refOnlyMapper;
    public ModuleResponseMapper moduleResponseMapper;
    public CompetenceResponseMapper competenceResponseMapper;

    public ReferentielServiceImpl(
            ReferentielRepository repository,ReferentielResponseMapper responseMapper,ModuleResponseMapper moduleResponseMapper,
            CompetenceResponseMapper competenceResponseMapper,
            RefOnlyMapper refOnlyMapper){
        this.repository = repository;
        this.responseMapper = responseMapper;
        this.moduleResponseMapper = moduleResponseMapper;
        this.competenceResponseMapper = competenceResponseMapper;
        this.refOnlyMapper = refOnlyMapper;
    }

    @Override
    public Collection<RefOnlyDtoResponse> getAll(@Nullable ReferentielStatusEnum filter) {
        if (filter == null) {
            return refOnlyMapper.toDTOList(repository.findAll()
                    .stream().filter(ref -> ref.getStatus() == ReferentielStatusEnum.ACTIF).toList());
        }else {
            Collection<Referentiel> filteredRef = repository.findAllByStatus(filter);
            if (!filteredRef.isEmpty()) {
                return refOnlyMapper.toDTOList(filteredRef);
            } else {
                throw new ReferentielException("Aucun référentiel ne correspond à votre recherche", HttpStatus.NOT_FOUND);
            }
        }
    }

    @Override
    public ResponseEntity<?> getById(Long id,@Nullable String filter) {
        ReferentielDtoResponse ref = this.repository
                .findById(id)
                .map(responseMapper::toDTO)
                .orElse(null);

        if (ref!=null) {
            if (ref.getStatus() == ReferentielStatusEnum.ACTIF){
                if (filter == null){
                    return ResponseEntity.ok(ref);
                }else {
                   if (filter.equalsIgnoreCase("competence")){
                       return ResponseEntity.ok(this.getCompetencesByReferentiel(id));
                   }
                   if (filter.equalsIgnoreCase("module")){
                        return ResponseEntity.ok(this.getModulesByReferentiel(id));
                   }
                }
            }else if (ref.getStatus() == ReferentielStatusEnum.ARCHIVER){
                throw new ReferentielException("Le référentiel est archiver", HttpStatus.BAD_REQUEST);
            }else {
                throw new ReferentielException("Le référentiel est INACTIF", HttpStatus.BAD_REQUEST);
            }
        }else {
            throw new ReferentielException("Le référentiel n'existe pas", HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public ReferentielDtoResponse save(Referentiel referentiel, @Nullable Collection<Competence> competences, @Nullable Collection<Module> modules) {
        return null;
    }

    @Override
    public ReferentielDtoResponse update(Referentiel referentiel) {
        return null;
    }

    @Override
    public void delete(Referentiel referentiel) {

    }

    @Override
    public Collection<ModuleDtoResponse> getModulesByReferentiel(Long referentielId) {
        Referentiel referentiel = repository.findById(referentielId)
                .orElseThrow(() -> new ReferentielException("Referentiel non trouvé", HttpStatus.NOT_FOUND));


        return referentiel.getCompetences().stream()
                .flatMap(competence -> competence.getModules().stream()).map(moduleResponseMapper::toDTO)
                .toList();
    }

    @Override
    public Collection<CompetenceDtoResponse> getCompetencesByReferentiel(Long referentielId) {
        Referentiel referentiel = repository.findById(referentielId)
                .orElseThrow(() -> new ReferentielException("Referentiel non trouvé", HttpStatus.NOT_FOUND));
        return referentiel.getCompetences().stream().map(competenceResponseMapper::toDTO)
                .toList();
    }


}
