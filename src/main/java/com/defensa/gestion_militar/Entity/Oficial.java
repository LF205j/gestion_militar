package com.defensa.gestion_militar.Entity;

import com.defensa.gestion_militar.Capacidad.CapacidadOficial;
import com.defensa.gestion_militar.Capacidad.CapacidadServicio;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OFICIAL")
public class Oficial extends Usuario implements CapacidadOficial {
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