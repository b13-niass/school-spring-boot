package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Emargement;
import org.example.odc.data.entity.Role;
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

    private Role role;

    private List<Emargement> emargements;
}