package org.example.odc.web.dto.request;

import lombok.Data;

@Data
public class LoginUserDTORequest {
    private String email;
    private String password;
}
