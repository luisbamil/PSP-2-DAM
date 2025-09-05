package PracticaFinal;

import java.io.*;
import java.net.*;
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
                System.out.println("\n--- Menú Hospital ---");
                System.out.println("1. Añadir Paciente");
                System.out.println("2. Actualizar Paciente");
                System.out.println("3. Eliminar Paciente");
                System.out.println("4. Consultar Paciente");
                System.out.println("5. Listar Pacientes");
                System.out.println("6. Registrar Consulta");
                System.out.println("7. Listar Consultas de un Paciente");
                System.out.println("8. Salir");
                System.out.print("Selecciona una opción: ");
                opcion = teclado.nextLine();

                String lineaLeida;
                switch (opcion) {
                    case "1":
                        System.out.print("ID: ");
                        String id = teclado.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = teclado.nextLine();
                        System.out.print("Edad: ");
                        String edad = teclado.nextLine();
                        System.out.print("Dirección: ");
                        String direccion = teclado.nextLine();
                        lineaLeida = "ADDPACIENTE," + id + "," + nombre + "," + edad + "," + direccion;
                        break;
                    case "2":
                        System.out.print("ID: ");
                        id = teclado.nextLine();
                        System.out.print("Nombre: ");
                        nombre = teclado.nextLine();
                        System.out.print("Edad: ");
                        edad = teclado.nextLine();
                        System.out.print("Dirección: ");
                        direccion = teclado.nextLine();
                        lineaLeida = "UPDATEPACIENTE," + id + "," + nombre + "," + edad + "," + direccion;
                        break;
                    case "3":
                        System.out.print("ID: ");
                        id = teclado.nextLine();
                        lineaLeida = "REMOVEPACIENTE," + id;
                        break;
                    case "4":
                        System.out.print("ID: ");
                        id = teclado.nextLine();
                        lineaLeida = "GETPACIENTE," + id;
                        break;
                    case "5":
                        lineaLeida = "LISTPACIENTES";
                        break;
                    case "6":
                        System.out.print("ID: ");
                        id = teclado.nextLine();
                        System.out.print("Descripción: ");
                        String descripcion = teclado.nextLine();
                        System.out.print("Fecha: ");
                        String fecha = teclado.nextLine();
                        lineaLeida = "ADDCONSULTA," + id + "," + descripcion + "," + fecha;
                        break;
                    case "7":
                        System.out.print("ID: ");
                        id = teclado.nextLine();
                        lineaLeida = "LISTCONSULTAS," + id;
                        break;
                    case "8":
                        lineaLeida = "EXIT";
                        break;
                    default:
                        lineaLeida = "";
                        System.out.println("Opción no válida.");
                }

                if (!lineaLeida.isEmpty()) {
                    pw.println(lineaLeida);
                    System.out.println(br.readLine());
                }
            } while (!"8".equals(opcion));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
