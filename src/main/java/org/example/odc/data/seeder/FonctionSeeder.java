package org.example.odc.data.seeder;

import org.example.odc.data.entity.Fonction;
import org.example.odc.data.repository.FonctionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(3)
public class FonctionSeeder implements CommandLineRunner {

    private final FonctionRepository fonctionRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public FonctionSeeder(FonctionRepository fonctionRepository) {
        this.fonctionRepository = fonctionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Fonction fonction1 = new Fonction();
            fonction1.setLibelle("Instructor");
            fonction1.setDescription("Responsible for teaching courses.");

            Fonction fonction2 = new Fonction();
            fonction2.setLibelle("Coordinator");
            fonction2.setDescription("Oversees the curriculum and course schedules.");

            fonctionRepository.saveAll(Arrays.asList(fonction1, fonction2));
            System.out.println("FonctionSeeder is running");
        }else {
            System.out.println("FonctionSeeder is disabled");
        }
    }
}
