package com.defensa.gestion_militar.DTOs;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ServicioHistorialDTO {
    private Long idRealizaServicio; // El ID de la tabla 'realiza_servicio'
    private String nombreServicio;
    private String descripcion;
    private LocalDate fecha;
    private boolean realizado; // Atributo clave que mencionas
    private String observaciones;
}
