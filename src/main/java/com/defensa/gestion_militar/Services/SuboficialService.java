package com.defensa.gestion_militar.Services;

import com.defensa.gestion_militar.Capacidad.CapacidadSuboficial;
import com.defensa.gestion_militar.DTOs.*;
import com.defensa.gestion_militar.Mappers.UsuarioMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuboficialService implements CapacidadSuboficial {

    @Autowired
    private Repo_Usuarios repoUsuarios;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private CompaniaService companiaService;

    @Autowired
    private CuerpoService cuerpoService;
    @Autowired
    private CuartelService cuartelService;

    @Autowired
    private ServicioService servicioService;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Override
    public void agregarCompania(CompaniaDTO dto) {

    }

    @Override
    public void agregarCuartel(CuartelDTO dto) {

    }

    @Override
    public void agregarCuerpo(CuerpoDTO dto) {

    }

    @Override
    public void agregarServicio(ServiciosDTO dto) {

    }

    @Override
    public void agregarUsuario(UsuarioDTO dto) {

    }

    @Override
    public void asignarCompania(Long idUser, Long idCompania) {

    }

    @Override
    public void asignarCuartel(Long idUser, Long idCuartel) {

    }

    @Override
    public void asignarCuerpo(Long idUser, Long idCuerpo) {

    }

    @Override
    public void asignarServicio(Long idUser, Long idServicio) {

    }

    @Override
    public CompaniaDTO consultarCompaniaPorId(Long id) {
        return companiaService.obtenerCompaniaPorId(id);
    }

    @Override
    public List<CompaniaDTO> consultarCompanias() {
        return  companiaService.obtenerTodasLasCompanias();
    }

    @Override
    public CuartelDTO consultarCuartelPorId(Long id) {
        return  cuartelService.obtenerCuartelPorId(id);
    }

    @Override
    public List<CuartelDTO> consultarCuarteles() {
        return cuartelService.obtenerTodosLosCuarteles();
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
        return repoUsuarios.findById(id)
                .map(usuarioMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<UsuarioDTO> consultarUsuarios() {
        return usuariosService.obtenerTodoElPersonal();
    }

    @Override
    public void editarCompania(Long id, CompaniaDTO dto) {

    }

    @Override
    public void editarCuartel(Long id, CuartelDTO dto) {

    }

    @Override
    public void editarCuerpo(Long id, CuerpoDTO dto) {

    }

    @Override
    public void editarServicio(Long id, ServiciosDTO dto) {

    }

    @Override
    public void editarUsuario(Long id, UsuarioDTO dto, String nuevaPass) {

    }

    @Override
    public void eliminarCompania(Long id) {

    }

    @Override
    public void eliminarCuartel(Long id) {

    }

    @Override
    public void eliminarCuerpo(long id) {

    }

    @Override
    public void eliminarServicio(Long id) {

    }

    @Override
    public void eliminarUsuario(Long id) {

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
