package org.example.odc;

import org.example.odc.data.entity.User;
import org.example.odc.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;


@SpringBootApplication
public class SchoolSpringBootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SchoolSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
