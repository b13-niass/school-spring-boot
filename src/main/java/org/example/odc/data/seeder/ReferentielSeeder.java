package org.example.odc.data.seeder;

import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.enums.ReferentielStatusEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(5)
public class ReferentielSeeder implements CommandLineRunner {

    private final ReferentielRepository referentielRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public ReferentielSeeder(ReferentielRepository referentielRepository) {
        this.referentielRepository = referentielRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Referentiel referentiel1 = new Referentiel();
            referentiel1.setLibelle("Web Development");
            referentiel1.setCode("WD-101");
            referentiel1.setStatus(ReferentielStatusEnum.ACTIF); // Set a default status

            Referentiel referentiel2 = new Referentiel();
            referentiel2.setLibelle("Data Science");
            referentiel2.setCode("DS-202");
            referentiel2.setStatus(ReferentielStatusEnum.ACTIF); // Set a default status

            referentielRepository.saveAll(Arrays.asList(referentiel1, referentiel2));
            System.out.println("ReferentielSeeder is running");
        }else {
            System.out.println("ReferentielSeeder is disabled");
        }
    }
}
