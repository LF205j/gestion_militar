package com.defensa.gestion_militar.Capacidad;

import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.ServiciosDTO;

import java.util.List;

public interface CapacidadConsultarServicio {
    ServiciosDTO consultarServicioPorId(Long id);
    List<ServiciosDTO> consultarServicios();
}
