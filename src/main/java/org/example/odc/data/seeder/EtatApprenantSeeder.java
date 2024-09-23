package org.example.odc.data.seeder;

import org.example.odc.data.entity.EtatApprenant;
import org.example.odc.data.repository.EtatApprenantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(9)
public class EtatApprenantSeeder implements CommandLineRunner {

    private final EtatApprenantRepository etatApprenantRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;
    public EtatApprenantSeeder(EtatApprenantRepository etatApprenantRepository) {
        this.etatApprenantRepository = etatApprenantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            EtatApprenant etat1 = new EtatApprenant();
            etat1.setEtat("Actif");

            EtatApprenant etat2 = new EtatApprenant();
            etat2.setEtat("Inactif");

            EtatApprenant etat3 = new EtatApprenant();
            etat3.setEtat("Suspendu");

            etatApprenantRepository.saveAll(Arrays.asList(etat1, etat2, etat3));
            System.out.println("EtatApprenantSeeder is running");
        }else {
            System.out.println("EtatApprenantSeeder is disabled");
        }
    }
}
