package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.ServiciosDTO;

public interface CapacidadEditarServicio {
    void editarServicio(Long id, ServiciosDTO dto);
}
