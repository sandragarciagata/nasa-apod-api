package com.sandragarciagata.nasaapodapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Informacion para guardar en la base de datos
 */

@Data
@Entity
@Table(name = "apod_records")
public class ApodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String explanation;

    private String url;
    private String mediaType;

    @Column(nullable = false, updatable = false)
    private LocalDateTime retrievedAt; // marca de tiempo

    @PrePersist
    protected void onCreate() {
        retrievedAt = LocalDateTime.now();
    }
}