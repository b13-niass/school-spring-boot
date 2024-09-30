package org.example.odc.data.seeder;

import org.example.odc.data.entity.Apprenant;
import org.example.odc.data.entity.EtatApprenant;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.User;
import org.example.odc.data.repository.ApprenantRepository;
import org.example.odc.data.repository.UserRepository;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.data.repository.EtatApprenantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(10)
public class ApprenantSeeder implements CommandLineRunner {

    private final ApprenantRepository apprenantRepository;
    private final UserRepository userRepository;
    private final PromoReferentielRepository promoReferentielRepository;
    private final EtatApprenantRepository etatApprenantRepository;

    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public ApprenantSeeder(ApprenantRepository apprenantRepository, UserRepository userRepository,
                           PromoReferentielRepository promoReferentielRepository, EtatApprenantRepository etatApprenantRepository) {
        this.apprenantRepository = apprenantRepository;
        this.userRepository = userRepository;
        this.promoReferentielRepository = promoReferentielRepository;
        this.etatApprenantRepository = etatApprenantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            User user = userRepository.findById(1L).orElseThrow();
            PromoReferentiel promoReferentiel = promoReferentielRepository.findById(1L).orElseThrow();
            EtatApprenant etat = etatApprenantRepository.findById(1L).orElseThrow();

            // Create multiple Apprenant instances
            Apprenant apprenant1 = Apprenant.builder()
                    .user(user)
                    .promoReferentiel(promoReferentiel)
                    .etatApprenant(etat)
                    .nomTuteur("Tuteur1")
                    .prenomTuteur("Prenom1")
                    .contactTuteur("0123456789")
                    .cniFile("path/to/cni1")
                    .extraitFile("path/to/extrait1")
                    .diplomeFile("path/to/diplome1")
                    .casierFile("path/to/casier1")
                    .photoCouverture("path/to/photo1")
                    .matricule("WD_2023_0001")
                    .build();

            Apprenant apprenant2 = Apprenant.builder()
                    .user(user)
                    .promoReferentiel(promoReferentiel)
                    .etatApprenant(etat)
                    .nomTuteur("Tuteur2")
                    .prenomTuteur("Prenom2")
                    .contactTuteur("0987654321")
                    .cniFile("path/to/cni2")
                    .extraitFile("path/to/extrait2")
                    .diplomeFile("path/to/diplome2")
                    .casierFile("path/to/casier2")
                    .photoCouverture("path/to/photo2")
                    .matricule("WD_2023_0002")
                    .build();

            apprenantRepository.saveAll(Arrays.asList(apprenant1, apprenant2));
            System.out.println("ApprenantSeeder is running");
        }else {
            System.out.println("ApprenantSeeder is disabled");
        }
    }
}
