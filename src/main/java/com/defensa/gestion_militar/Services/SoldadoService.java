package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadSoldado;
import org.springframework.stereotype.Service;

@Service
public class SoldadoService implements CapacidadSoldado {
    @Override
    public void verServiciosAsignados() {

    }

    @Override
    public boolean puedeRealizarServicio(Integer rangoServicio) {
        return false;
    }

    @Override
    public void verMiCuartel() {

    }

    @Override
    public void verMiCompania() {

    }

    @Override
    public void verMiCuerpo() {

    }
}
