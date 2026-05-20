package com.defensa.gestion_militar.Controllers;

import com.defensa.gestion_militar.DTOs.UsuarioDTO;
import com.defensa.gestion_militar.DTOs.UsuarioRegistroDTO;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Repositorios.Repo_Usuarios;
import com.defensa.gestion_militar.Services.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import java.util.List;

@Controller

public class AuthController {

//    @Autowired
//     Repo_Usuarios repo_usuarios;

    @Autowired
     UsuariosService usuariosService;

    @GetMapping("/login")
    public String mostrarLogin(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Como tu ruta de home requiere un ID, podemos obtenerlo del usuario logueado
            Usuario usuario = usuariosService.obtenerUsuarioLogueado();
            return "redirect:/api/home/" + usuario.getId();
        }
        return "Login"; // Busca Login.html en templates
    }


//    @PostMapping("/login")
//    public String procesarLogin(@ModelAttribute UsuarioRegistroDTO usuarioDto) {
//        usuariosService.validarLogin(usuarioDto.getEmail(),usuarioDto.getContrasenia());
//        return "home";
//    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute UsuarioRegistroDTO usuarioDto, HttpServletRequest request) {
//        // 1. Buscamos el usuario (entidad)
//        Usuario usuario = usuariosService.validarLogin(usuarioDto.getEmail(), usuarioDto.getContrasenia());
//
//        // Usamos el Enum para definir el rol de forma más segura
//        String nombreRol = "ROLE_" + usuario.getTipoUsuario().name();
//
//        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(nombreRol));
//
//        // 4. Autenticamos
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                usuario.getEmail(), null, authorities);
//
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        // 5. Guardamos en la sesión
//        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//
//        return "redirect:/api/home/" + usuario.getId();
        try {
            // 1. Buscamos y validamos el usuario
            Usuario usuario = usuariosService.validarLogin(usuarioDto.getEmail(), usuarioDto.getContrasenia());

            // Validación de seguridad por si el servicio devuelve null en vez de lanzar excepción
            if (usuario == null) {
                return "redirect:/login?error=true";
            }

            // 2. Definimos el rol usando el Enum
            String nombreRol = "ROLE_" + usuario.getTipoUsuario().name();
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(nombreRol));

            // 3. Autenticamos en el contexto de Spring Security
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);

            // 4. Guardamos en la sesión
            request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return "redirect:/api/home/" + usuario.getId();

        } catch (Exception e) {
            // Si las credenciales son incorrectas (o falla el servicio), redirige al login con el parámetro de error
            return "redirect:/login?error=true";
        }
    }


}
