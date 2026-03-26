package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuartel;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuerpo;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionServicio;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.ServiciosDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.Servicios;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Mappers.ServiciosMapper;
import com.defensa.gestion_militar.Mappers.UsuarioMapper;
import com.defensa.gestion_militar.Repositorios.Repo_RealizarServicio;
import com.defensa.gestion_militar.Repositorios.Repo_Servicios;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    @Autowired
    private Repo_Servicios repo_serv;

    @Autowired
    private ServiciosMapper serviciosMapper;

    @Autowired
    private Repo_RealizarServicio repo_realizarServicio;

    @Autowired
    private Repo_Usuarios repoUsuarios;

    public List<ServiciosDTO> obtenerTodosLosServicios() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repo_serv.findAll().stream()
                .map(serviciosMapper::toDTO)
                .collect(Collectors.toList());
    }
    public ServiciosDTO obtenerServicioPorId(Long idServicio){
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad
//        if (ejecutor instanceof CapacidadGestionServicio oficialConMando) {
//
//            // 3. El Oficial valida la intención de consulta
//            oficialConMando.consultarServicio(idServicio);
//
//            // 4. Ejecutamos la búsqueda técnica (tu método original)
            return repo_serv.findById(idServicio)
                    .map(serviciosMapper::toDTO)
                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado con ID: " + idServicio));
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: No tiene permisos para consultar Servicios militares.");
//        }
    }


    // GUARDAR (Crear desde cero)
    public void guardarServicio(ServiciosDTO dto) {
        Servicios entidad = serviciosMapper.toEntity(dto);
        repo_serv.save(entidad);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
//        if (ejecutor instanceof CapacidadGestionServicio oficialConMando) {
//
//            // 3. La entidad valida los datos del DTO según reglas militares
//            oficialConMando.guardarServicio(dto);
//
//            // 4. Si no hubo excepciones, procedemos con la persistencia
//            Servicios entidad = Servicios.builder().descripcion_servicio(dto.getDescripcion_servicio()).nombre_servicio(dto.getNombre_servicio()).rango(dto.getRango()).build();
//
//
//            repo_serv.save(entidad);
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Su rango no permite crear cuarteles.");
//        }
    }

    // ELIMINAR
    public void eliminarServicio(Long id) {

        repo_serv.deleteById(id);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Oficial no encontrado"));
//
//        // 2. ¿Tiene la capacidad de gestionar usuarios?
//        if (ejecutor instanceof CapacidadGestionServicio oficialConMando) {
//
//            // 3. Le pedimos al oficial que valide la operación (Lógica de Negocio)
//            oficialConMando.eliminarServicio(id);
//
//            // 4. Si llegamos aquí sin excepciones, operamos en la DB (Lógica de Persistencia)
//            if (repo_serv.existsById(id)) {
//                repo_serv.deleteById(id);
//            } else {
//                throw new RuntimeException("La compania que desea borrar ya no existe.");
//            }
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Usted no tiene rango para gestionar usuarios.");
//        }
    }

    public void editarServicio(Long idServicio,ServiciosDTO dto){
        Servicios servicios=repo_serv.findById(idServicio).orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        servicios.setDescripcion_servicio(dto.getDescripcion_servicio());
        servicios.setNombre_servicio(dto.getNombre_servicio());
        servicios.setRango(dto.getRango());
        repo_serv.save(servicios);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificar capacidad
//        if (ejecutor instanceof CapacidadGestionServicio oficialConMando) {
//
//            // 3. Buscar la entidad a modificar
//            Servicios servicioExistente = repo_serv.findById(idServicio)
//                    .orElseThrow(() -> new RuntimeException("servicio no encontrado"));
//
//            // 4. VALIDACIÓN: El Oficial comprueba si la edición es lícita
//            oficialConMando.editarServicio(idServicio, dto);
//
//            // 5. PERSISTENCIA: Aplicamos los cambios que venían en el DTO
//            servicioExistente.setDescripcion_servicio(dto.getDescripcion_servicio());
//            servicioExistente.setNombre_servicio(dto.getNombre_servicio());
//            servicioExistente.setRango(dto.getRango());
//
//            repo_serv.save(servicioExistente);
//
//        } else {
//            throw new RuntimeException("No tiene permisos para editar cuerpos militares.");
//        }
    }
}
