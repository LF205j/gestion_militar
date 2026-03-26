package com.defensa.gestion_militar.Entity;

import com.defensa.gestion_militar.Capacidad.CapacidadServicio;
import com.defensa.gestion_militar.Capacidad.CapacidadSuboficial;
import com.defensa.gestion_militar.DTOs.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SUBOFICIAL")
public class Suboficial extends Usuario  {
    @Override
    public void mostrarMenu() {

    }
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
//    public void agregarCompania(CompaniaDTO dto) {
//        if (dto.getActividadPrincipal() == null || dto.getActividadPrincipal().length() < 5) {
//            throw new RuntimeException("El nombre del cuartel es demasiado corto para ser oficial.");
//        }
//
//        // REGLA DE NEGOCIO: Validar ubicación
//        if (dto.getActividadPrincipal() == null || dto.getActividadPrincipal().isEmpty()) {
//            throw new RuntimeException("No se puede registrar un cuartel sin ubicación geográfica.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la creación del cuartel: " + dto.getActividadPrincipal());
//    }
//
//    @Override
//    public void eliminarCompania(Long idCompania) {
//        if (this.getCompania() != null && this.getCompania().getId().equals(idCompania)) {
//            throw new RuntimeException("Denegado: No puedes eliminar el cuartel al que perteneces actualmente.");
//        }
//
//        // Si la regla se cumple, el método termina normalmente (autorizado)
//        System.out.println("Oficial " + this.getApellido() + " autorizó la eliminación de la compania ID: " + idCompania);
//    }
//
//    @Override
//    public void editarCompania(Long id, CompaniaDTO dto) {
//        if (dto.getActividadPrincipal() == null || dto.getActividadPrincipal().trim().isEmpty()) {
//            throw new RuntimeException("La nueva denominación del cuerpo no puede estar vacía.");
//        }
//
//        // Ejemplo: No permitir que un oficial edite un cuerpo si el ID es 0 o negativo
//        if (id <= 0) {
//            throw new RuntimeException("ID de Compania inválido para edición.");
//        }
//
//        System.out.println("Validación de edición de cuerpo exitosa por Oficial: " + this.getApellido());
//    }
//
//    @Override
//    public void asignarCompania(Long idUser, Long idCompania) {
//        // REGLA DE NEGOCIO: Un suboficial no puede asignarse a sí mismo
//        if (this.getId().equals(idUser)) {
//            throw new RuntimeException("Denegado: No puedes realizar una auto-asignación de compañía.");
//        }
//
//        // REGLA DE NEGOCIO: Validar que el ID de compañía sea coherente
//        if (idCompania == null || idCompania <= 0) {
//            throw new RuntimeException("Error: El ID de la compañía de destino no es válido.");
//        }
//
//        // Nota: La validación de que idUser sea un SOLDADO se delega al Service
//        // ya que este método solo recibe los IDs y no tiene acceso a la DB.
//
//        System.out.println("Suboficial " + this.getApellido() + " validó los parámetros de asignación para el usuario " + idUser);
//    }
//
//    @Override
//    public void consultarCompaniaPorId(Long idCompania) {
//
//    }
//
//    @Override
//    public void agregarCuartel(CuartelDTO dto) {
//        if (dto.getNombreCuartel() == null || dto.getNombreCuartel().length() < 5) {
//            throw new RuntimeException("El nombre del cuartel es demasiado corto para ser oficial.");
//        }
//
//        // REGLA DE NEGOCIO: Validar ubicación
//        if (dto.getUbicacion() == null || dto.getUbicacion().isEmpty()) {
//            throw new RuntimeException("No se puede registrar un cuartel sin ubicación geográfica.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la creación del cuartel: " + dto.getNombreCuartel());
//    }
//
//    @Override
//    public void eliminarCuartel(Long idCuartel) {
//        if (this.getCuartel() != null && this.getCuartel().getId().equals(idCuartel)) {
//            throw new RuntimeException("Denegado: No puedes eliminar el cuartel al que perteneces actualmente.");
//        }
//
//        // Si la regla se cumple, el método termina normalmente (autorizado)
//        System.out.println("Oficial " + this.getApellido() + " autorizó la eliminación del cuartel ID: " + idCuartel);
//    }
//
//    @Override
//    public void editarCuartel(Long id, CuartelDTO dto) {
//        if (dto.getNombreCuartel() == null || dto.getNombreCuartel().trim().isEmpty()) {
//            throw new RuntimeException("La nueva Nombre del cuartel no puede estar vacía.");
//        }
//
//        // Ejemplo: No permitir que un oficial edite un cuerpo si el ID es 0 o negativo
//        if (id <= 0) {
//            throw new RuntimeException("ID de cuerpo inválido para edición.");
//        }
//
//        System.out.println("Validación de edición de Cuartel exitosa por Oficial: " + this.getApellido());
//    }
//
//    @Override
//    public void asignarCuartel(Long idUser, Long idCuartel) {
//
//    }
//
//    @Override
//    public void consultarCuartelPorId(Long idCompania) {
//
//    }
//
//    @Override
//    public void agregarCuerpo(CuerpoDTO dto) {
//        if (dto.getDenominacionCuerpo() == null || dto.getDenominacionCuerpo().length() < 5) {
//            throw new RuntimeException("El nombre del Cuerpo es demasiado corto para ser oficial.");
//        }
//
//        // REGLA DE NEGOCIO: Validar ubicación
//        if (dto.getDenominacionCuerpo() == null || dto.getDenominacionCuerpo().isEmpty()) {
//            throw new RuntimeException("No se puede registrar un Cuerpo sin ubicación geográfica.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la creación del cuerpo: " + dto.getDenominacionCuerpo());
//    }
//
//    @Override
//    public void eliminarCuerpo(long idCuerpo) {
//        // REGLA DE NEGOCIO: Un oficial no puede borrar su propio cuartel de residencia
//        if (this.getCuerpo() != null && this.getCuerpo().getId().equals(idCuerpo)) {
//            throw new RuntimeException("Denegado: No puedes eliminar el cuartel al que perteneces actualmente.");
//        }
//
//        // Si la regla se cumple, el método termina normalmente (autorizado)
//        System.out.println("Oficial " + this.getApellido() + " autorizó la eliminación del cuartel ID: " + idCuerpo);
//
//    }
//
//    @Override
//    public void editarCuerpo(Long id, CuerpoDTO dto) {
//        // REGLA DE NEGOCIO: Validar que la denominación sea válida
//        if (dto.getDenominacionCuerpo() == null || dto.getDenominacionCuerpo().trim().isEmpty()) {
//            throw new RuntimeException("La nueva denominación del cuerpo no puede estar vacía.");
//        }
//
//        // Ejemplo: No permitir que un oficial edite un cuerpo si el ID es 0 o negativo
//        if (id <= 0) {
//            throw new RuntimeException("ID de cuerpo inválido para edición.");
//        }
//
//        System.out.println("Validación de edición de cuerpo exitosa por Oficial: " + this.getApellido());
//    }
//
//    @Override
//    public void asignarCuerpo(Long idUser, Long idCuerpo) {
//
//    }
//
//    @Override
//    public void consultarCuerpoPorId(Long idCompania) {
//
//    }
//
//    @Override
//    public void agregarServicio(ServiciosDTO dto) {
//
//    }
//
//    @Override
//    public void eliminarServicio(Long id) {
//
//    }
//
//    @Override
//    public void editarServicio(Long id, ServiciosDTO dto) {
//
//    }
//
//    @Override
//    public void asignarServicio(Long idUser, Long idCuerpo) {
//
//    }
//
//    @Override
//    public void consultarServicioPorId(Long idCompania) {
//
//    }
//
//    @Override
//    public void agregarUsuario(UsuarioDTO dto) {
//
//    }
//
//    @Override
//    public void eliminarUsuario(Long id) {
//
//    }
//
//    @Override
//    public void editarUsuario(Long id, UsuarioDTO dto, String nuevaPass) {
//
//    }
//
//    @Override
//    public void consultarUsuarioPorId(Long idCompania) {
//
//    }
//
//    @Override
//    public void verMiCuartel() {
//
//    }
//
//    @Override
//    public void verMiCompania() {
//
//    }
//
//    @Override
//    public void verMiCuerpo() {
//
//    }
//
//    @Override
//    public void consultarCompanias() {
//
//    }
//
//    @Override
//    public void consultarCuarteles() {
//
//    }
//
//
//    @Override
//    public void consultarCuerpos() {
//
//    }
//
//
//    @Override
//    public void consultarServicios() {
//
//    }
//
//    @Override
//    public void consultarUsuarios() {
//
//    }


}
