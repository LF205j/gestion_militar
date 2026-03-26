package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadGestionCompania;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuartel;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuerpo;
import com.defensa.gestion_militar.DTOs.*;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.RealizaServicio;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Mappers.CuerpoMapper;
import com.defensa.gestion_militar.Mappers.ServiciosMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Cuerpos;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuerpoService {

    @Autowired
    Repo_Cuerpos repoCuerpos;

    @Autowired
    private CuerpoMapper cuerpoMapper;

    @Autowired
    private Repo_Usuarios repoUsuarios;

    public List<CuerpoDTO> obtenerTodosLosCuerpos() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repoCuerpos.findAll().stream()
                .map(cuerpoMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CuerpoDTO obtenerCuerpoPorId(Long idCuerpo){
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad
//        if (ejecutor instanceof CapacidadGestionCuerpo oficialConMando) {
//
//            // 3. El Oficial valida la intención de consulta
//            oficialConMando.consultarCuerpo(idCuerpo);
//
//            // 4. Ejecutamos la búsqueda técnica (tu método original)
            return repoCuerpos.findById(idCuerpo)
                    .map(cuerpoMapper::toDTO)
                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado con ID: " + idCuerpo));
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: No tiene permisos para consultar cuerpos militares.");
//        }
    }

    public void eliminarCuerpo(Long id){
        if (!repoCuerpos.existsById(id)){
            throw new RuntimeException("El cuerpo con ID " + id + " no existe.");
        }
        repoCuerpos.deleteById(id);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Oficial no encontrado"));
//
//        // 2. ¿Tiene la capacidad de gestionar usuarios?
//        if (ejecutor instanceof CapacidadGestionCuerpo oficialConMando) {
//
//            // 3. Le pedimos al oficial que valide la operación (Lógica de Negocio)
//            oficialConMando.eliminarCuerpo(id);
//
//            // 4. Si llegamos aquí sin excepciones, operamos en la DB (Lógica de Persistencia)
//            if (repoCuerpos.existsById(id)) {
//                repoCuerpos.deleteById(id);
//            } else {
//                throw new RuntimeException("La compania que desea borrar ya no existe.");
//            }
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Usted no tiene rango para gestionar usuarios.");
//        }
    }

    public void guardarCuerpo(CuerpoDTO dto) {
        Cuerpo entidad = Cuerpo.builder().denominacion_cuerpo(dto.getDenominacionCuerpo()).build();

        repoCuerpos.save(entidad);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
//        if (ejecutor instanceof CapacidadGestionCuerpo oficialConMando) {
//
//            // 3. La entidad valida los datos del DTO según reglas militares
//            oficialConMando.guardarCuerpo(dto);
//
//            // 4. Si no hubo excepciones, procedemos con la persistencia
//            Cuerpo entidad = Cuerpo.builder().denominacion_cuerpo(dto.getDenominacionCuerpo()).build();
//
//
//            repoCuerpos.save(entidad);
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: Su rango no permite crear cuarteles.");
//        }
    }

    // Convertir de Entidad a DTO para MOSTRAR
    public CuerpoDTO obtenerCuerpo(Long id) {
        Cuerpo entidad = repoCuerpos.findById(id).orElseThrow();

        return CuerpoDTO.builder().id(entidad.getId()).denominacionCuerpo(entidad.getDenominacion_cuerpo()).build();
    }
    public void editarCuerpo(Long idCuerpo, CuerpoDTO dto) {
        // 1. Buscamos la entidad original por ID
        Cuerpo cuerpoExistente = repoCuerpos.findById(idCuerpo).orElseThrow(() -> new RuntimeException("Cuerpo no encontrado"));
        cuerpoExistente.setDenominacion_cuerpo(dto.getDenominacionCuerpo());
        repoCuerpos.save(cuerpoExistente);
//        Usuario ejecutor = repoUsuarios.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        // 2. Verificar capacidad
//        if (ejecutor instanceof CapacidadGestionCuerpo oficialConMando) {
//
//            // 3. Buscar la entidad a modificar
//            Cuerpo cuerpoExistente = repoCuerpos.findById(idCuerpo)
//                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado"));
//
//            // 4. VALIDACIÓN: El Oficial comprueba si la edición es lícita
//            oficialConMando.editarCuerpo(idCuerpo, dto);
//
//            // 5. PERSISTENCIA: Aplicamos los cambios que venían en el DTO
//            cuerpoExistente.setDenominacion_cuerpo(dto.getDenominacionCuerpo());
//
//            repoCuerpos.save(cuerpoExistente);
//
//        } else {
//            throw new RuntimeException("No tiene permisos para editar cuerpos militares.");
//        }
    }






}
