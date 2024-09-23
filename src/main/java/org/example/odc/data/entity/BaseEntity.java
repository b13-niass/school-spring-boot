package org.example.odc.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@MappedSuperclass  // This allows sharing the fields across multiple entities
public abstract class BaseEntity {

//    @Getter

    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @Column(nullable = false)
    protected boolean deleted = false;

    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}

