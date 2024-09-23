package org.example.odc.data.seeder;

import org.example.odc.data.entity.Module;
import org.example.odc.data.entity.Competence;
import org.example.odc.data.repository.ModuleRepository;
import org.example.odc.data.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(7)
public class ModuleSeeder implements CommandLineRunner {

    private final ModuleRepository moduleRepository;
    private final CompetenceRepository competenceRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public ModuleSeeder(ModuleRepository moduleRepository, CompetenceRepository competenceRepository) {
        this.moduleRepository = moduleRepository;
        this.competenceRepository = competenceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Competence competence = competenceRepository.findById(1L).orElseThrow(); // Assuming you have a competence to link

            Module module1 = new Module();
            module1.setNom("Advanced Java");
            module1.setDureeAcquisition(4);
            module1.setCompetence(competence);

            moduleRepository.saveAll(Arrays.asList(module1));
            System.out.println("ModuleSeeder is running");
        }else {
            System.out.println("ModuleSeeder is disabled");

        }
    }
}
