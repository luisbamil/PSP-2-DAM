// ---- Servidor.java ----

package Congreso_Sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {

    public void ejecutar() {
        try (ServerSocket ss = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado en el puerto 5000...");
            while (true) {
                new HiloServidor(ss.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor().ejecutar();
    }
}