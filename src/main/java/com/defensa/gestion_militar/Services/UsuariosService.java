package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.DTOs.*;
import com.defensa.gestion_militar.Entity.*;
import com.defensa.gestion_militar.Mappers.CompaniaMapper;
import com.defensa.gestion_militar.Mappers.CuartelMapper;
import com.defensa.gestion_militar.Mappers.CuerpoMapper;
import com.defensa.gestion_militar.Mappers.UsuarioMapper;
import com.defensa.gestion_militar.Repositorios.*;
import jakarta.persistence.DiscriminatorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosService {

    @Autowired
    private Repo_Cuarteles repoCuarteles;
    @Autowired
    private Repo_Cuerpos repoCuerpos;
    @Autowired
    private Repo_Usuarios repo_user;
    @Autowired
    private Repo_Companias repoCompanias;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CuartelMapper cuartelMapper;

    @Autowired
    private CompaniaMapper companiaMapper;

    @Autowired
    private CuerpoMapper cuerpoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Repo_RealizarServicio repo_realizarServicio;

    public List<UsuarioDTO> obtenerTodoElPersonal() {
        // Trae de la tabla 'usuarios' sin importar si son Soldados u Oficiales
        return repo_user.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerUserPorId(Long id){
        return repo_user.findById(id)
                .map(usuarioMapper::toDTO)
                .orElse(null);
    }

    public Usuario validarLogin(String email, String contrasenia)   {
        // Buscamos en el repositorio usando el método que ya definiste
        Usuario  user= repo_user.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado: "));

        if (!passwordEncoder.matches(contrasenia, user.getContrasenia())){
            throw new RuntimeException("Contraseña incorrecta");
        }
        return user;
    }
//    public List<RealizaServicio> obtenerTodosLosServicios(Long id){
//       return repo_realizarServicio.findByUsuarioId(id);
//
//    }
//
//    public List<CuartelDTO> obtenerTodosLosCuarteles() {
//        return repoCuarteles.findAll().stream()
//                .map(cuartelMapper::toDTO) // Necesitarás un CuartelMapper
//                .collect(Collectors.toList());
//    }
//
//    public List<CuerpoDTO> obtenerTodosLosCuerpos() {
//        return repoCuerpos.findAll().stream()
//                .map(cuerpoMapper::toDTO) // Necesitarás un CuartelMapper
//                .collect(Collectors.toList());
//    }
//    public List<CompaniaDTO> obtenerTodasLasCompanias(){
//        return repoCompanias.findAll().stream().map(companiaMapper::toDTO).collect(Collectors.toList());
//    }

    public void eliminarUsuario(Long id){
        if (repo_user.existsById(id)) {
            repo_user.deleteById(id);
        } else {
            throw new RuntimeException("No se pudo eliminar: Usuario no encontrado con ID " + id);
        }
    }
    public void eliminarUsuarioSuboficial(Long id) {
        // 1. Buscamos el usuario por ID
        Usuario usuario = repo_user.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario con ID: " + id));

        // 2. Verificamos la jerarquía: SOLO si es instancia de Soldado se procede
        if (usuario instanceof Soldado) {
            repo_user.deleteById(id);
        } else {
            // 3. Si es Oficial o Suboficial, lanzamos el error de jerarquía
            throw new RuntimeException("Acceso Denegado: Un suboficial solo tiene autoridad para eliminar personal de rango SOLDADO.");
        }
    }

    public void guardarUsuario(UsuarioDTO dto) {
        Usuario entidad;

        // 1. Instanciamos la clase específica
        if ("OFICIAL".equalsIgnoreCase(dto.getTipoUsuario())) {
            entidad = new Oficial();
        } else if ("SUBOFICIAL".equalsIgnoreCase(dto.getTipoUsuario())) {
            entidad = new Suboficial();
        } else {
            entidad = new Soldado();
        }

        // 2. Seteamos los campos comunes (aprovechando la herencia)
        entidad.setNombre(dto.getNombre());
        entidad.setApellido(dto.getApellido());
        entidad.setEmail(dto.getEmail());
        // Se usa el passwordEncoder inyectado en el servicio
        entidad.setContrasenia(passwordEncoder.encode("12345"));

        // 3. Persistencia
        repo_user.save(entidad);
    }
    public void editarUsuario(Long id, UsuarioDTO dto,String nuevaPass){
        Usuario usuario=repo_user.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());

        String tipoRecibido = dto.getTipoUsuario().toUpperCase();
        List<String> rangosValidos = List.of("SOLDADO", "SUBOFICIAL", "OFICIAL");

        if (!rangosValidos.contains(tipoRecibido)) {
            throw new RuntimeException("Rango no permitido. Debe ser: SOLDADO, SUBOFICIAL u OFICIAL.");
        }

        // 4. Actualizar contraseña solo si se envió una nueva
        if (nuevaPass != null && !nuevaPass.isBlank()) {
            usuario.setContrasenia(passwordEncoder.encode(nuevaPass));
        }
        repo_user.save(usuario);
    }

    public void asignarDestinos(AsignacionDTO dto){
        Usuario usuario=repo_user.findById(dto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuarioId()));;
        if (dto.getIdCuartel() != null) {
            Cuartel cuartel = repoCuarteles.findById(dto.getIdCuartel())
                    .orElseThrow(() -> new RuntimeException("Cuartel no encontrado"));
            usuario.setCuartel(cuartel);
        }

        // 3. Asignación de Cuerpo
        if (dto.getIdCuerpo() != null) {
            Cuerpo cuerpo = repoCuerpos.findById(dto.getIdCuerpo())
                    .orElseThrow(() -> new RuntimeException("Cuerpo no encontrado"));
            usuario.setCuerpo(cuerpo);
        }

        // 4. Asignación de Compañía
        if (dto.getIdCompania() != null) {
            Compania compania = repoCompanias.findById(dto.getIdCompania())
                    .orElseThrow(() -> new RuntimeException("Compañía no encontrada"));
            usuario.setCompania(compania);
        }

        // 5. Guardar cambios
        // Gracias a @Transactional, JPA detecta los cambios en los setters
        // y actualiza las claves foráneas automáticamente.
        repo_user.save(usuario);
    }

    public CuartelDTO obtenerCuartelUsuarioIdentificado() {
        // 1. Obtener el email del usuario desde la sesión de Spring Security
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        Usuario usuario = repo_user.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado en la sesión"));

        if (usuario.getCuartel() == null) {
            throw new RuntimeException("El usuario ingresante no tiene un cuartel asignado");
        }
        return cuartelMapper.toDTO(usuario.getCuartel());
    }

    public CompaniaDTO obtenerCompaniaUsuarioIdentificado() {
        // 1. Obtener el email del usuario desde la sesión de Spring Security
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        Usuario usuario = repo_user.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado en la sesión"));

        if (usuario.getCompania() == null) {
            throw new RuntimeException("El usuario ingresante no tiene un cuartel asignado");
        }
        return companiaMapper.toDTO(usuario.getCompania());
    }

    public CuerpoDTO obtenerCuerpoUsuarioIdentificado() {
        // 1. Obtener el email del usuario desde la sesión de Spring Security
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        Usuario usuario = repo_user.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado en la sesión"));

        if (usuario.getCompania() == null) {
            throw new RuntimeException("El usuario ingresante no tiene un cuartel asignado");
        }
        return cuerpoMapper.toDTO(usuario.getCuerpo());
    }
}
