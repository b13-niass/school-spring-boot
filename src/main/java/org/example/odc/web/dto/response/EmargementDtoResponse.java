package org.example.odc.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.odc.data.entity.Emargement;
import org.example.odc.data.entity.User;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmargementDtoResponse {

    private Long id;
    private LocalDateTime entree;
    private LocalDateTime sortie;
    private User user;

}
