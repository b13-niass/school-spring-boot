package org.example.odc.web.dto.response.mapper;

import org.example.odc.data.entity.Module;
import org.example.odc.web.dto.response.ModuleDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModuleResponseMapper {
    Module toEntity(ModuleDtoResponse moduleDtoResponse);
    ModuleDtoResponse toDTO(Module module);
    List<ModuleDtoResponse> toDTOList(List<Module> modules);
    List<Module> toEntityList(List<ModuleDtoResponse> moduleDtoResponses);
}
