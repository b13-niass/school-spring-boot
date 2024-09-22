package org.example.odc.service.impl;

import org.example.odc.data.repository.CompetenceRepository;
import org.example.odc.service.CompetenceService;
import org.example.odc.web.dto.response.CompetenceDtoResponse;
import org.example.odc.web.dto.response.mapper.CompetenceResponseMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    private final CompetenceRepository competenceRepository;
    private final CompetenceResponseMapper competenceResponseMapper;

    public CompetenceServiceImpl(CompetenceRepository competenceRepository, CompetenceResponseMapper competenceResponseMapper) {
        this.competenceRepository = competenceRepository;
        this.competenceResponseMapper = competenceResponseMapper;
    }

    public Collection<CompetenceDtoResponse> getCompetencesByReferentiel(Long referentielId) {
        return competenceRepository
                .findByReferentielId(referentielId)
                .stream()
                .map(competenceResponseMapper::toDTO)
                .toList();
    }
}
