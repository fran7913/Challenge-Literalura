package com.alura.Challenge_Literalura.modelos;

import jakarta.persistence.*;

@Entity
@Table(name= "libros")

public class libro {

@Id
@GeneratedValue

private Long id;

    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idiomas;
    private String fechaDeNacimiento;
    private String fechaDeDeceso;

    //@Column(name = "numero_de_descargas")
    private double numeroDeDescargas;




//--------------getter y setter------------------------


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeDeceso() {
        return fechaDeDeceso;
    }

    public void setFechaDeDeceso(String fechaDeDeceso) {
        this.fechaDeDeceso = fechaDeDeceso;
    }

    public double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}



