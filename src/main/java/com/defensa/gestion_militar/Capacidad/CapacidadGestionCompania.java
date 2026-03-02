package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;

public interface CapacidadGestionCompania {
    void guardarCompania(CompaniaDTO dto);
    void eliminarCompania(Long id);
    void editarCompania(Long id, CompaniaDTO dto);
    void asignarCompania(Long idUser,Long idCompania);
    void consultarCompania(Long idCompania);//Long idCompania
}
