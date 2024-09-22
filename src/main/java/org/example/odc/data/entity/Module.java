package org.example.odc.data.entity;
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
public class Module {

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
