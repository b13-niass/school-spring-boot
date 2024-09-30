package org.example.odc.data.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apprenant extends BaseEntity{
    // ajout create_at et update_at
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
    private String qrCode;

    @Column(unique = true, nullable = false) // Assure l'unicité du matricule
    private String matricule;

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


