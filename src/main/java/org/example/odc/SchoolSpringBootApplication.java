package org.example.odc;

import org.example.odc.data.entity.User;
import org.example.odc.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

import org.dieng.core.exception.ReferentielException;

@SpringBootApplication
public class SchoolSpringBootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SchoolSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        mvn clean package -DskipTests
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        // The plain text password
//        String plainPassword = "password";
//
//        // Hash the password
//        String hashedPassword = passwordEncoder.encode(plainPassword);
//
//        // Print the hashed password
//        System.out.println("Hashed Password: " + hashedPassword);

    }
}
