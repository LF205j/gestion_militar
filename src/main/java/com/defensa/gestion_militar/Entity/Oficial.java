package com.defensa.gestion_militar.Entity;

import com.defensa.gestion_militar.Capacidad.CapacidadOficial;
import com.defensa.gestion_militar.Capacidad.CapacidadServicio;
import com.defensa.gestion_militar.DTOs.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("OFICIAL")
public class Oficial extends Usuario  {
    @Override
    public void mostrarMenu() {

    }
    //implements CapacidadOficial
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
//        // REGLA DE NEGOCIO: Validar que la denominación sea válida
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
//        if (this.getId().equals(idUser)) {
//            throw new RuntimeException("Denegado: No puedes reasignarte de servicio a ti mismo.");
//        }
//
//        // REGLA DE NEGOCIO: Podrías validar que el ID de cuartel sea válido
//        if (idCompania == null || idCompania <= 0) {
//            throw new RuntimeException("El ID del cuartel de destino no es válido.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la asignación del usuario " + idUser + " a la compania " + idCompania);
//    }
//
////    @Override
////    public void consultarCompania(Long idCompania) {
////        if (idCompania <= 0) {
////            throw new RuntimeException("El identificador del cuerpo para la consulta es inválido.");
////        }
////
////        System.out.println("El Oficial " + this.getApellido() + " está consultando el cuerpo ID: " + idCompania);
////    }
//
//    @Override
//    public void agregarCuartel(CuartelDTO dto) {
//        // REGLA DE NEGOCIO: Ejemplo, validar que el nombre no sea ridículamente corto
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
//        // REGLA DE NEGOCIO: Un oficial no puede borrar su propio cuartel de residencia
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
//        // REGLA DE NEGOCIO: Ejemplo, un oficial no puede asignarse a sí mismo
//        // a un nuevo cuartel sin autorización superior (o simplemente bloquearlo)
//        if (this.getId().equals(idUser)) {
//            throw new RuntimeException("Denegado: No puedes reasignarte de cuartel a ti mismo.");
//        }
//
//        // REGLA DE NEGOCIO: Podrías validar que el ID de cuartel sea válido
//        if (idCuartel == null || idCuartel <= 0) {
//            throw new RuntimeException("El ID del cuartel de destino no es válido.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la asignación del usuario " + idUser + " al cuartel " + idCuartel);
//    }
//
//    @Override
//    public void consultarCuartelPorId(Long idCuartel) {
//        if (idCuartel <= 0) {
//            throw new RuntimeException("El identificador del cuerpo para la consulta es inválido.");
//        }
//
//        System.out.println("El Oficial " + this.getApellido() + " está consultando el cuerpo ID: " + idCuartel);
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
//    public void agregarUsuario(UsuarioDTO dto) {
//        if (dto.getNombre() == null || dto.getNombre().length() < 5) {
//            throw new RuntimeException("El nombre del usuario es demasiado corto para ser oficial.");
//        }
//
//        // REGLA DE NEGOCIO: Validar ubicación
//        if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
//            throw new RuntimeException("No se puede registrar un cuartel sin ubicación geográfica.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la creación del usuario: " + dto.getNombre());
//    }
//
//    @Override
//    public void eliminarUsuario(Long idABorrar) {
//        // REGLA 1: No auto-eliminación
//        if (this.getId().equals(idABorrar)) {
//            throw new RuntimeException("Error de Seguridad: Un oficial no puede eliminarse a sí mismo.");
//        }
//
//        // REGLA 2: (Opcional) Podrías validar que el oficial tenga cierta antigüedad
//        // o que no esté borrando a otro Oficial de mayor rango si tuvieras esa lógica.
//
//        System.out.println("El Oficial " + this.getApellido() + " ha validado la baja del usuario " + idABorrar);
//    }
//
//    @Override
//    public void editarUsuario(Long id, UsuarioDTO dto, String nuevaPass) {
//        // REGLA 1: Validar que el tipo de usuario sea correcto
//        List<String> rangosValidos = List.of("SOLDADO", "SUBOFICIAL", "OFICIAL");
//        String tipoRecibido = (dto.getTipoUsuario() != null) ? dto.getTipoUsuario().toUpperCase() : "";
//
//        if (!rangosValidos.contains(tipoRecibido)) {
//            throw new RuntimeException("Rango no permitido. Debe ser: SOLDADO, SUBOFICIAL u OFICIAL.");
//        }
//
//        // REGLA 2: Validar que no se envíen campos esenciales vacíos
//        if (dto.getNombre() == null || dto.getNombre().isBlank() ||
//                dto.getApellido() == null || dto.getApellido().isBlank()) {
//            throw new RuntimeException("El nombre y apellido son obligatorios.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " validó la edición del usuario ID: " + id);
//    }
//
//    @Override
//    public void consultarUsuarioPorId(Long idUsuario) {
//        if (idUsuario <= 0) {
//            throw new RuntimeException("El identificador del cuerpo para la consulta es inválido.");
//        }
//
//        System.out.println("El Oficial " + this.getApellido() + " está consultando el cuerpo ID: " + idUsuario);
//    }
//
//    @Override
//    public void eliminarCuerpo(long id) {
//        // REGLA DE NEGOCIO: Un oficial no puede borrar su propio cuartel de residencia
//        if (this.getCuerpo() != null && this.getCuerpo().getId().equals(id)) {
//            throw new RuntimeException("Denegado: No puedes eliminar el cuartel al que perteneces actualmente.");
//        }
//
//        // Si la regla se cumple, el método termina normalmente (autorizado)
//        System.out.println("Oficial " + this.getApellido() + " autorizó la eliminación del cuartel ID: " + id);
//    }
//
//
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
//        // REGLA DE NEGOCIO: Ejemplo, un oficial no puede asignarse a sí mismo
//        // a un nuevo cuartel sin autorización superior (o simplemente bloquearlo)
//        if (this.getId().equals(idUser)) {
//            throw new RuntimeException("Denegado: No puedes reasignarte de cuartel a ti mismo.");
//        }
//
//        // REGLA DE NEGOCIO: Podrías validar que el ID de cuartel sea válido
//        if (idCuerpo == null || idCuerpo <= 0) {
//            throw new RuntimeException("El ID del cuartel de destino no es válido.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la asignación del usuario " + idUser + " al Cuerpo " + idCuerpo);
//    }
//
//    @Override
//    public void consultarCuerpoPorId(Long idCuerpo) {
//        if (idCuerpo <= 0) {
//            throw new RuntimeException("El identificador del cuerpo para la consulta es inválido.");
//        }
//
//        System.out.println("El Oficial " + this.getApellido() + " está consultando el cuerpo ID: " + idCuerpo);
//    }
//
//    @Override
//    public void agregarServicio(ServiciosDTO dto) {
//        if (dto.getNombre_servicio() == null || dto.getNombre_servicio().length() < 5) {
//            throw new RuntimeException("El nombre del Cuerpo es demasiado corto para ser oficial.");
//        }
//
//        // REGLA DE NEGOCIO: Validar ubicación
//        if (dto.getDescripcion_servicio() == null || dto.getNombre_servicio().isEmpty()) {
//            throw new RuntimeException("No se puede registrar un Cuerpo sin ubicación geográfica.");
//        }
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la creación del cuerpo: " + dto.getDescripcion_servicio());
//    }
//
//    @Override
//    public void eliminarServicio(Long idServicio) {
//        // REGLA DE NEGOCIO: No eliminar un servicio si el oficial lo tiene en su historial activo
//        // Filtramos en la lista de servicios realizados del oficial
//        boolean loEstoyRealizando = getServiciosRealizados().stream()
//                .anyMatch(rs -> rs.getServicio().getId().equals(idServicio));
//
//        if (loEstoyRealizando) {
//            throw new RuntimeException("Denegado: No puedes eliminar un servicio que tienes asignado personalmente.");
//        }
//
//        // Si quieres ser más estricto, podrías validar si el servicio existe en el sistema
//        // (aunque eso suele ir en el Service, aquí validamos la "voluntad" del Oficial).
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la eliminación del Servicio ID: " + idServicio);
//    }
//
//    @Override
//    public void editarServicio(Long id, ServiciosDTO dto) {
//        if (dto.getNombre_servicio() == null || dto.getNombre_servicio().trim().isEmpty()) {
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
//    public void asignarServicio(Long idUser, Long idServicio) {
//        // REGLA DE NEGOCIO: Ejemplo, un oficial no puede asignarse a sí mismo
//        // a un nuevo cuartel sin autorización superior (o simplemente bloquearlo)
//        if (this.getId().equals(idUser)) {
//            throw new RuntimeException("Denegado: No puedes reasignarte de servicio a ti mismo.");
//        }
//
//        // REGLA DE NEGOCIO: Podrías validar que el ID de cuartel sea válido
//        if (idServicio == null || idServicio <= 0) {
//            throw new RuntimeException("El ID del cuartel de destino no es válido.");
//        }
//
//
//        System.out.println("Oficial " + this.getApellido() + " autorizó la asignación del usuario " + idUser + " al servicio " + idServicio);
//    }
//
//    @Override
//    public void consultarServicioPorId(Long idServicio) {
//        if (idServicio <= 0) {
//            throw new RuntimeException("El identificador del cuerpo para la consulta es inválido.");
//        }
//
//        System.out.println("El Oficial " + this.getApellido() + " está consultando el servicio ID: " + idServicio);
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
//
//
////    @Override
////    public void agregarCuerpo(CuerpoDTO dto) {
////
////    }
////
////    @Override
////    public void agregarUsuario(UsuarioDTO dto) {
////
////    }
//
//    @Override
//    public void consultarCompaniaPorId(Long id) {
//        if (id <= 0) {
//            throw new RuntimeException("El identificador del cuerpo para la consulta es inválido.");
//        }
//
//        System.out.println("El Oficial " + this.getApellido() + " está consultando el cuerpo ID: " + id);
//    }
//
//    @Override
//    public void consultarCompanias() {
//
//    }
//
////    @Override
////    public void consultarCuartelPorId(Long id) {
////
////    }
//
//    @Override
//    public void consultarCuarteles() {
//
//    }
//
////    @Override
////    public void consultarCuerpoPorId(Long id) {
////
////    }
//
//    @Override
//    public void consultarCuerpos() {
//
//    }
//
////    @Override
////    public void consultarServicioPorId(Long id) {
////
////    }
//
//    @Override
//    public void consultarServicios() {
//
//    }
//
////    @Override
////    public void consultarUsuarioPorId(Long id) {
////
////    }
//
//    @Override
//    public void consultarUsuarios() {
//
//    }


}