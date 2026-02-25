package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.RealizaServicio;
import com.defensa.gestion_militar.Mappers.CuartelMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Cuarteles;
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

    public List<CuartelDTO> obtenerTodosLosCuarteles() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repoCuarteles.findAll().stream()
                .map(cuartelMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CuartelDTO obtenerUserPorId(Long id){
        return repoCuarteles.findById(id)
                .map(cuartelMapper::toDTO)
                .orElse(null);
    }

    public void eliminarCuartel(Long id){
        if (!repoCuarteles.existsById(id)){
            throw new RuntimeException("El cuartel con ID " + id + " no existe.");
        }
        repoCuarteles.deleteById(id);
    }

    public void guardarCuartel(CuartelDTO dto) {
        Cuartel entidad = Cuartel.builder().nombre_cuartel(dto.getNombreCuartel()).ubicacion(dto.getUbicacion()).build();

        repoCuarteles.save(entidad);
    }

    // Convertir de Entidad a DTO para MOSTRAR
    public CuartelDTO obtenerCuerpo(Long id) {
        Cuartel entidad = repoCuarteles.findById(id).orElseThrow();

        return CuartelDTO.builder().id(entidad.getId()).nombreCuartel(entidad.getNombre_cuartel()).ubicacion(entidad.getUbicacion()).build();
    }

    public void editarCuartel(Long id,CuartelDTO dto){
        Cuartel cuartel=repoCuarteles.findById(id).orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));;
        cuartel.setNombre_cuartel(dto.getNombreCuartel());
        cuartel.setUbicacion(dto.getUbicacion());
        repoCuarteles.save(cuartel);


    }



}
