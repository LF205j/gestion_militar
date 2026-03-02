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

import java.util.ArrayList;

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
    private Repo_Cuarteles repoCuarteles;
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
        model.addAttribute("companiaDto",companiaDTO);
        model.addAttribute("targetAction", "/api/oficial/consultar/compania");
        return "/oficial/consultar/consultar_compania";
    }
    @PostMapping("/consultar/compania")
    public String obtenerCompaniaPorId(@ModelAttribute("companiaDto") CompaniaDTO dto, Model model){
        Usuario usuarioRegistrado=usuariosService.obtenerUsuarioLogueado();
        CompaniaDTO companiaEncontrado = companiaService.obtenerCompaniaPorId(usuarioRegistrado.getId(),dto.getId());

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

    @GetMapping("/consultar/servicio")
    public String obtenerFormularioServicioPorId(Model model){
        ServiciosDTO serviciosDTO=new ServiciosDTO();
        model.addAttribute("servicioDto",serviciosDTO);
        return "/oficial/consultar/consultar_servicios";
    }
    @PostMapping("/consultar/servicio")
    public String obtenerServicioPorId(@ModelAttribute("servicioDto") ServiciosDTO dto, Model model){
        Usuario usuarioAdmin=usuariosService.obtenerUsuarioLogueado();
        ServiciosDTO servicioEncontrado = servicioService.obtenerServicioPorId(usuarioAdmin.getId(),dto.getId());

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
        Usuario usuarioAdmin=usuariosService.obtenerUsuarioLogueado();
        UsuarioDTO usuarioEncontrado = usuariosService.obtenerUserPorId(usuarioAdmin.getId(),dto.getId());

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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        cuerpoService.eliminarCuerpo(usuarioLogueado.getId(),id);
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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        cuartelService.eliminarCuartel(usuarioLogueado.getId(),id);
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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        companiaService.eliminarCompania(usuarioLogueado.getId(),id);
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
        model.addAttribute("targetAction", "/api/oficial/agregar/compania");
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
        model.addAttribute("targetAction", "/api/oficial/agregar/cuartel");
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
            Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
            usuariosService.guardarUsuario(usuarioLogueado.getId(),dto);
            ra.addFlashAttribute("mensaje", "Usuario registrado correctamente.");
            return "redirect:/api/oficial/consultar/usuario";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "/oficial/agregar/agregar_usuario";
        }
    }
    @PostMapping("/agregar/servicio")
    public String guardarServicio(@ModelAttribute("nuevoServicio") ServiciosDTO dto,RedirectAttributes redirectAttributes) {
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        servicioService.guardarServicio(usuarioLogueado.getId(),dto);
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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        servicioService.eliminarServicio(usuarioLogueado.getId(),id);
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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {
            usuariosService.eliminarUsuario(usuarioLogueado.getId(),id);
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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {
            cuartelService.editarCuartel(usuarioLogueado.getId(),dto.getId(), dto);
            ra.addFlashAttribute("mensaje", "Cuartel actualizado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
        }
        return "/oficial/editar/editar_cuartel";
    }

    @GetMapping("/editar/usuario")
    public String mostrarFormularioEditarUsuario(Model model) {
        // Entregamos un DTO vacío para que el usuario ingrese el ID y los datos
        model.addAttribute("usuarioDto", new UsuarioDTO());
        return "/oficial/editar/editar_usuarios";
    }

    @PostMapping("/editar/usuario")
    public String editarUsuario(
            @ModelAttribute("usuarioDto") UsuarioDTO dto,
            @RequestParam(value = "nuevaPass", required = false) String nuevaPass,
            RedirectAttributes ra) {


        try {
            Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
            // Usamos el ID que el usuario escribió en el formulario (dto.getId())
            // y llamamos a tu función del service
            usuariosService.editarUsuario(usuarioLogueado.getId(),dto.getId(), dto, nuevaPass);

            ra.addFlashAttribute("mensaje", "Usuario con ID " + dto.getId() + " actualizado con éxito.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
        }
        // Redirigimos para limpiar el formulario y mostrar el mensaje
        return "/oficial/editar/editar_usuarios";
    }


    @GetMapping("/editar/cuerpo")
    public String mostrarFormularioEditarCuerpo(Model model){
        model.addAttribute("cuerpoDto",new CuerpoDTO());
        return "/oficial/editar/editar_cuerpo";
    }
    @PostMapping("/editar/cuerpo")
    public String editarCuerpo(@ModelAttribute("cuerpoDto")CuerpoDTO dto,RedirectAttributes ra){
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {
            cuerpoService.editarCuerpo(usuarioLogueado.getId(),dto.getId(), dto);
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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {
            companiaService.editarCompania(usuarioLogueado.getId(),dto.getId(), dto);
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
        Usuario usuarioLogueado = usuariosService.obtenerUsuarioLogueado();
        try {
            servicioService.editarServicio(usuarioLogueado.getId(),dto.getId(), dto);
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
            Usuario oficial = usuariosService.obtenerUsuarioLogueado();
            asignacionService.AsignarCuartel(oficial.getId(),dto.getUsuarioId(), dto.getIdCuartel());
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
            Usuario oficial = usuariosService.obtenerUsuarioLogueado();
            asignacionService.AsignarCompania(oficial.getId(),dto.getUsuarioId(), dto.getIdCompania());
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
            Usuario oficial = usuariosService.obtenerUsuarioLogueado();
            asignacionService.AsignarCuerpo(oficial.getId(),dto.getUsuarioId(), dto.getIdCuerpo());
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
            Usuario oficial = usuariosService.obtenerUsuarioLogueado();
            asignacionService.AsignarServicio(oficial.getId(),dto.getUsuarioId(), dto.getIdServicio());
            // El FlashAttribute sobrevive al redireccionamiento
            ra.addFlashAttribute("mensajeExito", "El usuario ha sido movido al Cuerpo indicado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error: " + e.getMessage());
        }
        // Redireccionamos a la misma página para limpiar el formulario y mostrar el mensaje
        return "/oficial/asignacion/asignar/asignar_servicio";
    }

@GetMapping("/asignacion/consultar")
public String paginaConsultaMultiple(Model model) {
    // Enviamos un DTO vacío.
    // Thymeleaf lo usará para vincular (bind) los inputs de texto/número.
    model.addAttribute("asignacionDto", new AsignacionDTO());

    // Opcional: Puedes inicializar la lista de usuarios como nula
    // para que la tabla no se vea hasta que se haga una búsqueda.
    model.addAttribute("usuariosAsignados", null);

    return "/oficial/asignacion/consultar/consultar_asignacion";
}
    @PostMapping("/asignacion/consultar")
    public String procesarConsulta(@ModelAttribute("asignacionDto") AsignacionDTO dto, Model model) {
        List<UsuarioDTO> resultados = new ArrayList<>();
        String criterio = "";

        if (dto.getIdCuartel() != null) {
            resultados = usuariosService.obtenerUsuariosPorCuartel(dto.getIdCuartel());
            criterio = "Cuartel ID: " + dto.getIdCuartel();
        } else if (dto.getIdCompania() != null) {
            resultados = usuariosService.obtenerUsuariosPorCompania(dto.getIdCompania());
            criterio = "Compañía ID: " + dto.getIdCompania();
        } else if (dto.getIdCuerpo() != null) {
            resultados = usuariosService.obtenerUsuariosPorCuerpo(dto.getIdCuerpo());
            criterio = "Cuerpo ID: " + dto.getIdCuerpo();
        } else if (dto.getIdServicio() != null) { // NUEVA CONDICIÓN
            resultados = usuariosService.obtenerUsuariosPorServicio(dto.getIdServicio());
            criterio = "Servicio ID: " + dto.getIdServicio();
        }

        model.addAttribute("usuariosAsignados", resultados);
        model.addAttribute("criterio", criterio);
        model.addAttribute("asignacionDto", new AsignacionDTO()); // Limpiar para sig. búsqueda

        return "/oficial/asignacion/consultar/consultar_asignacion";
    }

    @GetMapping("/asignacion/reasignar")
    public String paginaReasignar(Model model) {
        model.addAttribute("asignacionDto", new AsignacionDTO());
        model.addAttribute("targetAction", "/api/oficial/asignacion/reasignar");
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
