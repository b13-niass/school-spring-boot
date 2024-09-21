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
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    private Double note;

    // Getters and Setters
}

