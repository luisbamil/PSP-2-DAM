// Cliente.java
package PracticaFinalRecu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        new Cliente().ejecutar();
    }

    public void ejecutar() {
        try (Socket socket = new Socket("localhost", 2033);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
             Scanner teclado = new Scanner(System.in)) {

            String opcion;
            do {
                System.out.println("\n--- Menú Taller de Coches ---");
                System.out.println("1. Añadir Coche");
                System.out.println("2. Eliminar Coche");
                System.out.println("3. Consultar Coche");
                System.out.println("4. Listar Coches");
                System.out.println("5. Registrar Reparación");
                System.out.println("6. Salir");
                System.out.print("Selecciona una opción: ");
                opcion = teclado.nextLine();

                String lineaLeida;
                switch (opcion) {
                    case "1":
                        System.out.print("Introduce el ID del coche: ");
                        String id = teclado.nextLine();
                        System.out.print("Introduce la marca del coche: ");
                        String marca = teclado.nextLine();
                        System.out.print("Introduce el modelo del coche: ");
                        String modelo = teclado.nextLine();
                        System.out.print("Introduce el año del coche: ");
                        String anio = teclado.nextLine();
                        lineaLeida = "ADDCOCHE," + id + "," + marca + "," + modelo + "," + anio;
                        break;
                    case "2":
                        System.out.print("Introduce el ID del coche a eliminar: ");
                        id = teclado.nextLine();
                        lineaLeida = "REMOVECOCHE," + id;
                        break;
                    case "3":
                        System.out.print("Introduce el ID del coche a consultar: ");
                        id = teclado.nextLine();
                        lineaLeida = "GETCOCHE," + id;
                        break;
                    case "4":
                        lineaLeida = "LISTCOCHES";
                        break;
                    case "5":
                        System.out.print("Introduce el ID del coche: ");
                        id = teclado.nextLine();
                        System.out.print("Describe el problema: ");
                        String problema = teclado.nextLine();
                        System.out.print("Introduce el costo de la reparación: ");
                        String costo = teclado.nextLine();
                        lineaLeida = "ADDREPARACION," + id + "," + problema + "," + costo;
                        break;
                    case "6":
                        lineaLeida = "EXIT";
                        break;
                    default:
                        lineaLeida = "";
                        System.out.println("Opción no válida");
                }

                if (!lineaLeida.isEmpty()) {
                    pw.println(lineaLeida);
                    System.out.println(br.readLine());
                }

            } while (!"6".equals(opcion));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}