package org.example.odc.web.controller.impl;

import org.example.odc.data.entity.User;
import org.example.odc.service.impl.AuthServiceImpl;
import org.example.odc.service.impl.JwtServiceImpl;
import org.example.odc.web.dto.request.LoginUserDTORequest;
import org.example.odc.web.dto.response.LoginDTOResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthControllerImpl {

    private final JwtServiceImpl jwtService;

    private final AuthServiceImpl authenticationService;

    public AuthControllerImpl(JwtServiceImpl jwtService, AuthServiceImpl authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDTOResponse> authenticate(@RequestBody LoginUserDTORequest loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginDTOResponse loginResponse = LoginDTOResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }
}
