package Equipo_Futbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Hashtable;

public class HiloServidor extends Thread {
	
	private Socket socket;
	private Servidor servidor;
	
	public HiloServidor(Socket socket, Servidor servidor) {
		
		this.socket = socket;
		this.servidor = servidor;
	}
	
	
	public void run() {

		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			Hashtable<String, Equipo> equipos = null;
			Hashtable<String, Partido> partidos = null;
			String lineaLeida = "";
			String [] partes = null;
			
			//RECIBIR COMANDOS
			
			while(true) {
				
				//Recibo la linea y la separo en partes
				lineaLeida = br.readLine();
				partes = lineaLeida.split(","); // PARA GUARDAR LAS PARTES A PARTIR DE LA COMA
				
				System.out.println("Comando: "+partes[0]);
				//A�adir comandos ADDEQUIPO...
				switch (partes[0]) {
				case "ADDEQUIPO":
					
					pw.println(this.servidor.addEquipo(partes[1], partes[2]));
					pw.flush();
					
					break;
					
				case "REMOVEEQUIPO":
					
					pw.println(this.servidor.removeEquipo(partes[1]));
					pw.flush();
					
					break;
					
				case "GETEQUIPO":
					
					Equipo equipo = this.servidor.getEquipo(partes[1]);
					lineaLeida = "ID: "+equipo.getId()+" Nombre: "+equipo.getNombre()+" Puntos: "+equipo.getPuntos();
					pw.println(lineaLeida);
					pw.flush();
					
					break;
					
				case "LISTEQUIPOS":
					
					equipos = this.servidor.listEquipos();
					lineaLeida = "List equipos: ";
					for (Equipo equip : equipos.values()) {
						lineaLeida += " ID: "+equip.getId()+" Nombre: "+equip.getNombre()+" Puntos: "+equip.getPuntos()+" ||";
					}
					pw.println(lineaLeida);
					pw.flush();
					
					break;
					
				case "ADDPARTIDO":
					
					pw.println(this.servidor.addPartido(partes[1], partes[2], partes[3], partes[4]));
					pw.flush();
					
					break;
					
				case "EXIT":
					
					lineaLeida = "Goodbye";
					pw.println(lineaLeida);
					pw.flush();
					socket.close();
					break;

				default:
					System.out.println("FAILED "+ partes[0] +"Error 4000 el comando no existe");
					break;
				}
				
			}
			
			
		} catch (Exception e) {
			System.out.println("El socket se ha cerrado");
		}
		
		
	}
	
	

}
