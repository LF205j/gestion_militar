package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;

import java.util.List;

public interface CapacidadConsultarCuartel {
    CuartelDTO consultarCuartelPorId(Long id);
    List<CuartelDTO> consultarCuarteles();
}
