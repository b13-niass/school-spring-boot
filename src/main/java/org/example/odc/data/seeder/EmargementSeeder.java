package org.example.odc.data.seeder;

import org.example.odc.data.entity.Emargement;
import org.example.odc.data.entity.User;
import org.example.odc.data.repository.EmargementRepository;
import org.example.odc.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Order(12)
public class EmargementSeeder implements CommandLineRunner {

    private final EmargementRepository emargementRepository;
    private final UserRepository userRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public EmargementSeeder(EmargementRepository emargementRepository, UserRepository userRepository) {
        this.emargementRepository = emargementRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            User apprenant = userRepository.findById(1L).orElseThrow();
            Emargement emargement1 = Emargement.builder()
                    .user(apprenant)
                    .entree(LocalDateTime.now())
                    .sortie(LocalDateTime.now().plusHours(2)) // Simulating a 2-hour session
                    .build();

            Emargement emargement2 = Emargement.builder()
                    .user(apprenant)
                    .entree(LocalDateTime.now().minusDays(1).plusHours(1)) // Previous day's entry
                    .sortie(LocalDateTime.now().minusDays(1).plusHours(3)) // Previous day's exit
                    .build();

            emargementRepository.saveAll(Arrays.asList(emargement1, emargement2));
            System.out.println("EmargementSeeder is running");
        } else {
            System.out.println("EmargementSeeder is disabled");
        }
    }
}
