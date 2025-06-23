package com.alura.Challenge_Literalura.repositorios;

import com.alura.Challenge_Literalura.modelos.libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LibrosRepositorio extends JpaRepository<libro, Long> {

    boolean existsByTituloIgnoreCase(String titulo);   //evita que los registros sea duplicados.


    @Query("SELECT l FROM libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :tituloLibro, '%'))")
    List<libro> buscarPorTitulo(@Param("tituloLibro") String tituloLibro);

    @Query("SELECT l FROM libro l ORDER BY l.titulo")
    List<libro> listarTodos();

    @Query("SELECT DISTINCT l.autor FROM libro l WHERE l.autor IS NOT NULL AND l.autor <> '' ORDER BY l.autor")
    List<String> obtenerAutoresUnicos();

    @Query("""
        SELECT DISTINCT l.autor FROM libro l
        WHERE CAST(l.fechaDeNacimiento AS int) <= :anio
          AND (l.fechaDeDeceso IS NULL OR CAST(l.fechaDeDeceso AS int) >= :anio)
    """)
    List<String> obtenerAutoresVivosEnAnio(@Param("anio") Integer anio);

    @Query("SELECT l FROM libro l WHERE LOWER(l.idiomas) LIKE LOWER(CONCAT('%', :idioma, '%'))")
    List<libro> buscarPorIdioma(@Param("idioma") String idioma);


}
