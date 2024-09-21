package org.example.odc.web.controller.impl;

import org.example.odc.data.entity.User;
import org.example.odc.data.repository.UserRepository;
import org.example.odc.web.dto.response.UserDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/referentiels")
public class ReferentielControllerImpl {
    public final UserRepository userRepository;
    public ReferentielControllerImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getById(@PathVariable long id) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isPresent()) {
            UserDtoResponse userDTO = UserDtoResponse.toDTO(userOpt.get());
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
