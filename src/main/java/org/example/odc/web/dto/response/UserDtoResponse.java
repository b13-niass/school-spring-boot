package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.User;

import java.util.List;
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class UserDtoResponse {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String email;
    private String photo;
    private String status;

    // You can add other fields or a list of related entities as needed
    // For example, if you want to include roles, you could do:
    private String role; // or RoleDTO if you create a DTO for Role

    // If you need to include a list of Emargements, consider creating an EmargementDTO
    private List<EmargementDtoResponse> emargements; // Assuming you create this DTO as well

    public static UserDtoResponse toDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserDtoResponse.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .adresse(user.getAdresse())
                .telephone(user.getTelephone())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .status(user.getStatus())
                // Map role and emargements as needed
                .role(user.getRole() != null ? user.getRole().getLibelle() : null) // Assuming Role has a getName() method
                // .emargements(user.getEmargements().stream().map(EmargementMapper::toDTO).collect(Collectors.toList())) // If you create a mapper for Emargement
                .build();
    }

    public static User toEntity(UserDtoResponse userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setAdresse(userDTO.getAdresse());
        user.setTelephone(userDTO.getTelephone());
        user.setEmail(userDTO.getEmail());
        user.setPhoto(userDTO.getPhoto());
        user.setStatus(userDTO.getStatus());
        // Set role and emargements as needed
        return user;
    }
}