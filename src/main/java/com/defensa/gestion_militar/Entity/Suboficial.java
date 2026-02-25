package com.defensa.gestion_militar.Entity;

import com.defensa.gestion_militar.Capacidad.CapacidadServicio;
import com.defensa.gestion_militar.Capacidad.CapacidadSuboficial;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SUBOFICIAL")
public class Suboficial extends Usuario implements CapacidadSuboficial {
    // Un soldado solo puede ver sus servicios
    @Override
    public void verServiciosAsignados() {
        // Lógica para listar servicios desde su propia lista
    }

    @Override
    public boolean puedeRealizarServicio(Integer rangoServicio) {
        return false;
    }

    @Override
    public void mostrarMenu() {

    }
}
