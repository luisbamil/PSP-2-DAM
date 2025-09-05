package Equipo_Futbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	
	public static void main(String[] args) {
		(new Cliente()).ejecutar();
	}
	
	public void ejecutar()
	
	{
		try {
			
			 socket = new Socket("localhost", 2033);
			 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 pw = new PrintWriter(socket.getOutputStream());
			 Scanner teclado = new Scanner(System.in);
			 String lineaLeida ="", opcion="";
			 
			 //MENU
			 
			 do {
				 System.out.println("\n---Menu Global---");
				 System.out.println("1.- A�adir Equipo");
				 System.out.println("2.- Eliminar Equipo");
				 System.out.println("3.- Obtener Equipo");
				 System.out.println("4.- Listar Equipos(Clasificacion)");
				 System.out.println("5.- A�adir Partido");
				 System.out.println("6.- Salir");
				 System.out.print("Elige una opcion: ");
				 opcion = teclado.nextLine();
				 System.out.print("\n");
				 
				 switch (opcion) {
				case "1":
					lineaLeida = "ADDEQUIPO,";
					lineaLeida += ","; // NO DECIR ALUMNOS
					System.out.print("Introduce el ID del equipo: ");
					lineaLeida += teclado.nextLine();
					lineaLeida += ",";
					System.out.print("Introduce el nombre del equipo: ");
					lineaLeida += teclado.nextLine();
					
					//Enviar
					this.pw.println(lineaLeida);
					this.pw.flush();
					
					//Recibir respuesta
					System.out.println(this.br.readLine());
					
					break;
					
				case "2":
					lineaLeida = "REMOVEEQUIPO,";
					System.out.print("Introduce el ID del equipo a borrar: ");
					lineaLeida += teclado.nextLine();
					
					//Enviar
					this.pw.println(lineaLeida);
					this.pw.flush();
					
					//Recibir respuesta
					System.out.println(this.br.readLine());
					
					break;
					
				case "3":
					lineaLeida = "GETEQUIPO,";
					System.out.print("Introduce el ID del equipo a obtener: ");
					lineaLeida += teclado.nextLine();
					
					//Enviar
					this.pw.println(lineaLeida);
					this.pw.flush();
					
					//Recibir respuesta
					System.out.println(this.br.readLine());
					
					break;
					
				case "4":
					lineaLeida = "LISTEQUIPOS";
					
					//Enviar
					this.pw.println(lineaLeida);
					this.pw.flush();
					
					//Recibir respuesta
					System.out.println(this.br.readLine());
					
					break;
					
				case "5":
					lineaLeida = "ADDPARTIDO,";
					lineaLeida += ",";
					System.out.print("Introduce el ID del equipo 1: ");
					lineaLeida += teclado.nextLine();
					lineaLeida += ",";
					System.out.print("Introduce el ID del equipo 2: ");
					lineaLeida += teclado.nextLine();
					lineaLeida += ",";
					System.out.print("Introduce goles del equipo 1: ");
					lineaLeida += teclado.nextLine();
					lineaLeida += ",";
					System.out.print("Introduce goles del equipo 2: ");
					lineaLeida += teclado.nextLine();
					
					//Enviar
					this.pw.println(lineaLeida);
					this.pw.flush();
					
					//Recibir respuesta
					System.out.println(this.br.readLine());
					
					break;
				case "6":
					lineaLeida = "EXIT";
					this.pw.println(lineaLeida);
					this.pw.flush();
					
					System.out.println(this.br.readLine());
					socket.close();
					
					System.out.println("Se cerr� sesi�n");
					
					break;

				default:
					break;
				}
				
			} while (!"6".equals(opcion));
			 teclado.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
