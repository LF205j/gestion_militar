package com.defensa.gestion_militar.Controllers;

import com.defensa.gestion_militar.DTOs.*;
import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.RealizaServicio;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Mappers.CuerpoMapper;
import com.defensa.gestion_militar.Mappers.UsuarioMapper;
import com.defensa.gestion_militar.Repositorios.Repo_Cuarteles;
import com.defensa.gestion_militar.Repositorios.Repo_Cuerpos;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import com.defensa.gestion_militar.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/oficial")
public class OficialController {

    @Autowired
    CompaniaService companiaService;
    @Autowired
    CuartelService cuartelService;
    @Autowired
    CuerpoService cuerpoService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private Repo_Cuerpos repoCuerpo;
    @Autowired
    private AsignacionService asignacionService;




    @GetMapping("/consultar/cuerpo")
    public String obtenerFormularioCuerpoPorId(Model model){
        CuerpoDTO cuerpoDTO=new CuerpoDTO();
        model.addAttribute("targetAction", "/api/oficial/consultar/cuerpo");
        model.addAttribute("cuerpoDto",cuerpoDTO);
        return "/oficial/consultar/consultar_cuerpo";
    }
    @PostMapping("/consultar/cuerpo")
    public String obtenerCuerpoPorId(@ModelAttribute("cuerpoDto") CuerpoDTO dto, Model model){
        CuerpoDTO cuerpoEncontrado = cuerpoService.obtenerCuerpoPorId(dto.getId());

        if (cuerpoEncontrado != null) {
            model.addAttribute("cuerpo", cuerpoEncontrado);
        } else {
            model.addAttribute("error", "No se encontró ningún cuerpo con el ID: " + dto.getId());
        }
        model.addAttribute("cuerpoDto", dto);
        return "/oficial/consultar/consultar_cuerpo";
    }

    @GetMapping("/consultar/compania")
    public String obtenerFormularioCompaniaPorId(Model model){
        CompaniaDTO companiaDTO=new CompaniaDTO();
        model.addAttribute("companiaDto",companiaDTO);
        model.addAttribute("targetAction", "/api/oficial/consultar/compania");
        return "/oficial/consultar/consultar_compania";
    }
    @PostMapping("/consultar/compania")
    public String obtenerCompaniaPorId(@ModelAttribute("companiaDto") CompaniaDTO dto, Model model){
        CompaniaDTO companiaEncontrado = companiaService.obtenerUserPorId(dto.getId());

        if (companiaEncontrado != null) {
            model.addAttribute("compania", companiaEncontrado);
        } else {
            model.addAttribute("error", "No se encontró ningúna compania con el ID: " + dto.getId());
        }
        model.addAttribute("companiaDto", dto);
        return "/oficial/consultar/consultar_compania";
    }

    @GetMapping("/consultar/cuartel")
    public String obtenerFormularioCuartelPorId(Model model){
        CuartelDTO cuartelDTO=new CuartelDTO();
        model.addAttribute("targetAction", "/api/oficial/consultar/cuartel");
        model.addAttribute("cuartelDto",cuartelDTO);
        return "/oficial/consultar/consultar_cuartel";
    }
    @PostMapping("/consultar/cuartel")
    public String obtenerCuartelPorId(@ModelAttribute("cuartelDto") CuartelDTO dto, Model model){
        CuartelDTO cuartelEncontrado = cuartelService.obtenerUserPorId(dto.getId());

        if (cuartelEncontrado != null) {
            model.addAttribute("cuartel", cuartelEncontrado);
        } else {
            model.addAttribute("error", "No se encontró ningún cuartel con el ID: " + dto.getId());
        }
        model.addAttribute("cuartelDto", dto);
        return "/oficial/consultar/consultar_cuartel";
    }

    @GetMapping("/consultar/servicio")
    public String obtenerFormularioServicioPorId(Model model){
        ServiciosDTO serviciosDTO=new ServiciosDTO();
        model.addAttribute("servicioDto",serviciosDTO);
        return "/oficial/consultar/consultar_servicios";
    }
    @PostMapping("/consultar/servicio")
    public String obtenerServicioPorId(@ModelAttribute("servicioDto") ServiciosDTO dto, Model model){
        ServiciosDTO servicioEncontrado = servicioService.obtenerServicioPorId(dto.getId());

        if (servicioEncontrado != null) {
            model.addAttribute("servicio", servicioEncontrado);
        } else {
            model.addAttribute("error", "No se encontró ningún servicio con el ID: " + dto.getId());
        }
        model.addAttribute("servicioDto", dto);
        return "/oficial/consultar/consultar_servicios";
    }

