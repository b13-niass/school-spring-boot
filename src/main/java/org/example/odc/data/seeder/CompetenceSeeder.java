package org.example.odc.data.seeder;

import org.example.odc.data.entity.Competence;
import org.example.odc.data.entity.Referentiel;
import org.example.odc.data.repository.CompetenceRepository;
import org.example.odc.data.repository.ReferentielRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
@Order(6)
public class CompetenceSeeder implements CommandLineRunner {

    private final CompetenceRepository competenceRepository;
    private final ReferentielRepository referentielRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public CompetenceSeeder(CompetenceRepository competenceRepository,
                            ReferentielRepository referentielRepository) {
        this.competenceRepository = competenceRepository;
        this.referentielRepository = referentielRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Referentiel referentielWebDev = referentielRepository.findByCode("WD-101").orElseThrow();
            Referentiel referentielDataSci = referentielRepository.findByCode("DS-202").orElseThrow();

            Competence competence1 = Competence.builder()
                    .nom("Java Programming")
                    .description("Learn the fundamentals of Java programming.")
                    .dureeAcquisition(3)
                    .type("Programming")
                    .referentiel(referentielWebDev)
                    .build();

            Competence competence2 = Competence.builder()
                    .nom("Web Development")
                    .description("Introduction to web development using HTML, CSS, and JavaScript.")
                    .dureeAcquisition(4)
                    .type("Development")
                    .referentiel(referentielWebDev)
                    .build();

            Competence competence3 = Competence.builder()
                    .nom("Database Management")
                    .description("Understand how to manage databases using SQL.")
                    .dureeAcquisition(2)
                    .type("Database")
                    .referentiel(referentielDataSci)
                    .build();

            competenceRepository.saveAll(Arrays.asList(competence1, competence2, competence3));
            System.out.println("CompetenceSeeder is running");
        } else {
            System.out.println("CompetenceSeeder is disabled");
        }
    }
}
