package org.example.odc.data.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Module extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

    private String description;
    private int dureeAcquisition;

    @OneToMany(mappedBy = "module")
    @JsonIgnore
    private List<Note> notes;

    @ManyToOne
    @JsonIgnore
    private Competence competence;
}
