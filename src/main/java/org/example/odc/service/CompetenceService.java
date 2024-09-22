package org.example.odc.service;

import org.example.odc.web.dto.response.CompetenceDtoResponse;

import java.util.Collection;

public interface CompetenceService {
    Collection<CompetenceDtoResponse> getCompetencesByReferentiel(Long referentielId);
}
