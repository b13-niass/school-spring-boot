package org.example.odc.service.impl;

import org.example.odc.data.entity.Promo;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.data.repository.PromoRepository;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.service.PromoService;
import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.example.odc.web.dto.response.mapper.PromoResponseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromoServiceImpl implements PromoService {

    private final PromoRepository promoRepository;
    private final PromoReferentielRepository promoReferentielRepository;
    private final ReferentielRepository referentielRepository;
    private final PromoResponseMapper promoResponseMapper;

    public PromoServiceImpl(PromoRepository promoRepository,
                            PromoReferentielRepository promoReferentielRepository,
                            PromoResponseMapper promoResponseMapper,
                            ReferentielRepository referentielRepository) {
        this.promoRepository = promoRepository;
        this.promoReferentielRepository = promoReferentielRepository;
        this.promoResponseMapper = promoResponseMapper;
        this.referentielRepository = referentielRepository;
    }

    @Override
    @Transactional
    public PromoDtoResponse save(PromoDTORequest promoCreationDTO) {

        LocalDate dateDebut = promoCreationDTO.getDateDebut();
        LocalDate dateFin = promoCreationDTO.getDateFin();
        Integer dureeReel = promoCreationDTO.getDureeReel();

        if (dateFin == null && dureeReel != 0) {
            dateFin = dateDebut.plusMonths(dureeReel);
        }

        if (dureeReel == 0 && dateFin != null) {
            dureeReel = (int) ChronoUnit.MONTHS.between(dateDebut, dateFin);
        }

        // Create a new Promo entity
        Promo promo = Promo.builder()
                .libelle(promoCreationDTO.getLibelle())
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .dureeReel(dureeReel)
                .etat("Inactif") // Set initial state to Inactive
                .build();

        promo = promoRepository.save(promo);

        // Initialize promoReferentiels list
        List<PromoReferentiel> promoReferentiels = new ArrayList<>();
        for (Long id : promoCreationDTO.getReferentielIds()) {
            Referentiel referentiel = referentielRepository.findById(id).orElse(null);

            // Check if the referentiel exists before adding
            if (referentiel != null) {
                PromoReferentiel p = PromoReferentiel.builder()
                        .promo(promo)
                        .referentiel(referentiel)
                        .build();

                // Save the PromoReferentiel and add it to the list
                promoReferentiels.add(this.promoReferentielRepository.save(p));
            }
        }

        // Set the promoReferentiels to the promo object
        promo.setPromoReferentiels(promoReferentiels);

        // Save the promotion and return the DTO response
        return promoResponseMapper.toDTO(promo);
    }

}
