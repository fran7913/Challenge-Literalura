package com.alura.Challenge_Literalura.servicios;

import com.alura.Challenge_Literalura.modelos.DatosAutor;
import com.alura.Challenge_Literalura.modelos.DatosLibros;
import com.alura.Challenge_Literalura.modelos.libro;
import com.alura.Challenge_Literalura.repositorios.LibrosRepositorio;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class LibroService {

    private final LibrosRepositorio repositorio;

    public LibroService(LibrosRepositorio repositorio) {
        this.repositorio = repositorio;
    }


    public List<libro> buscarAutoresVivosEnAnio(int anio) {
        List<libro> libros = repositorio.findAll();

        return libros.stream().filter(libro -> {
            try {
                int nacimiento = Integer.parseInt(libro.getFechaDeNacimiento());
                int fallecimiento = libro.getFechaDeDeceso() != null && !libro.getFechaDeDeceso().isBlank()
                        ? Integer.parseInt(libro.getFechaDeDeceso())
                        : Integer.MAX_VALUE;

                return nacimiento <= anio && fallecimiento >= anio;
            } catch (NumberFormatException e) {
                return false;
            }
        }).toList();
    }

    //--------------
    public List<String> autoresVivosEnAnio(int anio) {
        return repositorio.obtenerAutoresVivosEnAnio(anio);
    }

    //---------------------

    public List<libro> buscarLibrosPorIdioma(String idioma) {
        return repositorio.buscarPorIdioma(idioma);
    }

    public void guardarLibro(libro libro) {
        repositorio.save(libro);
    }

    public List<libro> obtenerLibros() {
        return repositorio.findAll();
    }



    public void guardarLibro(DatosLibros datos) {    // Metodo que recibe el nuevo libro
        libro nuevo = new libro();
        nuevo.setTitulo(datos.titulo());

        // Toma el primer autor para simplificar
        if (!datos.autor().isEmpty()) {
            DatosAutor autor = datos.autor().get(0);
            nuevo.setAutor(autor.nombre());
            nuevo.setFechaDeNacimiento(autor.fechaDeNacimiento());
            nuevo.setFechaDeDeceso(autor.fechaDeDeceso());



        }

        nuevo.setIdiomas(String.join(",", datos.idiomas()));
        repositorio.save(nuevo);


    }
    public List<libro> obtenerTodosLosLibros() {
        return repositorio.findAll();
    }

    public List<String> obtenerAutoresRegistrados() {
        return repositorio.obtenerAutoresUnicos();
    }



}