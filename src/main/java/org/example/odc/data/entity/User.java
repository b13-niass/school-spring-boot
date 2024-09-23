package org.example.odc.data.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;

    @Column(unique = true)
    private String telephone;

    @ManyToOne
    private Fonction fonction;

    @ManyToOne
    private Role role;
    @Column(unique = true)
    private String email;

    private String photo;
    private String status; // Bloquer, Actif, Inactif
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Emargement> emargements;
}

