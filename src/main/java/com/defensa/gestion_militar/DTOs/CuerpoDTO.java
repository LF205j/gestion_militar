package com.defensa.gestion_militar.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuerpoDTO {
    private Long id;
    private String denominacionCuerpo; // Ejemplo: "Infantería", "Artillería"
}
