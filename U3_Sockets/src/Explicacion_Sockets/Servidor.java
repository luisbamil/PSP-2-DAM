package Explicacion_Sockets; 
// Declaramos el paquete donde está la clase. Sirve para organizar el código en módulos.

import java.io.IOException; 
// Importa la excepción que puede lanzarse al trabajar con sockets (errores de E/S).

import java.net.ServerSocket; 
// Clase que representa el socket del lado servidor, que escucha conexiones entrantes.

import java.net.Socket; 
// Clase que representa un socket de comunicación (una conexión concreta con un cliente).

public class Servidor { 
// Definición de la clase Servidor.

    public void ejecutar() { 
        // Método que arranca la lógica del servidor.

        // try-with-resources: crea el ServerSocket y lo cierra automáticamente al salir del bloque.
        try (ServerSocket ss = new ServerSocket(5000)) { 
            // Crea un ServerSocket en el puerto 5000. 
            // Esto significa que el servidor "escucha" en ese puerto esperando clientes.

            System.out.println("Servidor iniciado en el puerto 5000...");
            // Mensaje informativo para saber que el servidor está listo.

            // Espera (bloquea la ejecución) hasta que un cliente se conecte.
            Socket socket = ss.accept(); 
            // Cuando un cliente se conecta, accept() devuelve un objeto Socket para comunicarse con él.

            System.out.println("Cliente conectado desde: " + socket.getInetAddress());
            // Muestra la IP del cliente que se ha conectado.

            // Aquí iría la lógica de comunicación (lectura/escritura de datos).
            // En este ejemplo básico no se envían ni reciben datos, solo se acepta la conexión.
            socket.close(); 
            // Cerramos la conexión con el cliente.

        } catch (IOException e) { 
            // Captura cualquier error de entrada/salida (por ejemplo, si el puerto está ocupado).
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) { 
        // Método principal: punto de entrada del programa.
        new Servidor().ejecutar(); 
        // Crea una instancia de Servidor y llama a ejecutar().
    }
}
