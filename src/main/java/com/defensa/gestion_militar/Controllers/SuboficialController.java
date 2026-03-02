package com.defensa.gestion_militar.Controllers;

import com.defensa.gestion_militar.DTOs.*;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/suboficial")
public class SuboficialController {

    @Autowired
    private CuerpoService cuerpoService;

    @Autowired
    private CompaniaService companiaService;

    @Autowired
    private CuartelService cuartelService;
    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private ServicioService servicioService;
    @Autowired
    private AsignacionService asignacionService;

    @GetMapping("/consultar/cuerpo")
    public String obtenerFormularioCuerpoPorId(Model model){
        CuerpoDTO cuerpoDTO=new CuerpoDTO();
        model.addAttribute("targetAction", "/api/suboficial/consultar/cuerpo");
        model.addAttribute("cuerpoDto",cuerpoDTO);
        return "/oficial/consultar/consultar_cuerpo";
    }
    @PostMapping("/consultar/cuerpo")
    public String obtenerCuerpoPorId(@ModelAttribute("cuerpoDto") CuerpoDTO dto, Model model){
        Usuario usuarioRegistrado=usuariosService.obtenerUsuarioLogueado();
        CuerpoDTO cuerpoEncontrado = cuerpoService.obtenerCuerpoPorId(usuarioRegistrado.getId(),dto.getId());

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
        model.addAttribute("targetAction", "/api/suboficial/consultar/compania");
        model.addAttribute("companiaDto",companiaDTO);
        return "/oficial/consultar/consultar_compania";
    }
    @PostMapping("/consultar/compania")
    public String obtenerCompaniaPorId(@ModelAttribute("companiaDto") CompaniaDTO dto, Model model){
        Usuario usuarioAdmin=usuariosService.obtenerUsuarioLogueado();
        CompaniaDTO companiaEncontrado = companiaService.obtenerCompaniaPorId(usuarioAdmin.getId(),dto.getId());

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
        model.addAttribute("targetAction", "/api/suboficial/consultar/cuartel");
        model.addAttribute("cuartelDto",cuartelDTO);
        return "/oficial/consultar/consultar_cuartel";
    }
    @PostMapping("/consultar/cuartel")
    public String obtenerCuartelPorId(@ModelAttribute("cuartelDto") CuartelDTO dto, Model model){
        Usuario usuarioAdmin=usuariosService.obtenerUsuarioLogueado();
        CuartelDTO cuartelEncontrado = cuartelService.obtenerCuartelPorId(usuarioAdmin.getId(),dto.getId());

        if (cuartelEncontrado != null) {
            model.addAttribute("cuartel", cuartelEncontrado);
        } else {
            model.addAttribute("error", "No se encontró ningún cuartel con el ID: " + dto.getId());
        }
        model.addAttribute("cuartelDto", dto);
        return "/oficial/consultar/consultar_cuartel";
    }

    @GetMapping("/asignar/servicio")
    public String eliminarCuerpo(){
        return "/suboficial/asignar/asignar_servicio";
    }

    @GetMapping("/asignar/compania")
    public String asignarCompania(@RequestParam("idSoldado") Long idSoldado,
                                  @RequestParam("idCompania") Long idCompania,
                                  RedirectAttributes ra){

        return "/suboficial/asginar/asignar_compania";
    }
    @GetMapping("/asignar/cuerpo")
    public String asignarCuerpo(){
        return "/suboficial/asginar/asignar_cuerpo";
    }
    @GetMapping("/asignar/cuartel")
    public String asignarCuartel(){
        return "/suboficial/asginar/asignar_cuartel";
    }

