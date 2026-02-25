package com.defensa.gestion_militar.Capacidad;

public interface CapacidadServicio {
     void verServiciosAsignados();
     // Podrías agregar este para validar el rango:
     boolean puedeRealizarServicio(Integer rangoServicio);
}
