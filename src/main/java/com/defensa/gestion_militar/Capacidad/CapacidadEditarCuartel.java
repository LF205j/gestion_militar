package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;

public interface CapacidadEditarCuartel {
    void editarCuartel(Long id, CuartelDTO dto);
}
