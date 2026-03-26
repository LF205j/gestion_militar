package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;

import java.util.List;

public interface CapacidadConsultarCompania {
    CompaniaDTO consultarCompaniaPorId(Long id);
    List<CompaniaDTO> consultarCompanias();
}
