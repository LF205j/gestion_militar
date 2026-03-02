package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;

public interface CapacidadGestionUsuario {
    void guardarUsuario(UsuarioDTO dto);
    void eliminarUsuario(Long id);
    void editarUsuario(Long id, UsuarioDTO dto, String nuevaPass);
    void consultarUsuario(Long idCompania);


}
