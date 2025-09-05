package Equipo_Futbol;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Servidor {
	
	//Hashtables
	
	private Hashtable<String, Equipo> equipos = new Hashtable<String, Equipo>();
	private Hashtable<String, Partido> partidos = new Hashtable<String, Partido>();
	
	private static int PORT = 2033;
	
	
	public static void main(String[] args) {
		(new Servidor()).ejecutar();
	}
	
	@SuppressWarnings("resource")
	public void ejecutar() {
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while(true)
			{
				Socket socket = serverSocket.accept();
				(new HiloServidor(socket,this)).start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//Funciones de las clases aqui:
	
	public String addEquipo(String id, String nombre) {
		
		if(!equipos.contains(id) && id!="")
		{
			equipos.put(id, new Equipo(id, nombre));
			return "OK";
		}
		else
			return "FAIL";	
	}
	
	public String removeEquipo(String id) {
		if (equipos.containsKey(id))
		{
			equipos.remove(id);
			return "OK";
		}
		else
			return "FAIL";
	}
	
	public Equipo getEquipo(String id)
	{
		if(equipos.containsKey(id))
			return equipos.get(id);
		else
			return null;
	}
	
	public Hashtable<String, Equipo> listEquipos(){
		if(equipos.size()>0)
			return equipos;
		else
			return null;
	}
	
	public String addPartido(String id1, String id2, String goles1, String goles2)
	{
		if (equipos.containsKey(id1) && equipos.containsKey(id2))
		{
			partidos.put(id1+id2, new Partido(id1, id2, goles1, goles2));
			
			int resultado = Integer.parseInt(goles1) - Integer.parseInt(goles2);
			int suma1=0, suma2=0;
			
			if (resultado==0)
			{
				suma1=1; 
				suma2=1;
			}

			if (resultado>0)
			{
				suma1=3; 
				suma2=0;
			}

			if (resultado<0)
			{
				suma1=0; 
				suma2=3;
			}
				
			equipos.get(id1).setPuntos(equipos.get(id1).getPuntos()+suma1);
			equipos.get(id2).setPuntos(equipos.get(id2).getPuntos()+suma2);
				
			return "OK";
		}
		else
			return "FAIL";
	}

}
