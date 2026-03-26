package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuerpoDTO;

import java.util.List;

public interface CapacidadConsultarCuerpo {
    CuerpoDTO consultarCuerpoPorId(Long id);
    List<CuerpoDTO> consultarCuerpos();
}
