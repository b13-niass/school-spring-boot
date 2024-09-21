package org.example.odc.data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apprenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomTuteur;
    private String prenomTuteur;
    private String contactTuteur;

    private String cniFile;
    private String extraitFile;
    private String diplomeFile;
    private String casierFile;
    private String photoCouverture;

    @ManyToOne
    private User user;

    @ManyToOne
    private PromoReferentiel promoReferentiel;

    @ManyToOne
    private EtatApprenant etatApprenant;

    @OneToMany(mappedBy = "apprenant")
    private List<Note> notes;

    // Getters and Setters
}


