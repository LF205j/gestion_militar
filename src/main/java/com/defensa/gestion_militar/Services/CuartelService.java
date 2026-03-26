package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuartel;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuerpo;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionUsuario;
import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.RealizaServicio;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Mappers.CuartelMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Cuarteles;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuartelService {

    @Autowired
    private Repo_Cuarteles repoCuarteles;

    @Autowired
    private CuartelMapper cuartelMapper;

    @Autowired
    private Repo_Usuarios repoUsuarios;

    public List<CuartelDTO> obtenerTodosLosCuarteles() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repoCuarteles.findAll().stream()
                .map(cuartelMapper::toDTO)
                .collect(Collectors.toList());
    }
//    public CuartelDTO obtenerCuartelPorId(Long idAdmin,Long idCuartel){
////        return repoCuarteles.findById(id)
////                .map(cuartelMapper::toDTO)
////                .orElse(null);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad
//        if (ejecutor instanceof CapacidadGestionCuartel oficialConMando) {
//
//            // 3. El Oficial valida la intención de consulta
//            oficialConMando.consultarCuartel(idCuartel);
//
//            // 4. Ejecutamos la búsqueda técnica (tu método original)
//            return repoCuarteles.findById(idCuartel)
//                    .map(cuartelMapper::toDTO)
//                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado con ID: " + idCuartel));
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: No tiene permisos para consultar cuerpos militares.");
//        }
//    }
    public CuartelDTO obtenerCuartelPorId(Long idCuartel){
            return repoCuarteles.findById(idCuartel)
                    .map(cuartelMapper::toDTO)
                    .orElse(null);
    }

    public void eliminarCuartel(Long id){
        if (!repoCuarteles.existsById(id)){
            throw new RuntimeException("El cuartel con ID " + id + " no existe.");
        }
        repoCuarteles.deleteById(id);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Oficial no encontrado"));
//
//        // 2. ¿Tiene la capacidad de gestionar usuarios?
//        if (ejecutor instanceof CapacidadGestionCuartel oficialConMando) {
//
//            // 3. Le pedimos al oficial que valide la operación (Lógica de Negocio)
//            oficialConMando.eliminarCuartel(id);
//
//            // 4. Si llegamos aquí sin excepciones, operamos en la DB (Lógica de Persistencia)
//            if (repoCuarteles.existsById(id)) {
//                repoCuarteles.deleteById(id);
//            } else {
//                throw new RuntimeException("El usuario que desea borrar ya no existe.");
//            }
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Usted no tiene rango para gestionar usuarios.");
//        }
    }

//    public void guardarCuartel(Long idAdmin,CuartelDTO dto) {
////        Cuartel entidad = Cuartel.builder().nombre_cuartel(dto.getNombreCuartel()).ubicacion(dto.getUbicacion()).build();
////
////        repoCuarteles.save(entidad);
//
//            // 1. Buscamos al oficial que intenta realizar la acción
//            Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                    .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//            // 2. Verificamos si tiene la capacidad de gestionar cuarteles
//            if (ejecutor instanceof CapacidadGestionCuartel oficialConMando) {
//
//                // 3. La entidad valida los datos del DTO según reglas militares
//                oficialConMando.guardarCuartel(dto);
//
//
//
//                // 4. Si no hubo excepciones, procedemos con la persistencia
//                Cuartel entidad = Cuartel.builder()
//                        .nombre_cuartel(dto.getNombreCuartel())
//                        .ubicacion(dto.getUbicacion())
//                        .build();
//
//                repoCuarteles.save(entidad);
//
//            } else {
//                throw new RuntimeException("Acceso Denegado: Su rango no permite crear cuarteles.");
//            }
//
//    }
public void guardarCuartel(CuartelDTO dto) {
        Cuartel entidad = Cuartel.builder().nombre_cuartel(dto.getNombreCuartel()).ubicacion(dto.getUbicacion()).build();

        repoCuarteles.save(entidad);

}

    // Convertir de Entidad a DTO para MOSTRAR
    public CuartelDTO obtenerCuerpo(Long id) {
        Cuartel entidad = repoCuarteles.findById(id).orElseThrow();

        return CuartelDTO.builder().id(entidad.getId()).nombreCuartel(entidad.getNombre_cuartel()).ubicacion(entidad.getUbicacion()).build();
    }

    public void editarCuartel(Long idCuartel,CuartelDTO dto){
        Cuartel cuartel=repoCuarteles.findById(idCuartel).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        cuartel.setNombre_cuartel(dto.getNombreCuartel());
        cuartel.setUbicacion(dto.getUbicacion());
        repoCuarteles.save(cuartel);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificar capacidad
//        if (ejecutor instanceof CapacidadGestionCuartel oficialConMando) {
//
//            // 3. Buscar la entidad a modificar
//            Cuartel cuartelExistente = repoCuarteles.findById(idCuartel)
//                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado"));
//
//            // 4. VALIDACIÓN: El Oficial comprueba si la edición es lícita
//            oficialConMando.editarCuartel(idCuartel, dto);
//
//            // 5. PERSISTENCIA: Aplicamos los cambios que venían en el DTO
//            cuartelExistente.setNombre_cuartel(dto.getNombreCuartel());
//
//            repoCuarteles.save(cuartelExistente);
//
//        } else {
//            throw new RuntimeException("No tiene permisos para editar cuerpos militares.");
//        }


    }



}
