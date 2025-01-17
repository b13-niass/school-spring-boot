package org.example.odc.web.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class LoginDTOResponse {
    private String token;

    private long expiresIn;
}
