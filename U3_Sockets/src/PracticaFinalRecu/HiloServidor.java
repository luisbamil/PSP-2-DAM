// HiloServidor.java
package PracticaFinalRecu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {

    private Socket socket;
    private Servidor servidor;

    public HiloServidor(Socket socket, Servidor servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {

            String lineaLeida;
            while ((lineaLeida = br.readLine()) != null) {
                String[] partes = lineaLeida.split(",");
                String respuesta;

                switch (partes[0]) {
                    case "ADDCOCHE":
                        respuesta = servidor.addCoche(partes[1], partes[2], partes[3], partes[4]);
                        break;
                    case "REMOVECOCHE":
                        respuesta = servidor.removeCoche(partes[1]);
                        break;
                    case "GETCOCHE":
                        respuesta = servidor.getCoche(partes[1]);
                        break;
                    case "LISTCOCHES":
                        respuesta = servidor.listCoches();
                        break;
                    case "ADDREPARACION":
                        respuesta = servidor.addReparacion(partes[1], partes[2], partes[3]);
                        break;
                    case "EXIT":
                        respuesta = "Goodbye";
                        socket.close();
                        return;
                    default:
                        respuesta = "Comando no reconocido";
                }

                pw.println(respuesta);
            }

        } catch (Exception e) {
            System.out.println("El cliente se ha desconectado");
        }
    }
}