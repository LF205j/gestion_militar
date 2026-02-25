package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.DTOs.*;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.RealizaServicio;
import com.defensa.gestion_militar.Mappers.CuerpoMapper;
import com.defensa.gestion_militar.Mappers.ServiciosMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Cuerpos;
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

    public List<CuerpoDTO> obtenerTodosLosCuerpos() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repoCuerpos.findAll().stream()
                .map(cuerpoMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CuerpoDTO obtenerCuerpoPorId(Long id){
        return repoCuerpos.findById(id)
                .map(cuerpoMapper::toDTO)
                .orElse(null);
    }

    public void eliminarCuerpo(Long id){
        if (!repoCuerpos.existsById(id)){
            throw new RuntimeException("El cuerpo con ID " + id + " no existe.");
        }
        repoCuerpos.deleteById(id);
    }

    public void guardarCuerpo(CuerpoDTO dto) {
        Cuerpo entidad = Cuerpo.builder().denominacion_cuerpo(dto.getDenominacionCuerpo()).build();

        repoCuerpos.save(entidad);
    }

    // Convertir de Entidad a DTO para MOSTRAR
    public CuerpoDTO obtenerCuerpo(Long id) {
        Cuerpo entidad = repoCuerpos.findById(id).orElseThrow();

        return CuerpoDTO.builder().id(entidad.getId()).denominacionCuerpo(entidad.getDenominacion_cuerpo()).build();
    }
    public void editarCuerpo(Long id, CuerpoDTO dto) {
        // 1. Buscamos la entidad original por ID
        Cuerpo cuerpoExistente = repoCuerpos.findById(id).orElseThrow(() -> new RuntimeException("Cuerpo no encontrado"));
        cuerpoExistente.setDenominacion_cuerpo(dto.getDenominacionCuerpo());
        repoCuerpos.save(cuerpoExistente);
    }






}
