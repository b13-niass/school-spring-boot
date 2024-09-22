package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.User;
import org.example.odc.web.dto.response.UserDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    User toEntity(UserDtoResponse userDtoResponse);
    UserDtoResponse toDTO(User user);
    List<UserDtoResponse>  toDTOList(List<UserDtoResponse> userDtoResponses);
    List<User> toEntityList(List<UserDtoResponse> userDtoResponses);
}
