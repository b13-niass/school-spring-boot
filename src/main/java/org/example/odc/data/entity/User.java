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
//@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String adresse;

    @Column(unique = true)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(unique = true, nullable = false)
    private String email;

    private String photo;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String password;

    // Getters and Setters
}

