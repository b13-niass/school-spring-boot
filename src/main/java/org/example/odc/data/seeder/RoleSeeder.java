package org.example.odc.data.seeder;

import org.example.odc.data.entity.Role;
import org.example.odc.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(1)
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    @Value("${seeder.enabled}")
    private boolean seederEnabled;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (seederEnabled) {
            Role role1 = new Role();
            role1.setLibelle("Admin");

            Role role2 = new Role();
            role2.setLibelle("Manager");

            Role role3 = new Role();
            role3.setLibelle("Apprenant"); // Additional role

            try {
                roleRepository.saveAll(Arrays.asList(role1, role2, role3));
                System.out.println("RoleSeeder is running");
            } catch (Exception e) {
                System.err.println("Error saving roles: " + e.getMessage());
            }
        }else {
            System.out.println("RoleSeeder is disabled");
        }
    }
}
