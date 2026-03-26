package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadGestionCompania;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuartel;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuerpo;
import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.Compania;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Mappers.CompaniaMapper;
import com.defensa.gestion_militar.Mappers.CuerpoMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Companias;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompaniaService {
    @Autowired
    private Repo_Companias repoCompanias;

    @Autowired
    private Repo_Usuarios repoUsuarios;

    @Autowired
    private CompaniaMapper companiaMapper;

    public List<CompaniaDTO> obtenerTodasLasCompanias() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repoCompanias.findAll().stream()
                .map(companiaMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CompaniaDTO obtenerCompaniaPorId(Long idCompania){
        return repoCompanias.findById(idCompania)
                .map(companiaMapper::toDTO)
                .orElse(null);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad
//        if (ejecutor instanceof CapacidadGestionCompania oficialConMando) {
//
//            // 3. El Oficial valida la intención de consulta
//            oficialConMando.consultarCompaniaPorId(idCompania);
//
//            // 4. Ejecutamos la búsqueda técnica (tu método original)
//            return repoCompanias.findById(idCompania)
//                    .map(companiaMapper::toDTO)
//                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado con ID: " + idCompania));
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: No tiene permisos para consultar cuerpos militares.");
//        }
    }
    public void eliminarCompania(Long idAdmin,Long id){
//        if (!repoCompanias.existsById(id)){
//            throw new RuntimeException("El compania con ID " + id + " no existe.");
//        }
//        repoCompanias.deleteById(id);
        Usuario ejecutor = repoUsuarios.findById(idAdmin)
                .orElseThrow(() -> new RuntimeException("Oficial no encontrado"));

        // 2. ¿Tiene la capacidad de gestionar usuarios?
        if (ejecutor instanceof CapacidadGestionCompania oficialConMando) {

            // 3. Le pedimos al oficial que valide la operación (Lógica de Negocio)
            oficialConMando.eliminarCompania(id);

            // 4. Si llegamos aquí sin excepciones, operamos en la DB (Lógica de Persistencia)
            if (repoCompanias.existsById(id)) {
                repoCompanias.deleteById(id);
            } else {
                throw new RuntimeException("La compania que desea borrar ya no existe.");
            }

        } else {
            throw new RuntimeException("Acceso Denegado: Usted no tiene rango para gestionar usuarios.");
        }
    }

    public void guardarCompania(CompaniaDTO dto) {
        Compania entidad = Compania.builder().actividad_principal(dto.getActividadPrincipal()).build();

        repoCompanias.save(entidad);

//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
//        if (ejecutor instanceof CapacidadGestionCompania oficialConMando) {
//
//            // 3. La entidad valida los datos del DTO según reglas militares
//            oficialConMando.agregarCompania(dto);
//
//            // 4. Si no hubo excepciones, procedemos con la persistencia
//            Compania entidad = Compania.builder().actividad_principal(dto.getActividadPrincipal())
//                    .build();
//
//            repoCompanias.save(entidad);
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Su rango no permite crear cuarteles.");
//        }
    }

    // Convertir de Entidad a DTO para MOSTRAR
    public CompaniaDTO obtenerCompania(Long id) {
        Compania entidad = repoCompanias.findById(id).orElseThrow();

        return CompaniaDTO.builder().id(entidad.getId()).actividadPrincipal(entidad.getActividad_principal()).build();
    }
    public void editarCompania(Long idCompania,CompaniaDTO dto){
        Compania compania=repoCompanias.findById(idCompania).orElseThrow(() -> new RuntimeException("Compania no encontrado"));;
        compania.setActividad_principal(dto.getActividadPrincipal());
        repoCompanias.save(compania);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificar capacidad
//        if (ejecutor instanceof CapacidadGestionCompania oficialConMando) {
//
//            // 3. Buscar la entidad a modificar
//            Compania companiaExistente = repoCompanias.findById(idCompania)
//                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado"));
//
//            // 4. VALIDACIÓN: El Oficial comprueba si la edición es lícita
//            oficialConMando.editarCompania(idCompania, dto);
//
//            // 5. PERSISTENCIA: Aplicamos los cambios que venían en el DTO
//            companiaExistente.setActividad_principal(dto.getActividadPrincipal());
//
//            repoCompanias.save(companiaExistente);
//
//        } else {
//            throw new RuntimeException("No tiene permisos para editar cuerpos militares.");
//        }
    }
}
