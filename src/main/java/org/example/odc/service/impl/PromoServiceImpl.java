package org.example.odc.service.impl;

import org.example.odc.data.entity.Promo;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.data.repository.PromoRepository;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.exception.ReferentielException;
import org.example.odc.exception.promo.PromoAlreadyExistsException;
import org.example.odc.exception.promo.PromoBadRequestException;
import org.example.odc.exception.promo.PromoNotFoundException;
import org.example.odc.exception.promoref.PromoReferentielNotFoundException;
import org.example.odc.service.PromoService;
import org.example.odc.web.dto.request.PromoDTORequest;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;
import org.example.odc.web.dto.request.PromoUpdateRefDTORequest;
import org.example.odc.web.dto.request.PromoUpdateStatusDTORequest;
import org.example.odc.web.dto.response.PromoDtoResponse;
import org.example.odc.web.dto.response.PromoStatsDTOResponse;
import org.example.odc.web.dto.response.ReferentielDtoResponse;
import org.example.odc.web.dto.response.ReferentielStatsDTOResponse;
import org.example.odc.web.dto.response.mapper.PromoResponseMapper;
import org.example.odc.web.dto.response.mapper.ReferentielResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PromoServiceImpl implements PromoService {

    private final PromoRepository promoRepository;
    private final PromoReferentielRepository promoReferentielRepository;
    private final ReferentielRepository referentielRepository;
    private final PromoResponseMapper promoResponseMapper;
    private final ReferentielResponseMapper referentielResponseMapper;

    public PromoServiceImpl(PromoRepository promoRepository,
                            PromoReferentielRepository promoReferentielRepository,
                            PromoResponseMapper promoResponseMapper,
                            ReferentielRepository referentielRepository,
                            ReferentielResponseMapper referentielResponseMapper) {
        this.promoRepository = promoRepository;
        this.promoReferentielRepository = promoReferentielRepository;
        this.promoResponseMapper = promoResponseMapper;
        this.referentielRepository = referentielRepository;
        this.referentielResponseMapper = referentielResponseMapper;
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

        Promo promo = Promo.builder()
                .libelle(promoCreationDTO.getLibelle())
                .dateDebut(dateDebut)
                .dateFin(dateFin)
                .dureeReel(dureeReel)
                .etat("INACTIF") // Set initial state to Inactive
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

    @Override
    public PromoDtoResponse update(PromoUpdateDTORequest promoDTORequest, Long id) {
        Optional<Promo> optionalPromo = promoRepository.findById(id);

        if (optionalPromo.isPresent()) {
            Promo existingPromo = optionalPromo.get();

            // Mise à jour des dates (si dateDebut ou dateFin est fourni)
            if (promoDTORequest.getDateDebut() != null) {
                if (existingPromo.getDateFin() != null && promoDTORequest.getDateDebut().isAfter(existingPromo.getDateFin())) {
                    throw new IllegalArgumentException("La date de début ne peut pas être supérieure à la date de fin.");
                }
                existingPromo.setDateDebut(promoDTORequest.getDateDebut());

                if (existingPromo.getDateFin() != null) {
                    // Calcul de la durée réelle en mois
                    long duree = ChronoUnit.MONTHS.between(promoDTORequest.getDateDebut(), existingPromo.getDateFin());
                    existingPromo.setDureeReel((int) duree);
                }
            }

            // Si à la fois dateDebut et dateFin sont fournis
            if (promoDTORequest.getDateDebut() != null && promoDTORequest.getDateFin() != null) {
                if (promoDTORequest.getDateDebut().isAfter(promoDTORequest.getDateFin())) {
                    throw new IllegalArgumentException("La date de début ne peut pas être supérieure à la date de fin.");
                }
                existingPromo.setDateDebut(promoDTORequest.getDateDebut());
                existingPromo.setDateFin(promoDTORequest.getDateFin());

                // Calcul de la durée réelle en mois
                long duree = ChronoUnit.MONTHS.between(promoDTORequest.getDateDebut(), promoDTORequest.getDateFin());
                existingPromo.setDureeReel((int) duree);
            }

            // Si dureeReel est fournie, recalculer la date de fin
            if (promoDTORequest.getDureeReel() != null) {
                if (existingPromo.getDateDebut() != null) {
                    existingPromo.setDureeReel(promoDTORequest.getDureeReel());
                    existingPromo.setDateFin(existingPromo.getDateDebut().plusMonths(promoDTORequest.getDureeReel()));
                } else {
                    throw new IllegalArgumentException("Impossible de recalculer la date de fin sans la date de début.");
                }
            }

            // Mise à jour du libellé et de l'état si fournis
            if (promoDTORequest.getLibelle() != null) {
                existingPromo.setLibelle(promoDTORequest.getLibelle());
            }
            if (promoDTORequest.getEtat() != null) {
                existingPromo.setEtat(promoDTORequest.getEtat());
            }

            // Enregistrement de la promo mise à jour
            Promo promo = promoRepository.save(existingPromo);
            return promoResponseMapper.toDTO(promo);

        } else {
            throw new RuntimeException("Promo introuvable avec l'id " + id);
        }
    }

    @Override
    @Transactional
    public PromoDtoResponse updateReferentiel(PromoUpdateRefDTORequest request) {
        List<Long> referentielIdsToAdd = request.getReferentielIdsToAdd();
        Long promoId = request.getPromoId();

        Promo promo = promoRepository.findById(promoId)
                .orElseThrow(() -> new PromoNotFoundException("Promo not found"));

        // 1. Ajouter les référentiels
        for (Long referentielIdToAdd : request.getReferentielIdsToAdd()) {
            Referentiel referentiel = referentielRepository.findById(referentielIdToAdd)
                    .orElseThrow(() -> new ReferentielException("Referentiel not found with id: " + referentielIdToAdd, HttpStatus.BAD_REQUEST));

            boolean isActive = referentielRepository.existsByIdAndStatusActive(referentielIdToAdd);
            boolean isAlreadyAssociated = promoReferentielRepository.existsByPromoIdAndReferentielId(promoId, referentielIdToAdd);

            if (!isActive) {
                throw new PromoBadRequestException("Toutes les referentiels à ajouter doivent etre activé");
            }

            if (isAlreadyAssociated) {
                throw new PromoBadRequestException("Les référentiels sont déjà associer au référentiel");
            }

            PromoReferentiel promoReferentiel = PromoReferentiel.builder()
                    .promo(promo)
                    .referentiel(referentiel)
                    .build();
            promoReferentielRepository.save(promoReferentiel);
        }

        for (Long referentielIdToRemove : request.getReferentielIdsToRemove()) {
            PromoReferentiel promoReferentiel = promoReferentielRepository.findByPromoAndReferentielId(promo, referentielIdToRemove)
                    .orElseThrow(() -> new PromoReferentielNotFoundException("Referentiel association not found with id: " + referentielIdToRemove));

            promoReferentielRepository.delete(promoReferentiel);
        }
        return promoResponseMapper.toDTO(promo);
    }

    @Override
    public PromoDtoResponse updateStatus(PromoUpdateStatusDTORequest request) {
        // Vérifier si une promotion est déjà active
        System.out.println("teste");
        Promo promo = promoRepository.findById(request.getPromoId())
                .orElseThrow(() -> new PromoNotFoundException("Promo not found"));

        Optional<Promo> existingPromoActif = promoRepository.findByEtat("EN_COUR");

        if (request.getEtat().equals("EN_COUR") && existingPromoActif.isPresent()) {
            throw new PromoAlreadyExistsException("Un promo Actif existe déjà");
        }

        promo.setEtat(request.getEtat());

        return this.promoResponseMapper.toDTO(promoRepository.save(promo));
    }

    @Override
    public List<PromoDtoResponse> getAll() {
        Collection<Promo> promos = this.promoRepository.findByDeletedFalse();
        return this.promoResponseMapper.toDTOList(promos.stream().toList());
    }

    public PromoDtoResponse getEncours(){
        Promo promo = this.promoRepository.findByEtat("EN_COUR")
                .orElseThrow(() -> new PromoNotFoundException("Aucun Promo en cours"));
        return promoResponseMapper.toDTO(promo);
    }

    @Override
    public List<ReferentielDtoResponse> getActiveReferentielsForPromo(Long promoId) {
        List<PromoReferentiel> promoReferentiels = promoReferentielRepository
                .findByPromoIdAndReferentielStatus(promoId, ReferentielStatusEnum.ACTIF)
                .orElseThrow(() -> new ReferentielException("Pas de ", HttpStatus.NOT_FOUND));

        // Extract and return the list of Referentiels
        List<Referentiel> referentiels = promoReferentiels.stream()
                .map(PromoReferentiel::getReferentiel)
                .collect(Collectors.toList());
        return referentielResponseMapper.toDTOList(referentiels);
    }

    public PromoStatsDTOResponse getPromoStats(Long promoId) {
        // Fetch the Promo details
        Optional<Promo> promoOpt = promoRepository.findById(promoId);
        if (promoOpt.isEmpty()) {
            throw new PromoNotFoundException("Promo with ID " + promoId + " not found.");
        }
        Promo promo = promoOpt.get();

        // Fetch active referentiels
        List<PromoReferentiel> activeReferentiels = promoReferentielRepository.findByPromoIdAndReferentielStatus(promoId, ReferentielStatusEnum.ACTIF).orElse(null);

        // Calculate total learners, active learners, inactive learners, and learners per referentiel
        AtomicInteger totalLearners = new AtomicInteger();
        AtomicInteger activeLearners = new AtomicInteger();
        AtomicInteger inactiveLearners = new AtomicInteger();

        // Collect the data for active referentiels and their learner counts
        List<ReferentielStatsDTOResponse> referentielStats = activeReferentiels.stream()
                .map(promoReferentiel -> {
                    int referentielLearnersCount = promoReferentiel.getApprenants().size();
                    totalLearners.addAndGet(referentielLearnersCount);

                    // Count active and inactive learners
                    long activeCount = promoReferentiel.getApprenants().stream()
                            .filter(apprenant -> apprenant.getEtatApprenant().getEtat().equals("ACTIF"))
                            .count();

                    long inactiveCount = referentielLearnersCount - activeCount;
                    activeLearners.addAndGet((int) activeCount);
                    inactiveLearners.addAndGet((int) inactiveCount);

                    // Return referentiel details
                    return ReferentielStatsDTOResponse.builder()
                            .id(promoReferentiel.getReferentiel().getId())
                            .libelle(promoReferentiel.getReferentiel().getLibelle())
                            .apprenantCount((long) referentielLearnersCount)
                            .build();
                }).collect(Collectors.toList());

        // Build the PromoStatsDTOResponse to return
        return PromoStatsDTOResponse.builder()
                .id(promo.getId())
                .libelle(promo.getLibelle())
                .dateDebut(promo.getDateDebut())
                .dateFin(promo.getDateFin())
                .totalLearners(totalLearners.longValue())
                .activeLearners(activeLearners.longValue())
                .inactiveLearners(inactiveLearners.longValue())
                .referentielStats(referentielStats)
                .build();
    }
}
