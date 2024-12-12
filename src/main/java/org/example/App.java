package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        try {
            // URL de la API de Gemini, con el API Key en la URL
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyDYQRHKHl1qAbXNPoax9rpGj_icr_yduJA";
            URL url = new URL(apiUrl);

            // Configurar la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Hacer que la conexión soporte la escritura del cuerpo
            connection.setDoOutput(true);

            // Cuerpo de la solicitud (datos JSON que se envían)
            String jsonData = "{\"contents\":[{\"parts\":[{\"text\":\"Dime si XML mola\"}]}]}";

            // Enviar los datos JSON al servidor
            try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
                writer.writeBytes(jsonData);
                writer.flush();
            }

            // Leer la respuesta utilizando Commons IO
            String response = IOUtils.toString(connection.getInputStream(), String.valueOf(StandardCharsets.UTF_8));

            // Parsear la respuesta JSON utilizando Gson
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

            // Imprimir la respuesta
            System.out.println("Respuesta de la API:");
            System.out.println(jsonResponse.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
