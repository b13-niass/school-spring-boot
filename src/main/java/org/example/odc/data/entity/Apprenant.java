package org.example.odc.data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Apprenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom_tuteur;
    private String prenom_tuteur;
    private String contact_tuteur;
    private String cni_file;
    private String extrait_file;
    private String diplome_file;
    private String casier_file;
    private String photo_couverture;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "referentiel_id")
    private Referentiel referentiel;

    @ManyToOne
    @JoinColumn(name = "etat_id")
    private EtatApprenant etatApprenant;

    // Getters and Setters
}