    @GetMapping("/consultar/usuario")
    public String obtenerFormularioUsuarioPorId(Model model){
        UsuarioDTO usuarioDTO=new UsuarioDTO();
        model.addAttribute("usuarioDto",usuarioDTO);
        return "/oficial/consultar/consultar_usuarios";
    }
    @PostMapping("/consultar/usuario")
    public String obtenerUsuarioPorId(@ModelAttribute("usuarioDto") UsuarioDTO dto, Model model){
        UsuarioDTO usuarioEncontrado = usuariosService.obtenerUserPorId(dto.getId());

        if (usuarioEncontrado != null) {
            model.addAttribute("usuario", usuarioEncontrado);
        } else {
            model.addAttribute("error", "No se encontró ningún usuario con el ID: " + dto.getId());
        }
        model.addAttribute("usuarioDto", dto);
        return "/oficial/consultar/consultar_usuarios";
    }

//    @GetMapping("/consultar/cuerpo")
//    public String obtenerCuerpo(Model model){
//        List<CuerpoDTO>listaCuerpos=cuerpoService.obtenerTodosLosCuerpos();
//        model.addAttribute("cuerpos",listaCuerpos);
//        return "/oficial/consultar/consultar_cuerpo";
//    }
//    @GetMapping("/consultar/compania")
//    public String obtenerCompaniao(Model model){
//        List<CompaniaDTO>listaCompania=companiaService.obtenerTodasLasCompanias();
//        model.addAttribute("companias",listaCompania);
//        return "/oficial/consultar/consultar_compania";
//    }
//
//    @GetMapping("/consultar/cuartel")
//    public String obtenerCuarteles(Model model){
//        List<CuartelDTO> listaCuarteles=cuartelService.obtenerTodosLosCuarteles();
//        model.addAttribute("cuarteles",listaCuarteles);
//
//        return "/oficial/consultar/consultar_cuartel";
//    }
//    @GetMapping("/consultar/servicio")
//    public String obtenerServicio(Model model){
//        List<ServiciosDTO>listaServicios=servicioService.obtenerTodosLosServicios();
//        model.addAttribute("servicios",listaServicios);
//        return "/oficial/consultar/consultar_servicios";
//    }
//
//    @GetMapping("/consultar/usuario")
//    public String obtenerUsuarios(Model model){
//        List<UsuarioDTO>listaUsuarios=usuariosService.obtenerTodoElPersonal();
//        model.addAttribute("usuarios",listaUsuarios);
//        return "/oficial/consultar/consultar_usuarios";
//    }


