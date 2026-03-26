package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadOficial;
import com.defensa.gestion_militar.DTOs.*;
import com.defensa.gestion_militar.Entity.*;
import com.defensa.gestion_militar.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class OficialService implements CapacidadOficial {

    @Autowired
    ServicioService servicioService;

    @Autowired
    CuerpoService cuerpoService;

    @Autowired
    CuartelService cuartelService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    Repo_Usuarios repoUsuarios;

    @Autowired
    AsignacionService asignacionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void agregarCompania(CompaniaDTO dto) {
        if (dto!=null){
            companiaService.guardarCompania(dto);
        }
    }

    @Override
    public void agregarCuartel(CuartelDTO dto) {

        if (dto!=null){
            cuartelService.guardarCuartel(dto);
        }

    }

    @Override
    public void agregarCuerpo(CuerpoDTO dto) {
        if (dto!=null){
            cuerpoService.guardarCuerpo(dto);
        }
    }

    @Override
    public void agregarServicio(ServiciosDTO dto) {
        if (dto!=null){
            servicioService.guardarServicio(dto);
        }
    }

    @Override
    public void agregarUsuario(UsuarioDTO dto) {
        Usuario entidad;
        if (dto!=null){

            if ("OFICIAL".equalsIgnoreCase(dto.getTipoUsuario())) {
                entidad = new Oficial();
            } else if ("SUBOFICIAL".equalsIgnoreCase(dto.getTipoUsuario())) {
                entidad = new Suboficial();
            } else {
                entidad = new Soldado();
            }
            entidad.setNombre(dto.getNombre());
            entidad.setApellido(dto.getApellido());
            entidad.setEmail(dto.getEmail());
            // Se usa el passwordEncoder inyectado en el servicio
            entidad.setContrasenia(passwordEncoder.encode("12345"));
            repoUsuarios.save(entidad);
        }
    }

    @Override
    public void asignarCompania(Long idUser, Long idCompania) {
        asignacionService.AsignarCompania(idUser,idCompania);
    }

    @Override
    public void asignarCuartel(Long idUser, Long idCuartel) {
        asignacionService.AsignarCuartel(idUser,idCuartel);
    }

    @Override
    public void asignarCuerpo(Long idUser, Long idCuerpo) {
        asignacionService.AsignarCuerpo(idUser,idCuerpo);
    }

    @Override
    public void asignarServicio(Long idUser, Long idServicio) {
       // asignacionService.AsignarServicio(idUser,idServicio);
    }

    @Override
    public CompaniaDTO consultarCompaniaPorId(Long id) {
        return  companiaService.obtenerCompaniaPorId(id);
    }

    @Override
    public List<CompaniaDTO> consultarCompanias() {
        return  companiaService.obtenerTodasLasCompanias();
    }

    @Override
    public CuartelDTO consultarCuartelPorId(Long id) {
        return cuartelService.obtenerCuartelPorId(id);
    }

    @Override
    public List<CuartelDTO> consultarCuarteles() {
        return  cuartelService.obtenerTodosLosCuarteles();
    }

    @Override
    public CuerpoDTO consultarCuerpoPorId(Long id) {
        return cuerpoService.obtenerCuerpoPorId(id);
    }

    @Override
    public List<CuerpoDTO> consultarCuerpos() {
        return cuerpoService.obtenerTodosLosCuerpos();
    }

    @Override
    public ServiciosDTO consultarServicioPorId(Long id) {
        return servicioService.obtenerServicioPorId(id);
    }

    @Override
    public List<ServiciosDTO> consultarServicios() {
        return servicioService.obtenerTodosLosServicios();
    }

    @Override
    public UsuarioDTO consultarUsuarioPorId(Long id) {
       return  usuariosService.obtenerUserPorId(id);
//        return repoUsuarios.findById(id)
//                .map(usuarioMapper::toDTO)
//                .orElse(null);
    }

    @Override
    public List<UsuarioDTO> consultarUsuarios() {
        return usuariosService.obtenerTodoElPersonal();
    }

    @Override
    public void editarCompania(Long id, CompaniaDTO dto) {
        companiaService.editarCompania(id,dto);
    }

    @Override
    public void editarCuartel(Long id, CuartelDTO dto) {
        cuartelService.editarCuartel(id,dto);
    }

    @Override
    public void editarCuerpo(Long id, CuerpoDTO dto) {
        cuerpoService.editarCuerpo(id,dto);
    }

    @Override
    public void editarServicio(Long id, ServiciosDTO dto) {
        servicioService.editarServicio(id,dto);
    }

    @Override
    public void editarUsuario(Long id, UsuarioDTO dto, String nuevaPass) {

    }

    @Override
    public void eliminarCompania(Long id) {

    }

    @Override
    public void eliminarCuartel(Long id) {
        CuartelDTO cuartel=cuartelService.obtenerCuartelPorId(id);
        if (cuartel!=null){
            cuartelService.eliminarCuartel(cuartel.getId());
        }

    }

    @Override
    public void eliminarCuerpo(long id) {
        CuerpoDTO cuerp=cuerpoService.obtenerCuerpoPorId(id);
        if (cuerp!=null){
            cuerpoService.eliminarCuerpo(cuerp.getId());
            //servicioService.eliminarServicio(serv.getId());
        }
    }

    @Override
    public void eliminarServicio(Long id) {
        ServiciosDTO serv =servicioService.obtenerServicioPorId(id);
        if (serv!=null){
            servicioService.eliminarServicio(serv.getId());
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuariosService.eliminarUsuario(id);

    }

    @Override
    public void verServiciosAsignados() {

    }

    @Override
    public boolean puedeRealizarServicio(Integer rangoServicio) {
        return false;
    }

    @Override
    public void verMiCuartel() {

    }

    @Override
    public void verMiCompania() {

    }

    @Override
    public void verMiCuerpo() {

    }
}
