package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;

import java.util.List;

public interface CapacidadConsultarUsuario {
    UsuarioDTO consultarUsuarioPorId(Long id);
    List<UsuarioDTO> consultarUsuarios();

}
