package com.alura.Challenge_Literalura.servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper(); //ObjectMapper permite transformar datos entre formatos Java y JSON, tanto al guardar como al leer información.

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {    // Se trata de un método genérico que toma como entrada un texto en formato JSON y una clase que indica el tipo de objeto a generar (como DatosLibros.class).
        try {
            return objectMapper.readValue(json, clase);  // Con esta instrucción se transforma una cadena JSON en una instancia de la clase Java correspondiente.
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);               // Si se produce un fallo durante la conversión, se lanza una excepción en tiempo de ejecución para evitar continuar con información incorrecta.
        }


    }
}