package com.defensa.gestion_militar.Security;

import com.defensa.gestion_militar.Services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private final UsuariosService usuarioService;
//
//    public SecurityConfig(UsuariosService usuarioService) {
//        this.usuarioService = usuarioService;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // 1. CAMBIO CLAVE: Permitir todo temporalmente para que el Main y los Controllers funcionen
//                        .anyRequest().permitAll()
//                )
//                // 2. COMENTAMOS EL LOGIN: Si no tenés el controlador de /login, esto causa bucles
//                 .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/api/home", true)
//                        .permitAll()
//                )
//
//                .logout(logout -> logout.permitAll());
//
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable()) // Mantener deshabilitado para tu lógica manual
            .authorizeHttpRequests(auth -> auth
                    // Permitimos acceso a login, logout y recursos estáticos
                    .requestMatchers("/login", "/logout", "/css/**", "/js/**", "/img/**").permitAll()
                    .anyRequest().permitAll() // Cambiar a .authenticated() cuando quieras activar seguridad total
            )
            // CONFIGURACIÓN DEL LOGOUT
            .logout(logout -> logout
                    .logoutUrl("/logout")               // La URL que activará el cierre de sesión
                    .logoutSuccessUrl("/login?logout")  // A dónde redirige tras salir
                    .invalidateHttpSession(true)        // Borra la sesión del servidor
                    .clearAuthentication(true)          // Limpia los datos de autenticación
                    .deleteCookies("JSESSIONID")        // Borra la cookie del navegador
                    .permitAll()
            );

    return http.build();
}
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Deshabilitar para facilitar pruebas iniciales
//                .authorizeHttpRequests(auth -> auth
//                        // Permitimos el acceso a la vista de consulta de películas
//                        .requestMatchers("/api/consultar/peliculas").permitAll()
//                        // Permitimos recursos estáticos (CSS, Imágenes)
//                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
//                        // El resto de rutas (como /admin/**) requieren autenticación
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Si tienes un HTML propio de login -borrar despues
//                        .defaultSuccessUrl("/api/home", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // La URL que pusiste en el th:href
//                        .logoutSuccessUrl("/login?logout") // Redirigir al login con un mensaje
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll()
//                );
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usamos BCrypt como ya lo haces en tu clase PelimarketApiApplication
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
