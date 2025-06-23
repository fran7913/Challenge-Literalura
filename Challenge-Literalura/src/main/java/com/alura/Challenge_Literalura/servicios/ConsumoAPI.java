package com.alura.Challenge_Literalura.servicios;

import java.io.IOException;        // Importa la clase IOException, necesaria para manejar errores de entrada/salida.
import java.net.URI;               // Importa la clase URI, que representa una URL para construir la solicitud HTTP.
import java.net.http.HttpClient;   //Importa HttpClient, que se usa para enviar solicitudes HTTP.
import java.net.http.HttpRequest;  //Importa HttpRequest, que representa una solicitud HTTP que será enviada.
import java.net.http.HttpResponse; // Importa HttpResponse, que representa la respuesta recibida tras una solicitud HTTP.

public class ConsumoAPI {

    public String obtenerDatos(String url) {            // Función pública que toma una URL como argumento y retorna un String con la respuesta en formato JSON.
        HttpClient client = HttpClient.newHttpClient(); // Se crea un objeto cliente HTTP para enviar la petición.
        HttpRequest request = HttpRequest.newBuilder()  // Se inicia la preparación de una solicitud HTTP nueva.
                .uri(URI.create(url))                   // Se establece la dirección URI como destino de la petición.
                .build();                               // Se completa la configuración del objeto HttpRequest  .
        HttpResponse<String> response = null;           // Se declara una variable tipo String, inicialmente nula, para guardar la respuesta HTTP.
        try {                                           // Se abre un bloque try para controlar excepciones durante el envío de la petición.
            response = client                           // Se envía la solicitud y se obtiene la respuesta, la cual será tratada como texto (String).
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (
                IOException e) {                       // Se capturan posibles errores de entrada/salida, como fallos de conexión con el servidor.
            throw new RuntimeException(e);              // Si ocurre un error de E/S, se lanza una excepción de tipo RuntimeException.
        } catch (
                InterruptedException e) {              // Se detecta si el hilo fue interrumpido mientras se esperaba la respuesta.
            throw new RuntimeException(e);              // En caso de interrupción, se arroja también una excepción apropiada.
        }

        String json = response.body();                  // Se obtiene el contenido del cuerpo de la respuesta HTTP, que es el JSON en texto.
        return json;                                    // El método retorna la cadena con el contenido JSON al solicitante.
    }
}
