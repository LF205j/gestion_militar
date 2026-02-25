package com.defensa.gestion_militar.Controllers;

import com.defensa.gestion_militar.DTOs.CompaniaDTO;
import com.defensa.gestion_militar.DTOs.CuartelDTO;
import com.defensa.gestion_militar.DTOs.CuerpoDTO;
import com.defensa.gestion_militar.Repositorios.Repo_Companias;
import com.defensa.gestion_militar.Repositorios.Repo_Cuarteles;
import com.defensa.gestion_militar.Repositorios.Repo_Cuerpos;
import com.defensa.gestion_militar.Services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/soldado")
public class SoldadoController {
    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/consultar/cuartel")
    public String consultarCuartel(Model model) {
        // 1. Obtenemos los datos del service
        CuartelDTO cuartel = usuariosService.obtenerCuartelUsuarioIdentificado();
        // 2. "Cargamos" los datos en el modelo para que el HTML los vea
        model.addAttribute("cuartel", cuartel);
        // 3. Devolvemos el nombre del archivo .html (sin el .html)
        return "/soldado/consultar/consultar_cuartel";
    }

    @GetMapping("/consultar/compania")
    public String consultarCompania(Model model){
        CompaniaDTO compania= usuariosService.obtenerCompaniaUsuarioIdentificado();
        model.addAttribute("compania",compania);
        return "/soldado/consultar/consultar_compania";
    }

    @GetMapping("/consultar/cuerpo")
    public String consultarCuerpo(Model model){
        CuerpoDTO cuerpo=usuariosService.obtenerCuerpoUsuarioIdentificado();
        model.addAttribute("cuerpo",cuerpo);
        return "/soldado/consultar/consultar_cuerpo";
    }




}
