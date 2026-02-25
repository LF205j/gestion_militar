package com.defensa.gestion_militar.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegistroDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private String tipoUsuario; // "OFICIAL", "SOLDADO", etc.
    private Long idCuartel;
    private Long idCuerpo;
    private Long idCompania;
}