    @GetMapping("/eliminar/cuerpo")
    public String mostrarFormularioEliminarCuerpo(Model model) {
        model.addAttribute("targetAction", "/api/oficial/eliminar/cuerpo");
        return "oficial/eliminar/eliminar_cuerpo"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/cuerpo") // <--- Debe ser POST
    public String eliminarCuerpo(@RequestParam("id")Long id, Model model) {
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        cuerpoService.eliminarCuerpo(usuarioLogueado.getId(),id);
        model.addAttribute("mensaje", "Cuerpo con ID " + id + " eliminado con éxito.");
        return "/oficial/eliminar/eliminar_cuerpo";
    }
    @GetMapping("/eliminar/cuartel")
    public String mostrarFormularioEliminarCuartel(Model model) {
        model.addAttribute("targetAction", "/api/suboficial/eliminar/cuartel");
        return "oficial/eliminar/eliminar_cuartel"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/cuartel") // <--- Debe ser POST
    public String eliminarCuartel(@RequestParam("id")Long id,Model model) {
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        cuartelService.eliminarCuartel(usuarioLogueado.getId(), id);
        model.addAttribute("mensaje", "Cuartel con ID " + id + " eliminado con éxito.");
        return "/oficial/eliminar/eliminar_cuartel";
    }

    @GetMapping("/eliminar/compania")
    public String mostrarFormularioEliminarCompania(Model model) {
        model.addAttribute("targetAction", "/api/suboficial/eliminar/compania");
        return "oficial/eliminar/eliminar_compania"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/compania") // <--- Debe ser POST
    public String eliminarCompania(@RequestParam("id")Long id,Model model) {
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        companiaService.eliminarCompania(usuarioLogueado.getId(),id);
        model.addAttribute("mensaje", "Compania con ID " + id + " eliminado con éxito.");
        return "/oficial/eliminar/eliminar_compania";
    }

    @GetMapping("/agregar/cuerpo")
    public String mostrarFormularioAgregarCuerpo(Model model) {
        model.addAttribute("nuevoCuerpo", new CuerpoDTO()); // Enviamos un objeto vacío para el formulario
        model.addAttribute("targetAction", "/api/suboficial/agregar/cuerpo");
        return "/oficial/agregar/agregar_cuerpo";
    }

    @PostMapping("/agregar/cuerpo")
    public String agregarCuerpo(@ModelAttribute("nuevoCuerpo") CuerpoDTO cuerpoDTO, RedirectAttributes redirectAttributes){
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {
            cuerpoService.guardarCuerpo(usuarioLogueado.getId(),cuerpoDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Cuerpo militar registrado con éxito.");
            return "/oficial/agregar/agregar_cuerpo"; // Redirige a la lista para ver el nuevo
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_cuerpo";
        }

    }

    @GetMapping("/agregar/compania")
    public String mostrarFormularioAgregarCompania(Model model) {
        model.addAttribute("targetAction", "/api/suboficial/agregar/compania");
        model.addAttribute("nuevaCompania", new CompaniaDTO()); // Enviamos un objeto vacío para el formulario
        return "/oficial/agregar/agregar_compania";
    }

    @PostMapping("/agregar/compania")
    public String agregarCompania(@ModelAttribute("nuevaCompania") CompaniaDTO companiaDTO, RedirectAttributes redirectAttributes){
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {

            companiaService.guardarCompania(usuarioLogueado.getId(),companiaDTO);
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
        model.addAttribute("targetAction", "/api/suboficial/agregar/cuartel");
        return "/oficial/agregar/agregar_cuartel";
    }

    @PostMapping("/agregar/cuartel")
    public String agregarCuartel(@ModelAttribute("nuevoCuartel") CuartelDTO cuartelDTO, RedirectAttributes redirectAttributes){
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {

            cuartelService.guardarCuartel(usuarioLogueado.getId(),cuartelDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Cuartel militar registrado con éxito.");
            return "/oficial/agregar/agregar_cuartel"; // Redirige a la lista para ver el nuevo
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_cuartel";
        }

    }

    @GetMapping("/agregar/usuario")
    public String mostrarFormularioRegistro(Model model) {
        // Enviamos un DTO vacío para vincularlo al formulario
        model.addAttribute("targetAction", "/api/suboficial/agregar/usuario");
        model.addAttribute("usuarioDto", new UsuarioDTO());

        // Opcional: Si necesitas cargar listas para desplegables (Cuarteles, Cuerpos, etc.)


        return "/oficial/agregar/agregar_usuario"; // Ajusta la ruta a tu carpeta
    }

    // POST: Procesar el registro
    @PostMapping("/agregar/usuario")
    public String guardarNuevoUsuario(@ModelAttribute("usuarioDto") UsuarioDTO dto, RedirectAttributes ra) {
        try {
            Usuario usuarioAdmin=usuariosService.obtenerUsuarioLogueado();
            usuariosService.guardarUsuario(usuarioAdmin.getId(),dto);
            ra.addFlashAttribute("mensaje", "Usuario registrado correctamente.");
            return "redirect:/api/oficial/consultar/usuario";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_usuario";
        }
    }
    @GetMapping("/agregar/servicio")
    public String mostrarFormularioServicio(Model model) {
        model.addAttribute("nuevoServicio", new ServiciosDTO()); // Objeto para el th:object
        model.addAttribute("targetAction", "/api/suboficial/agregar/servicio");
        return "/oficial/agregar/agregar_servicio";
    }
    @PostMapping("/agregar/servicio")
    public String guardarServicio(@ModelAttribute("nuevoServicio") ServiciosDTO dto,RedirectAttributes redirectAttributes) {
        Usuario usuarioAdmin=usuariosService.obtenerUsuarioLogueado();
        servicioService.guardarServicio(usuarioAdmin.getId(),dto);
        redirectAttributes.addFlashAttribute("mensaje", "Servicio  registrado con éxito.");
        return "/oficial/agregar/agregar_servicio";
    }
    @GetMapping("/eliminar/usuario")
    public String mostrarFormularioEliminarUsuario(Model model) {
        model.addAttribute("targetAction", "/api/suboficial/eliminar/usuario");
        return "/oficial/eliminar/eliminar_usuario"; // Ruta de tu archivo HTML
    }
    @PostMapping("/eliminar/usuario")
    public String eliminarUsuario(@RequestParam("id") Long id, RedirectAttributes ra) {
        try {
            usuariosService.eliminarUsuarioSuboficial(id);
            // El mensaje se guarda temporalmente para la redirección
            ra.addFlashAttribute("mensaje", "Usuario con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }

        // REDIRIGIMOS a la lista para que la tabla se actualice sola
        return "/oficial/eliminar/eliminar_usuario";
    }

    @GetMapping("/asignacion/reasignar")
    public String paginaReasignar(Model model) {
        model.addAttribute("asignacionDto", new AsignacionDTO());
        model.addAttribute("targetAction", "/api/suboficial/asignacion/reasignar");
        return "/oficial/asignacion/reasignar/reasignar_asignaciones";
    }

    // --- POST: Procesa la ejecución de reasignación ---
    @PostMapping("/asignacion/reasignar")
    public String ejecutarReasignacion(
            @ModelAttribute("asignacionDto") AsignacionDTO dto,
            @RequestParam("idDestino") Long idDestino,
            RedirectAttributes ra) {

        try {
            // Validamos cuál campo del formulario fue llenado (ID Viejo)
            if (dto.getIdCuartel() != null) {
                asignacionService.ReasignarCuartel(idDestino, dto.getIdCuartel());
                ra.addFlashAttribute("mensajeExito", "¡Éxito! El personal del Cuartel ha sido trasladado.");
            }
            else if (dto.getIdCompania() != null) {
                asignacionService.ReasignarCompania(idDestino, dto.getIdCompania());
                ra.addFlashAttribute("mensajeExito", "¡Éxito! El personal de la Compañía ha sido trasladado.");
            }
            else if (dto.getIdCuerpo() != null) {
                asignacionService.ReasignarCuerpo(idDestino, dto.getIdCuerpo());
                ra.addFlashAttribute("mensajeExito", "¡Éxito! El personal del Cuerpo ha sido trasladado.");
            }
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error en el proceso: " + e.getMessage());
        }

        // Redirección para limpiar el formulario y evitar re-envíos accidentales
        return "/oficial/asignacion/reasignar/reasignar_asignaciones";
    }
}
