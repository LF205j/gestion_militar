package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadGestionCuerpo;
import com.defensa.gestion_militar.Capacidad.CapacidadGestionUsuario;
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

    public UsuarioDTO obtenerUserPorId(Long idAdmin,Long idUser){
//        Usuario ejecutor = repo_user.findById(idAdmin)
//                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
//
//        // 2. Verificamos si tiene la capacidad
//        if (ejecutor instanceof CapacidadGestionUsuario oficialConMando) {
//
//            // 3. El Oficial valida la intención de consulta
//            oficialConMando.consultarUsuario(idUser);
//
//            // 4. Ejecutamos la búsqueda técnica (tu método original)
//            return repo_user.findById(idUser)
//                    .map(usuarioMapper::toDTO)
//                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUser));
//
//        } else {
//            throw new RuntimeException("Acceso Denegado: No tiene permisos para consultar usuario militares.");
//        }
        return repo_user.findById(idUser)
                .map(usuarioMapper::toDTO)
                .orElse(null);
    }
    public Usuario obtenerUsuarioLogueado() {
        // 1. Extraemos el nombre de usuario (email) desde el contexto de Spring Security
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        // 2. Buscamos el objeto Usuario completo en la base de datos
        // Nota: Asegúrate de que tu repo_user tenga el método findByEmail
        return repo_user.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario logueado en la base de datos."));
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

    //Long id
    public void eliminarUsuario(Long idOficial, Long idABorrar ){
//        if (repo_user.existsById(id)) {
//            repo_user.deleteById(id);
//        } else {
//            throw new RuntimeException("No se pudo eliminar: Usuario no encontrado con ID " + id);
//        }
        // 1. Obtener al oficial desde la DB
        Usuario ejecutor = repo_user.findById(idOficial)
                .orElseThrow(() -> new RuntimeException("Oficial no encontrado"));

        // 2. ¿Tiene la capacidad de gestionar usuarios?
        if (ejecutor instanceof CapacidadGestionUsuario oficialConMando) {

            // 3. Le pedimos al oficial que valide la operación (Lógica de Negocio)
            oficialConMando.eliminarUsuario(idABorrar);

            // 4. Si llegamos aquí sin excepciones, operamos en la DB (Lógica de Persistencia)
            if (repo_user.existsById(idABorrar)) {
                repo_user.deleteById(idABorrar);
            } else {
                throw new RuntimeException("El usuario que desea borrar ya no existe.");
            }

        } else {
            throw new RuntimeException("Acceso Denegado: Usted no tiene rango para gestionar usuarios.");
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

    public void guardarUsuario(Long idAdmin,UsuarioDTO dto) {
        Usuario ejecutor = repo_user.findById(idAdmin)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        // 2. Verificamos si tiene la capacidad de gestionar cuarteles
        if (ejecutor instanceof CapacidadGestionUsuario oficialConMando) {


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
//            oficialConMando.guardarUsuario(entidad);
            // 3. Persistencia
            repo_user.save(entidad);

        } else {
            throw new RuntimeException("Acceso Denegado: Su rango no permite crear cuarteles.");
        }

    }
    public void editarUsuario(Long idAdmin,Long idUsuarioAEditar, UsuarioDTO dto,String nuevaPass){
//        Usuario usuario=repo_user.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//        usuario.setNombre(dto.getNombre());
//        usuario.setApellido(dto.getApellido());
//        usuario.setEmail(dto.getEmail());
//
//        String tipoRecibido = dto.getTipoUsuario().toUpperCase();
//        List<String> rangosValidos = List.of("SOLDADO", "SUBOFICIAL", "OFICIAL");
//
//        if (!rangosValidos.contains(tipoRecibido)) {
//            throw new RuntimeException("Rango no permitido. Debe ser: SOLDADO, SUBOFICIAL u OFICIAL.");
//        }
//
//        // 4. Actualizar contraseña solo si se envió una nueva
//        if (nuevaPass != null && !nuevaPass.isBlank()) {
//            usuario.setContrasenia(passwordEncoder.encode(nuevaPass));
//        }
//        repo_user.save(usuario);
        Usuario ejecutor = repo_user.findById(idAdmin)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        // 2. Verificamos si tiene la capacidad de gestionar usuarios
        if (ejecutor instanceof CapacidadGestionUsuario oficialConMando) {

            // 3. Buscamos el usuario real en la base de datos
            Usuario usuarioExistente = repo_user.findById(idUsuarioAEditar)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // 4. La entidad Oficial valida la operación
            oficialConMando.editarUsuario(idUsuarioAEditar, dto, nuevaPass);

            // 5. Aplicamos los cambios técnicos (Persistencia)
            usuarioExistente.setNombre(dto.getNombre());
            usuarioExistente.setApellido(dto.getApellido());
            usuarioExistente.setEmail(dto.getEmail());
            // Aquí podrías cambiar el Discriminador si fuera necesario,
            // pero usualmente el tipo de usuario no cambia así de simple en JPA.

            if (nuevaPass != null && !nuevaPass.isBlank()) {
                usuarioExistente.setContrasenia(passwordEncoder.encode(nuevaPass));
            }

            repo_user.save(usuarioExistente);

        } else {
            throw new RuntimeException("Acceso Denegado: No tiene permisos para editar usuarios.");
        }
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
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email;
//
//        if (principal instanceof UserDetails) {
//            email = ((UserDetails) principal).getUsername();
//        } else {
//            email = principal.toString();
//        }
//
//        Usuario usuario = repo_user.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado en la sesión"));
//
//        if (usuario.getCuartel() == null) {
//            throw new RuntimeException("El usuario ingresante no tiene un cuartel asignado");
//        }
//        return cuartelMapper.toDTO(usuario.getCuartel());
        // 1. Obtener el usuario autenticado (usando tu lógica de email)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        Usuario usuario = repo_user.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en sesión"));

        // 2. EJECUTAR CAPACIDAD: La entidad valida si puede verla
        usuario.verMiCuartel();

        // 3. RETORNAR DTO: Usamos el mapper sobre la compañía del usuario
        return cuartelMapper.toDTO(usuario.getCuartel());
    }

    public CompaniaDTO obtenerCompaniaUsuarioIdentificado() {
        // 1. Obtener el email del usuario desde la sesión de Spring Security
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email;
//        if (principal instanceof UserDetails) {
//            email = ((UserDetails) principal).getUsername();
//        } else {
//            email = principal.toString();
//        }
//        Usuario usuario = repo_user.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado en la sesión"));
//
//        if (usuario.getCompania() == null) {
//            throw new RuntimeException("El usuario ingresante no tiene un cuartel asignado");
//        }
//        return companiaMapper.toDTO(usuario.getCompania());
        // 1. Obtener el usuario autenticado (usando tu lógica de email)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        Usuario usuario = repo_user.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en sesión"));

        // 2. EJECUTAR CAPACIDAD: La entidad valida si puede verla
        usuario.verMiCompania();

        // 3. RETORNAR DTO: Usamos el mapper sobre la compañía del usuario
        return companiaMapper.toDTO(usuario.getCompania());
    }

    public CuerpoDTO obtenerCuerpoUsuarioIdentificado() {
        // 1. Obtener el email del usuario desde la sesión de Spring Security
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String email;
//        if (principal instanceof UserDetails) {
//            email = ((UserDetails) principal).getUsername();
//        } else {
//            email = principal.toString();
//        }
//        Usuario usuario = repo_user.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado en la sesión"));
//
//        if (usuario.getCompania() == null) {
//            throw new RuntimeException("El usuario ingresante no tiene un cuartel asignado");
//        }
//        return cuerpoMapper.toDTO(usuario.getCuerpo());
        // 1. Obtener el usuario autenticado (usando tu lógica de email)
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        Usuario usuario = repo_user.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en sesión"));

        // 2. EJECUTAR CAPACIDAD: La entidad valida si puede verla
        usuario.verMiCuerpo();

        // 3. RETORNAR DTO: Usamos el mapper sobre la compañía del usuario
        return cuerpoMapper.toDTO(usuario.getCuerpo());
    }
    public List<UsuarioDTO> obtenerUsuariosPorCuartel(Long idCuartel) {
        // 1. Buscamos la lista de entidades en el repositorio
        List<Usuario> usuarios = repo_user.findByCuartelId(idCuartel);

        // 2. Convertimos la lista de entidades a una lista de DTOs
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioDTO> obtenerUsuariosPorCompania(Long idCompania) {
        // 1. Buscamos la lista de entidades en el repositorio
        List<Usuario> usuarios = repo_user.findByCompaniaId(idCompania);

        // 2. Convertimos la lista de entidades a una lista de DTOs
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<UsuarioDTO> obtenerUsuariosPorCuerpo(Long idCuerpo) {
        // 1. Buscamos la lista de entidades en el repositorio
        List<Usuario> usuarios = repo_user.findByCuerpoId(idCuerpo);

        // 2. Convertimos la lista de entidades a una lista de DTOs
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<UsuarioDTO> obtenerUsuariosPorServicio(Long idServicio) {
        // 1. Buscamos la lista de entidades en el repositorio
        List<Usuario> usuarios = repo_user.findByServiciosRealizados_Servicio_Id(idServicio);

        // 2. Convertimos la lista de entidades a una lista de DTOs
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

}
