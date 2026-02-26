package com.defensa.gestion_militar.Repositorios;

import com.defensa.gestion_militar.Entity.Servicios;
import com.defensa.gestion_militar.Entity.Usuario;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo_Usuarios extends JpaRepository<Usuario,Long> {

//    private String email;
//    private String contrasenia;

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmailAndContrasenia(String email,String contrasenia);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Usuario> findByCuartelId(Long cuartelId);
    List<Usuario> findByCuerpoId(Long cuerpoId);
    List<Usuario> findByCompaniaId(Long companiaId);
    List<Usuario> findByServiciosRealizados_Servicio_Id(Long servicioId);


}
