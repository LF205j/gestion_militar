package com.defensa.gestion_militar;

import com.defensa.gestion_militar.Entity.Cuartel;
import com.defensa.gestion_militar.Entity.Cuerpo;
import com.defensa.gestion_militar.Entity.Usuario;
import com.defensa.gestion_militar.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GestionMilitarApplication implements CommandLineRunner {

	@Autowired
	private Repo_Usuarios repoUsuarios;

	@Autowired
	private Repo_Cuarteles repoCuarteles;

	@Autowired
	private Repo_Companias repoCompanias;

	@Autowired
	private Repo_Cuerpos repoCuerpos;

	@Autowired
	private Repo_Servicios repoServicios;

	@Autowired
	private Repo_RealizarServicio repo_realizarServicio;

	public static void main(String[] args) {
		SpringApplication.run(GestionMilitarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicacion iniciada correctamente");

		// 3. Usamos repo_users (que es el que maneja la entidad Usuarios)
		List<Usuario> listaUsuarios = repoUsuarios.findAll();

		// 4. Imprimimos cada usuario individualmente
		listaUsuarios.forEach(u -> System.out.println(u.toString()));

		List<Cuartel> cuarteles=repoCuarteles.findAll();
		cuarteles.stream().forEach(cuarteles1 -> System.out.println(cuarteles1.toString()));
		System.out.println("\n");

		List<Cuerpo>cuerpos=repoCuerpos.findAll();
		cuerpos.stream().forEach(cuerpos1 -> System.out.println(cuerpos1.toString()));
	}
}
