package com.defensa.gestion_militar.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id; // Del Identificable
    private String nombre;
    private String apellido;
    private String email;
    private String tipoUsuario; // Aquí irá "OFICIAL", "SOLDADO", etc.

    private String nombreCuerpo; // Solo el nombre, no toda la entidad
    private String nombreCuartel; // Solo el nombre
    private String nombreCompania; // Solo el nombre
    private List<String> nombresServicios;

}
