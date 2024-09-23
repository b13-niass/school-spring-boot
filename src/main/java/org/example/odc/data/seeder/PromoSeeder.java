package org.example.odc.data.seeder;

import org.example.odc.data.entity.Promo;
import org.example.odc.data.repository.PromoRepository;
import org.example.odc.enums.PromoEtatEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(4)
public class PromoSeeder implements CommandLineRunner {

    private final PromoRepository promoRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public PromoSeeder(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Promo promo1 = new Promo();
            promo1.setLibelle("2023 Batch");
            promo1.setDateDebut(LocalDate.now());
            promo1.setDateFin(LocalDate.now().plusMonths(6));
            promo1.setDureeReel(6); // Example duration in months
            promo1.setEtat(String.valueOf(PromoEtatEnum.ACTIF));

            promoRepository.saveAll(Arrays.asList(promo1));
            System.out.println("PromoSeeder is running");
        }else {
            System.out.println("PromoSeeder is disabled");
        }
    }
}
