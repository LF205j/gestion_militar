package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.ServiciosDTO;

public interface CapacidadGestionServicio {
    void guardarServicio(ServiciosDTO dto);
    void eliminarServicio(Long id);
    void editarServicio(Long id, ServiciosDTO dto);
    void asignarServicio(Long idUser,Long idCuerpo);
    void consultarServicio(Long idCompania);

}
