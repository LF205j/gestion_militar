package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;

public interface CapacidadGestionCuerpo {
    void guardarCuerpo(CuerpoDTO dto);
    void eliminarCuerpo(Long id);
    void editarCuerpo(Long id, CuerpoDTO dto);
    void asignarCuerpo(Long idUser,Long idCuerpo);
    void consultarCuerpo(Long idCompania);

}
