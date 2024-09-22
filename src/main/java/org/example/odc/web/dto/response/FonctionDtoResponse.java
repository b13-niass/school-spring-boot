package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Fonction;
import org.example.odc.data.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FonctionDtoResponse {

    private Long id;
    private String libelle;
    private String description;

    private List<User> users;
}
