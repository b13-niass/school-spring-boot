package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Role;
import org.example.odc.web.dto.response.RoleDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleResponseMapper {
    Role toEntity(RoleDtoResponse roleDtoResponse);
    RoleDtoResponse toDTO(Role role);
    List<RoleDtoResponse> toDTOList(List<Role> roles);
    List<Role> toEntityList(List<RoleDtoResponse> roleDtoResponses);
}
