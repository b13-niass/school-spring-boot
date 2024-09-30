package org.example.odc.service;

public interface MatriculeService {
    String generateMatricule(String promo, String referentiel, int numberOfApprenants);
    String getReferentielAbbreviation(String referentiel);
}
