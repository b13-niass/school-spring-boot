package org.example.odc.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EtatApprenant  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String etat;

    private String motif;

    @OneToMany(mappedBy = "etatApprenant")
    private List<Apprenant> apprenants;

    // Getters and Setters
}

