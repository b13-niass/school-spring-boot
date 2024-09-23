package org.example.odc.data.seeder;

import org.example.odc.data.entity.Role;
import org.example.odc.data.entity.User;
import org.example.odc.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(2)
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Role role = Role.builder().id(1L).build();
            Role role1 = Role.builder().id(3L).build();
            User user1 = User.builder()
                    .nom("John")
                    .prenom("Doe")
                    .telephone("0123456789")
                    .email("john.doe@example.com")
                    .status("Actif")
                    .password("password")
                    .role(role)
                    .build();

            User user2 = User.builder()
                    .nom("Jane")
                    .prenom("Doe")
                    .telephone("0987654321")
                    .email("jane.doe@example.com")
                    .status("Actif")
                    .password("password")
                    .role(role1)
                    .build();

            userRepository.saveAll(Arrays.asList(user1, user2));
            System.out.println("UserSeeder is running");
        }else {
            System.out.println("UserSeeder is disabled");
        }
    }
}
