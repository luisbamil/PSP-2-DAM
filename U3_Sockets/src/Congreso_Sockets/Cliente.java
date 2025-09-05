package Congreso_Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        new Cliente().ejecutar();
    }

    private void ejecutar() {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
             Scanner teclado = new Scanner(System.in)) {

            String lineaLeida;

            do {
                mostrarMenu();
                System.out.print("Elige una opción: ");
                lineaLeida = teclado.nextLine();

                String comando = prepararComando(lineaLeida, teclado);
                if (comando == null) {
                    System.out.println("Opción no válida. Intenta de nuevo.\n");
                    continue;
                }

                System.out.println("Enviando: '" + comando + "'");
                pw.println(comando);

                String respuesta = br.readLine();
                System.out.println("\tRespuesta del servidor: " + respuesta + "\n");

            } while (true);

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarMenu() {
        System.out.println("1.- Añadir");
        System.out.println("2.- Obtener");
        System.out.println("3.- Eliminar");
        System.out.println("4.- Actualizar");
    }

    private String prepararComando(String opcion, Scanner teclado) {
        switch (opcion) {
            case "1":
                return "ADD " + solicitarDatos(teclado);
            case "2":
                return "GET " + solicitarId(teclado);
            case "3":
                return "REMOVE " + solicitarId(teclado);
            case "4":
                return "UPDATE " + solicitarDatos(teclado);
            default:
                return null;
        }
    }

    private String solicitarId(Scanner teclado) {
        System.out.print("Introduce ID: ");
        return teclado.nextLine();
    }

    private String solicitarDatos(Scanner teclado) {
        System.out.print("Introduce ID: ");
        String id = teclado.nextLine();
        System.out.print("Introduce Descripción: ");
        String desc = teclado.nextLine();
        System.out.print("Introduce Fecha: ");
        String fecha = teclado.nextLine();
        return id + "#" + desc + "#" + fecha;
    }
}
