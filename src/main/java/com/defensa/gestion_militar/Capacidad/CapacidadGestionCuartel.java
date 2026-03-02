package com.defensa.gestion_militar.Capacidad;


import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;

public interface CapacidadGestionCuartel {
    void guardarCuartel(CuartelDTO dto);
    void eliminarCuartel(Long id);
    void editarCuartel(Long id, CuartelDTO dto);
    void asignarCuartel(Long idUser,Long idCuartel);
    void consultarCuartel(Long idCompania);

}
