package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.Entity.Compania;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Mappers.CompaniaMapper;
import com.defensa.gestion_militar.Mappers.CuerpoMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Companias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompaniaService {
    @Autowired
    private Repo_Companias repoCompanias;

    @Autowired
    private CompaniaMapper companiaMapper;

    public List<CompaniaDTO> obtenerTodasLasCompanias() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repoCompanias.findAll().stream()
                .map(companiaMapper::toDTO)
                .collect(Collectors.toList());
    }
    public CompaniaDTO obtenerUserPorId(Long id){
        return repoCompanias.findById(id)
                .map(companiaMapper::toDTO)
                .orElse(null);
    }
    public void eliminarCompania(Long id){
        if (!repoCompanias.existsById(id)){
            throw new RuntimeException("El compania con ID " + id + " no existe.");
        }
        repoCompanias.deleteById(id);
    }

    public void guardarCompania(CompaniaDTO dto) {
        Compania entidad = Compania.builder().actividad_principal(dto.getActividadPrincipal()).build();

        repoCompanias.save(entidad);
    }

    // Convertir de Entidad a DTO para MOSTRAR
    public CompaniaDTO obtenerCompania(Long id) {
        Compania entidad = repoCompanias.findById(id).orElseThrow();

        return CompaniaDTO.builder().id(entidad.getId()).actividadPrincipal(entidad.getActividad_principal()).build();
    }
    public void editarCompania(Long id,CompaniaDTO dto){
        Compania compania=repoCompanias.findById(id).orElseThrow(() -> new RuntimeException("Compania no encontrado"));;
        compania.setActividad_principal(dto.getActividadPrincipal());
        repoCompanias.save(compania);
    }
}
