package com.defensa.gestion_militar.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionServicioDTO {
    private Long idUsuario;
    private Long idServicio;
    private LocalDate fechaRealizacion;
    private String observaciones;
}