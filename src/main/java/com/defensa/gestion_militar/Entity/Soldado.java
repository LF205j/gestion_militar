package com.defensa.gestion_militar.Entity;

import com.defensa.gestion_militar.Capacidad.CapacidadServicio;
import com.defensa.gestion_militar.Capacidad.CapacidadSoldado;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SOLDADO")
public class Soldado extends Usuario  {
    @Override
    public void mostrarMenu() {

    }
    //implements CapacidadSoldado
    // Un soldado solo puede ver sus servicios
//    @Override
//    public void verServiciosAsignados() {
//        // Lógica para listar servicios desde su propia lista
//    }
//
//    @Override
//    public boolean puedeRealizarServicio(Integer rangoServicio) {
//        return false;
//    }
//
//    @Override
//    public void mostrarMenu() {
//
//    }
//
//    @Override
//    public void verMiCuartel() {
//        if (this.getCuartel() == null) {
//            throw new RuntimeException("Denegado: No tienes una Cuartel asignada actualmente.");
//        }
//        System.out.println("Usuario " + this.getApellido() + " accediendo a datos de su compañía.");
//    }
//
//    @Override
//    public void verMiCompania() {
//        if (this.getCompania() == null) {
//            throw new RuntimeException("Denegado: No tienes una compañía asignada actualmente.");
//        }
//        System.out.println("Usuario " + this.getApellido() + " accediendo a datos de su compañía.");
//    }
//
//    @Override
//    public void verMiCuerpo() {
//        if (this.getCuerpo() == null) {
//            throw new RuntimeException("Denegado: No tienes una cuerpo asignada actualmente.");
//        }
//        System.out.println("Usuario " + this.getApellido() + " accediendo a datos de su compañía.");
//    }
}
