package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.UsuarioDTO;

public interface CapacidadEditarUsuarios {
    void editarUsuario(Long id, UsuarioDTO dto, String nuevaPass);
}
