package org.example.odc.service.impl;

import jakarta.annotation.Nullable;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.repository.CompetenceRepository;
import org.example.odc.data.repository.ModuleRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ReferentielServiceImpl implements ReferentielService {

    public final ReferentielRepository repository;
    public ReferentielResponseMapper responseMapper;
    public RefOnlyMapper refOnlyMapper;
    public ModuleResponseMapper moduleResponseMapper;
    private final CompetenceRepository competenceRepository;
    private final ModuleRepository moduleRepository;
    public CompetenceResponseMapper competenceResponseMapper;

    public ReferentielServiceImpl(
            ReferentielRepository repository,ReferentielResponseMapper responseMapper,ModuleResponseMapper moduleResponseMapper,
            CompetenceResponseMapper competenceResponseMapper,
            RefOnlyMapper refOnlyMapper,
            CompetenceRepository competenceRepository,
            ModuleRepository moduleRepository){
        this.repository = repository;
        this.responseMapper = responseMapper;
        this.moduleResponseMapper = moduleResponseMapper;
        this.competenceResponseMapper = competenceResponseMapper;
        this.refOnlyMapper = refOnlyMapper;
        this.competenceRepository = competenceRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public Collection<RefOnlyDtoResponse> getAll(@Nullable ReferentielStatusEnum filter) {
        if (filter == null) {
            return refOnlyMapper.toDTOList(repository.findByDeletedFalse()
                    .stream().filter(ref -> ref.getStatus() == ReferentielStatusEnum.ACTIF).toList());
        }else {
            Collection<Referentiel> filteredRef = repository.findByDeletedFalseAndStatus(filter);
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
                .findByIdAndDeletedFalse(id)
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
    @Transactional
    public ReferentielDtoResponse save(Referentiel referentiel) {

        for (Competence competence : referentiel.getCompetences()) {
            competence.setReferentiel(referentiel);
            for (Module module : competence.getModules()) {
                module.setCompetence(competence);
            }
        }
        return responseMapper.toDTO(repository.save(referentiel));
    }

    @Override
    @Transactional
    public ReferentielDtoResponse update(Referentiel dto) {
        Referentiel referentiel = repository.findById(dto.getId())
                .orElseThrow(() -> new ReferentielException("Référentiel not found", HttpStatus.NOT_FOUND));

        referentiel.setLibelle(dto.getLibelle());
        if (dto.getCode() != null) referentiel.setCode(dto.getCode());
        if (dto.getDescription() != null) referentiel.setDescription(dto.getDescription());
        if (dto.getPhotoCouverture() != null) referentiel.setPhotoCouverture(dto.getPhotoCouverture());
        if (dto.getStatus() != null) referentiel.setStatus(ReferentielStatusEnum.valueOf(String.valueOf(dto.getStatus())));

        if (dto.getCompetences() != null) {
            for (Competence competenceDto : dto.getCompetences()) {
                Competence competence = competenceRepository.findByReferentielAndNom(referentiel, competenceDto.getNom())
                        .orElseGet(() -> Competence.builder().build());

                competence.setNom(competenceDto.getNom());
                competence.setDescription(competenceDto.getDescription());
                competence.setDureeAcquisition(competenceDto.getDureeAcquisition());
                competence.setType(competenceDto.getType());
                competence.setReferentiel(referentiel);
                competenceRepository.save(competence);

                if (competenceDto.getModules() != null) {
                    for (Module moduleDto : competenceDto.getModules()) {
                        Module module = moduleRepository.findByCompetenceAndNom(competence, moduleDto.getNom())
                                .orElseGet(() -> Module.builder().build());

                        module.setNom(moduleDto.getNom());
                        module.setDescription(moduleDto.getDescription());
                        module.setDureeAcquisition(moduleDto.getDureeAcquisition());
                        module.setCompetence(competence);
                        moduleRepository.save(module);
                    }
                }
            }
        }

        return responseMapper.toDTO(repository.save(referentiel));
    }

    @Override
    public String delete(Long referentielId) {
        Referentiel referentiel = repository.findById(referentielId)
                .orElseThrow(() -> new ReferentielException("Referentiel non trouvé", HttpStatus.NOT_FOUND));

        repository.delete(referentiel);
        return "Referentiel soft-deleted successfully.";
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
