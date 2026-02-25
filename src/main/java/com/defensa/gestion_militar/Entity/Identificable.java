package com.defensa.gestion_militar.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass // Esta anotación es clave en Spring Boot
public abstract class Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id; // Usamos Long por convención de Spring

    // Getters y Setters

    @Override
    public String toString() {
        return "Identificable{" +
                "id=" + id +
                '}';
    }
}