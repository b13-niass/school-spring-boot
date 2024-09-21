package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RoleDtoResponse {

    private Long id;
    private String libelle;

    // If you need to include a list of users, consider creating a UserDtoResponse
    private List<UserDtoResponse> users;

    // Method to map Role entity to RoleDtoResponse
    public static RoleDtoResponse toDTO(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDtoResponse.builder()
                .id(role.getId())
                .libelle(role.getLibelle())
                .users(role.getUsers() != null
                        ? role.getUsers().stream().map(UserDtoResponse::toDTO).collect(Collectors.toList())
                        : null)
                .build();
    }

    // Method to map RoleDtoResponse to Role entity
    public static Role toEntity(RoleDtoResponse roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setLibelle(roleDTO.getLibelle());

        // Convert list of UserDtoResponse back to User entities if needed
        // You can implement this mapping if necessary
        // role.setUsers(roleDTO.getUsers() != null
        //        ? roleDTO.getUsers().stream().map(UserDtoResponse::toEntity).collect(Collectors.toList())
        //        : null);

        return role;
    }
}
