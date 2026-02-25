package com.defensa.gestion_militar.DTOs;

import lombok.Data;

@Data
public class AsignacionDTO {
    private Long usuarioId;
    private Long idCuartel;
    private Long idCuerpo;
    private Long idCompania;
    // Para servicios específicos
    private Long idServicio;
    private String observaciones;
}