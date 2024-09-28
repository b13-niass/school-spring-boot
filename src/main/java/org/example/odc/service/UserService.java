package org.example.odc.service;

import org.example.odc.web.dto.request.UserDTORequest;
import org.example.odc.web.dto.response.UserDtoResponse;

public interface UserService {
    UserDtoResponse create(UserDTORequest user);
}
