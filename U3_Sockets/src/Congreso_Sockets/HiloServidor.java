// ---- HiloServidor.java ----

package Congreso_Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;

public class HiloServidor extends Thread {

    private final Socket socket;
    private final Hashtable<String, Congreso> congreso;

    public HiloServidor(Socket socket) {
        this.socket = socket;
        this.congreso = new Hashtable<>();
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println("Línea recibida: '" + linea + "'");

                String[] partes = linea.split(" ", 2);
                String comando = partes[0];
                String datos = (partes.length > 1) ? partes[1] : "";

                String respuesta = procesarComando(comando, datos);
                pw.println(respuesta);
            }

        } catch (IOException e) {
            System.err.println("Error en HiloServidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String procesarComando(String comando, String datos) {
        try {
            switch (comando) {
                case "ADD":
                    return agregar(datos);
                case "UPDATE":
                    return actualizar(datos);
                case "GET":
                    return obtener(datos);
                case "REMOVE":
                    return eliminar(datos);
                default:
                    return "Comando no reconocido";
            }
        } catch (Exception e) {
            return "Error procesando comando: " + e.getMessage();
        }
    }

    private String agregar(String datos) {
        String[] partes = datos.split("#");
        if (partes.length != 3) return "Error: Datos inválidos para ADD";

        congreso.put(partes[0], new Congreso(partes[0], partes[1], partes[2]));
        return "OK añadido";
    }

    private String actualizar(String datos) {
        return agregar(datos); // Similar lógica a ADD
    }

    private String obtener(String datos) {
        Congreso c = congreso.get(datos);
        return (c != null) ? "OK " + c : "Error: No encontrado";
    }

    private String eliminar(String datos) {
        return (congreso.remove(datos) != null) ? "OK eliminado" : "Error: No encontrado";
    }
}