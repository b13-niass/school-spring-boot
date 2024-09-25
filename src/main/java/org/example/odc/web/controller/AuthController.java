package org.example.odc.web.controller;

import org.example.odc.web.dto.request.LoginUserDTORequest;
import org.example.odc.web.dto.response.LoginDTOResponse;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<LoginDTOResponse> authenticate(LoginUserDTORequest loginUserDto);
}
