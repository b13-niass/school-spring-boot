package org.example.odc.data.seeder;

import org.example.odc.data.entity.Promo;
import org.example.odc.data.entity.PromoReferentiel;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.data.repository.PromoRepository;
import org.example.odc.data.repository.ReferentielRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
@Order(8)
public class PromoReferentielSeeder implements CommandLineRunner {

    private final PromoReferentielRepository promoReferentielRepository;
    private final PromoRepository promoRepository;
    private final ReferentielRepository referentielRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public PromoReferentielSeeder(PromoReferentielRepository promoReferentielRepository, PromoRepository promoRepository, ReferentielRepository referentielRepository) {
        this.promoReferentielRepository = promoReferentielRepository;
        this.promoRepository = promoRepository;
        this.referentielRepository = referentielRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Promo promo = promoRepository.findByLibelle("Promo 2023").orElseThrow();
            Referentiel referentiel = referentielRepository.findByCode("WD-101").orElseThrow();

            PromoReferentiel promoReferentiel = PromoReferentiel.builder()
                    .promo(promo)
                    .referentiel(referentiel)
                    .build();

            promoReferentielRepository.saveAll(Arrays.asList(promoReferentiel));
            System.out.println("PromoReferentielSeeder is running");
        } else {
            System.out.println("PromoReferentielSeeder is disabled");
        }
    }
}
