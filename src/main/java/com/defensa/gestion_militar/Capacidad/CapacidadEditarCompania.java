package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;

public interface CapacidadEditarCompania {
    void editarCompania(Long id, CompaniaDTO dto);
}
