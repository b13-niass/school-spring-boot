package org.example.odc.service.impl;

import org.example.odc.service.MatriculeService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class MatriculeServiceImpl implements MatriculeService {

    @Override
    public String generateMatricule(String promo, String referentiel, int numberOfApprenants) {
        String year = promo.split(" ")[1];

        String abbreviation = getReferentielAbbreviation(referentiel);

        String number = new DecimalFormat("0000").format(numberOfApprenants + 1);

        return abbreviation + "_" + year + "_" + number;
    }

    @Override
    public String getReferentielAbbreviation(String referentiel) {
        String[] words = referentiel.split(" ");
        StringBuilder abbreviation = new StringBuilder();

        for (String word : words) {
            abbreviation.append(word.charAt(0));
        }

        return abbreviation.toString().toUpperCase();
    }
}