    @GetMapping("/eliminar/cuerpo")
    public String mostrarFormularioEliminarCuerpo(Model model) {
        model.addAttribute("targetAction", "/api/oficial/eliminar/cuerpo");
        return "oficial/eliminar/eliminar_cuerpo"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/cuerpo") // <--- Debe ser POST
    public String eliminarCuerpo(@RequestParam("id")Long id,Model model) {

        cuerpoService.eliminarCuerpo(id);
        model.addAttribute("mensaje", "Cuerpo con ID " + id + " eliminado con éxito.");
        return "/oficial/eliminar/eliminar_cuerpo";
    }
    @GetMapping("/eliminar/cuartel")
    public String mostrarFormularioEliminarCuartel(Model model) {
        model.addAttribute("targetAction", "/api/oficial/eliminar/cuartel");
        return "oficial/eliminar/eliminar_cuartel"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/cuartel") // <--- Debe ser POST
    public String eliminarCuartel(@RequestParam("id")Long id,Model model) {

        cuartelService.eliminarCuartel(id);
        model.addAttribute("mensaje", "Cuartel con ID " + id + " eliminado con éxito.");
        return "/oficial/eliminar/eliminar_cuartel";
    }

    @GetMapping("/eliminar/compania")
    public String mostrarFormularioEliminarCompania(Model model) {
        model.addAttribute("targetAction", "/api/oficial/eliminar/compania");
        return "oficial/eliminar/eliminar_compania"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/compania") // <--- Debe ser POST
    public String eliminarCompania(@RequestParam("id")Long id,Model model) {

        companiaService.eliminarCompania(id);
        model.addAttribute("mensaje", "Compania con ID " + id + " eliminado con éxito.");
        return "/oficial/eliminar/eliminar_compania";
    }

    @GetMapping("/agregar/cuerpo")
    public String mostrarFormularioAgregarCuerpo(Model model) {
        model.addAttribute("nuevoCuerpo", new CuerpoDTO()); // Enviamos un objeto vacío para el formulario
        model.addAttribute("targetAction", "/api/oficial/agregar/cuerpo");
        return "/oficial/agregar/agregar_cuerpo";
    }

    @PostMapping("/agregar/cuerpo")
    public String agregarCuerpo(@ModelAttribute("nuevoCuerpo") CuerpoDTO cuerpoDTO, RedirectAttributes redirectAttributes){
        try {
            cuerpoService.guardarCuerpo(cuerpoDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Cuerpo militar registrado con éxito.");
            return "/oficial/agregar/agregar_cuerpo"; // Redirige a la lista para ver el nuevo
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_cuerpo";
        }

    }

    @GetMapping("/agregar/compania")
    public String mostrarFormularioAgregarCompania(Model model) {
        model.addAttribute("targetAction", "/api/oficial/agregar/compania");
        model.addAttribute("nuevaCompania", new CompaniaDTO()); // Enviamos un objeto vacío para el formulario
        return "/oficial/agregar/agregar_compania";
    }

    @PostMapping("/agregar/compania")
    public String agregarCompania(@ModelAttribute("nuevaCompania") CompaniaDTO companiaDTO, RedirectAttributes redirectAttributes){
        try {

            companiaService.guardarCompania(companiaDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Compania militar registrado con éxito.");
            return "/oficial/agregar/agregar_compania"; // Redirige a la lista para ver el nuevo
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_compania";
        }

    }

    @GetMapping("/agregar/cuartel")
    public String mostrarFormularioAgregarCuartel(Model model) {
        model.addAttribute("nuevoCuartel", new CuartelDTO()); // Enviamos un objeto vacío para el formulario
        model.addAttribute("targetAction", "/api/oficial/agregar/cuartel");
        return "/oficial/agregar/agregar_cuartel";
    }

    @PostMapping("/agregar/cuartel")
    public String agregarCuartel(@ModelAttribute("nuevoCuartel") CuartelDTO cuartelDTO, RedirectAttributes redirectAttributes){
        try {

            cuartelService.guardarCuartel(cuartelDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Cuartel militar registrado con éxito.");
            return "/oficial/agregar/agregar_cuartel"; // Redirige a la lista para ver el nuevo
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_cuartel";
        }

    }
    // --- AGREGAR SERVICIO ---
    @GetMapping("/agregar/servicio")
    public String mostrarFormularioServicio(Model model) {
        model.addAttribute("nuevoServicio", new ServiciosDTO()); // Objeto para el th:object
        return "/oficial/agregar/agregar_servicio";
    }

    @GetMapping("/agregar/usuario")
    public String mostrarFormularioRegistro(Model model) {
        // Enviamos un DTO vacío para vincularlo al formulario
        model.addAttribute("targetAction", "/api/oficial/agregar/usuario");
        model.addAttribute("usuarioDto", new UsuarioDTO());

        // Opcional: Si necesitas cargar listas para desplegables (Cuarteles, Cuerpos, etc.)


        return "/oficial/agregar/agregar_usuario"; // Ajusta la ruta a tu carpeta
    }

    // POST: Procesar el registro
    @PostMapping("/agregar/usuario")
    public String guardarNuevoUsuario(@ModelAttribute("usuarioDto") UsuarioDTO dto, RedirectAttributes ra) {
        try {
            usuariosService.guardarUsuario(dto);
            ra.addFlashAttribute("mensaje", "Usuario registrado correctamente.");
            return "redirect:/api/oficial/consultar/usuario";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_usuario";
        }
    }
    @PostMapping("/agregar/servicio")
    public String guardarServicio(@ModelAttribute("nuevoServicio") ServiciosDTO dto,RedirectAttributes redirectAttributes) {
        servicioService.guardarServicio(dto);
        redirectAttributes.addFlashAttribute("mensaje", "Servicio  registrado con éxito.");
        return "/oficial/agregar/agregar_servicio";
    }

    @GetMapping("/eliminar/servicio")
    public String mostrarFormularioEliminarServicio(Model model) {
        model.addAttribute("targetAction", "/api/oficial/eliminar/servicio");
        return "/oficial/eliminar/eliminar_servicio"; // Ruta de tu archivo HTML
    }
    // --- ELIMINAR SERVICIO ---
    @PostMapping("/eliminar/servicio")
    public String eliminarServicio(@RequestParam("id") Long id,Model model) {
        servicioService.eliminarServicio(id);
        model.addAttribute("mensaje", "Servicio con ID " + id + " eliminado con éxito.");
        return "/oficial/eliminar/eliminar_servicio";
    }


    @GetMapping("/eliminar/usuario")
    public String mostrarFormularioEliminarUsuario(Model model) {
        model.addAttribute("targetAction", "/api/oficial/eliminar/usuario");
        return "/oficial/eliminar/eliminar_usuario"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/usuario")
    public String eliminarUsuario(@RequestParam("id") Long id, RedirectAttributes ra) {
        try {
            usuariosService.eliminarUsuario(id);
            // El mensaje se guarda temporalmente para la redirección
            ra.addFlashAttribute("mensaje", "Usuario con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }

        // REDIRIGIMOS a la lista para que la tabla se actualice sola
        return "/oficial/eliminar/eliminar_usuario";
    }
    @GetMapping("/editar/cuartel")
    public String mostrarFormularioEditarCuartel(Model model) {
        // Usamos el método que ya tienes para obtener el DTO
         // Nota: en tu Service lo llamaste obtenerCuerpo pero devuelve CuartelDTO

        model.addAttribute("cuartelDto", new CuartelDTO());
        return "/oficial/editar/editar_cuartel";
    }
    @PostMapping("/editar/cuartel")
    public String editarCuartel(@ModelAttribute("cuartelDto") CuartelDTO dto, RedirectAttributes ra){
        try {
            cuartelService.editarCuartel(dto.getId(), dto);
            ra.addFlashAttribute("mensaje", "Cuartel actualizado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
        }
        return "/oficial/editar/editar_cuartel";
    }

    @GetMapping("/editar/cuerpo")
    public String mostrarFormularioEditarCuerpo(Model model){
        model.addAttribute("cuerpoDto",new CuerpoDTO());
        return "/oficial/editar/editar_cuerpo";
    }
    @PostMapping("/editar/cuerpo")
    public String editarCuerpo(@ModelAttribute("cuerpoDto")CuerpoDTO dto,RedirectAttributes ra){
        try {
            cuerpoService.editarCuerpo(dto.getId(), dto);
            ra.addFlashAttribute("mensaje", "Cuerpo actualizado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
        }
        return "/oficial/editar/editar_cuerpo";
    }

    @GetMapping("/editar/compania")
    public String mostrarFormularioEditarCompania(Model model){
        model.addAttribute("companiaDto",new CompaniaDTO());
        return "/oficial/editar/editar_compania";
    }
    @PostMapping("/editar/compania")
    public String editarCompania(@ModelAttribute("companiaDto")CompaniaDTO dto,RedirectAttributes ra){
        try {
            companiaService.editarCompania(dto.getId(), dto);
            ra.addFlashAttribute("mensaje", "Compania actualizado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
        }
        return "/oficial/editar/editar_compania";
    }
    @GetMapping("/editar/servicio")
    public String mostrarFormularioEditarServicio(Model model){
        model.addAttribute("servicioDto",new ServiciosDTO());
        return "/oficial/editar/editar_servicio";
    }
    @PostMapping("/editar/servicio")
    public String editarServicio(@ModelAttribute("servicioDto")ServiciosDTO dto,RedirectAttributes ra){
        try {
            servicioService.editarServicio(dto.getId(), dto);
            ra.addFlashAttribute("mensaje", "Servicio actualizado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
        }
        return "/oficial/editar/editar_servicio";
    }
    @GetMapping("/asignar/cuartel")
    public String paginaAsignarCuartel(Model model) {
        // Es necesario pasar un DTO vacío para que el formulario sepa qué campos llenar
        model.addAttribute("asignacionDto", new AsignacionDTO());
        model.addAttribute("targetAction", "/api/oficial/asignar/cuartel");
        return "/oficial/asignacion/asignar/asignar_cuartel";
    }

    @PostMapping("/asignar/cuartel")
    public String guardarCuartel(@ModelAttribute("asignacionDto") AsignacionDTO dto, RedirectAttributes ra) {
        try {
            asignacionService.AsignarCuartel(dto.getUsuarioId(), dto.getIdCuartel());
            // El FlashAttribute sobrevive al redireccionamiento
            ra.addFlashAttribute("mensajeExito", "El usuario ha sido movido al cuartel indicado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        // Redireccionamos a la misma página para limpiar el formulario y mostrar el mensaje
        return "/oficial/asignacion/asignar/asignar_cuartel";
    }
    @GetMapping("/asignar/compania")
    public String paginaAsignarCompania(Model model) {
        // Es necesario pasar un DTO vacío para que el formulario sepa qué campos llenar
        model.addAttribute("asignacionDto", new AsignacionDTO());
        model.addAttribute("targetAction", "/api/oficial/asignar/compania");
        return "/oficial/asignacion/asignar/asignar_compania";
    }

    @PostMapping("/asignar/compania")
    public String guardarCompania(@ModelAttribute("asignacionDto") AsignacionDTO dto, RedirectAttributes ra) {
        try {
            asignacionService.AsignarCompania(dto.getUsuarioId(), dto.getIdCompania());
            // El FlashAttribute sobrevive al redireccionamiento
            ra.addFlashAttribute("mensajeExito", "El usuario ha sido movido al Compania indicado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        // Redireccionamos a la misma página para limpiar el formulario y mostrar el mensaje
        return "/oficial/asignacion/asignar/asignar_compania";
    }
    @GetMapping("/asignar/cuerpo")
    public String paginaAsignarCuerpo(Model model) {
        // Es necesario pasar un DTO vacío para que el formulario sepa qué campos llenar
        model.addAttribute("targetAction", "/api/oficial/asignar/cuerpo");
        model.addAttribute("asignacionDto", new AsignacionDTO());
        return "/oficial/asignacion/asignar/asignar_cuerpo";
    }

    @PostMapping("/asignar/cuerpo")
    public String guardarCuerpo(@ModelAttribute("asignacionDto") AsignacionDTO dto, RedirectAttributes ra) {
        try {
            asignacionService.AsignarCuerpo(dto.getUsuarioId(), dto.getIdCuerpo());
            // El FlashAttribute sobrevive al redireccionamiento
            ra.addFlashAttribute("mensajeExito", "El usuario ha sido movido al Cuerpo indicado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        // Redireccionamos a la misma página para limpiar el formulario y mostrar el mensaje
        return "/oficial/asignacion/asignar/asignar_cuerpo";
    }
    @GetMapping("/asignar/servicio")
    public String paginaAsignarServicio(Model model) {
        // Es necesario pasar un DTO vacío para que el formulario sepa qué campos llenar
        model.addAttribute("targetAction", "/api/oficial/asignar/servicio");
        model.addAttribute("asignacionDto", new AsignacionDTO());
        return "/oficial/asignacion/asignar/asignar_servicio";
    }

    @PostMapping("/asignar/servicio")
    public String guardarServicio(@ModelAttribute("asignacionDto") AsignacionDTO dto, RedirectAttributes ra) {
        try {
            asignacionService.AsignarServicio(dto.getUsuarioId(), dto.getIdServicio());
            // El FlashAttribute sobrevive al redireccionamiento
            ra.addFlashAttribute("mensajeExito", "El usuario ha sido movido al Cuerpo indicado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        // Redireccionamos a la misma página para limpiar el formulario y mostrar el mensaje
        return "/oficial/asignacion/asignar/asignar_servicio";
    }

}
