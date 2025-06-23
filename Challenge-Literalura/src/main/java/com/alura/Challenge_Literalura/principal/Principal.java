package com.alura.Challenge_Literalura.principal;

import com.alura.Challenge_Literalura.modelos.Datos;
import com.alura.Challenge_Literalura.modelos.DatosLibros;
import com.alura.Challenge_Literalura.modelos.libro;
import com.alura.Challenge_Literalura.repositorios.LibrosRepositorio;
import com.alura.Challenge_Literalura.servicios.ConsumoAPI;
import com.alura.Challenge_Literalura.servicios.ConvierteDatos;
import com.alura.Challenge_Literalura.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private  LibrosRepositorio repositorio;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private final LibroService libroService;

    public Principal(LibrosRepositorio repository, LibroService libroService) {    // Constructor
        this.repositorio = repository;
        this.libroService = libroService;

    }



    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año  
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);

            opcion = teclado.nextInt();

            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    ListasAutoresVivosPorAño();
                    break;

                case 5:
                    listarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }




    private void buscarPorTitulo() {

        System.out.print("Ingrese el título del libro: ");
        String titulo = teclado.nextLine();
        String url = URL_BASE + "?search=" + titulo.replace(" ", "+");

        String json = consumoAPI.obtenerDatos(url);
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        if (datos.resultado().isEmpty()) {                                                              // comprueba si el libro existe y genera mensaje
            System.out.println("No se encontraron libros con ese título.");
        } else {
            System.out.println("Libros encontrados:");                                                  // Si existe el libro genera mensaje Libros encontrados

            for (DatosLibros libro : datos.resultado()) {                                              //  Para cada objeto DatosLibros (al que llamamos libro) que hay en la lista (o colección) devuelta por datos.resultado()
                if (!repositorio.existsByTituloIgnoreCase(libro.titulo())) {                            //  Revisa si un libro con ese título ya existe en la base de datos (ignorando mayúsculas/minúsculas)
                    System.out.println("- " + libro.titulo());
                    libroService.guardarLibro(libro);                                                  //   Si NO existe, lo guarda usando guardaLibro que esta en la clase LibroService
                } else {
                    System.out.println("- " + libro.titulo() + " (ya existe, no se guarda)");         // Si el libro existe genera mensaje y no guarda el libro
                }
            }

            System.out.println("Proceso de guardado finalizado.");                                    // Mensaje si se cumple la condicion de que el libro no existe en la base de datos
        }
    }
//--------------------------------------------------------------------------
    private void listarLibrosRegistrados() {

        List<libro> libros = libroService.obtenerTodosLosLibros();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            System.out.println("\n Libros registrados:");
            for (libro l : libros) {
                System.out.printf("""
                    -------------------------------------
                    Título: %s
                    Autor: %s
                    Idiomas: %s
                    Año de nacimiento: %s
                    Año de fallecimiento: %s
                    numeroDeDescargas: %s
                    """,
                        l.getTitulo(), l.getAutor(), l.getIdiomas(),
                        l.getFechaDeNacimiento(), l.getFechaDeDeceso(), l.getNumeroDeDescargas());
            }
        }

    }





    //------------------------------------------------------------------------

    private void listarAutoresRegistrados() {

        List<String> autores = libroService.obtenerAutoresRegistrados();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
        } else {
            System.out.println("\n️ Autores registrados:");
            autores.forEach(a -> System.out.println("• " + a));
        }
    }
    //----------------------------------------------------------------------
    private void ListasAutoresVivosPorAño() {

        System.out.println("Ingrese el año para verificar autores vivos:");
        int anio = teclado.nextInt();
        teclado.nextLine();

        List<String> autores = libroService.autoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(System.out::println);
        }

    }
    //---------------------------------------------------------------------------
    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el código del idioma (por ejemplo, 'en' para inglés, 'es' para español): ");
        String idioma = teclado.nextLine();

        List<libro> libros = libroService.buscarLibrosPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma '" + idioma + "'.");
            return;
        }

        System.out.println("\nLibros en idioma '" + idioma + "':");
        libros.forEach(libro -> {
            System.out.println("------------------------------");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("Idiomas: " + libro.getIdiomas());
        });
    }
}



