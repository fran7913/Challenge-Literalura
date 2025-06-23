package com.alura.Challenge_Literalura;

import com.alura.Challenge_Literalura.principal.Principal;
import com.alura.Challenge_Literalura.repositorios.LibrosRepositorio;
import com.alura.Challenge_Literalura.servicios.LibroService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.muestraElMenu(); // Ya está inyectado automáticamente
	}


}
