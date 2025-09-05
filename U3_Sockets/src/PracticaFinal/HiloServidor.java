package PracticaFinal;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {
    private Socket socket;
    private Servidor servidor;

    public HiloServidor(Socket socket, Servidor servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {

            String lineaLeida;
            while ((lineaLeida = br.readLine()) != null) {
                String[] partes = lineaLeida.split(",");
                String respuesta;

                try {
                    switch (partes[0]) {
                        case "ADDPACIENTE":
                            respuesta = servidor.addPaciente(partes[1], partes[2], partes[3], partes[4]);
                            break;
                        case "UPDATEPACIENTE":
                            respuesta = servidor.updatePaciente(partes[1], partes[2], partes[3], partes[4]);
                            break;
                        case "REMOVEPACIENTE":
                            respuesta = servidor.removePaciente(partes[1]);
                            break;
                        case "GETPACIENTE":
                            respuesta = servidor.getPaciente(partes[1]);
                            break;
                        case "LISTPACIENTES":
                            respuesta = servidor.listPacientes();
                            break;
                        case "ADDCONSULTA":
                            respuesta = servidor.addConsulta(partes[1], partes[2], partes[3]);
                            break;
                        case "LISTCONSULTAS":
                            respuesta = servidor.listConsultas(partes[1]);
                            break;
                        case "EXIT":
                            respuesta = "Goodbye";
                            socket.close();
                            return;
                        default:
                            respuesta = "Comando no reconocido.";
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    respuesta = "Error: Comando incompleto o mal formado.";
                }

                pw.println(respuesta);
            }
        } catch (Exception e) {
            System.out.println("Cliente desconectado: " + e.getMessage());
        }
    }
}
