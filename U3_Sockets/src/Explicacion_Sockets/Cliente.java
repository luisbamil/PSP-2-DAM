package Explicacion_Sockets; 
// Mismo paquete que el servidor para mantener la organización.

import java.io.IOException; 
// Necesario para manejar posibles errores de E/S.

import java.net.Socket; 
// Clase que representa el socket del lado cliente.

public class Cliente { 
// Definición de la clase Cliente.

    public static void main(String[] args) { 
        // Método principal: punto de entrada del programa.

        // try-with-resources: crea el socket y lo cierra automáticamente al salir del bloque.
        try (Socket socket = new Socket("localhost", 5000)) { 
            // Crea un socket cliente que se conecta a la IP "localhost" (la misma máquina)
            // y al puerto 5000, donde debe estar escuchando el servidor.

            System.out.println("Conectado al servidor en localhost:5000");
            // Mensaje confirmando que la conexión se ha establecido correctamente.

            // Aquí iría la lógica de envío/recepción de datos.
            // En este ejemplo no se intercambia información, solo se conecta.

        } catch (IOException e) { 
            // Captura errores de conexión (por ejemplo, si el servidor no está disponible).
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
