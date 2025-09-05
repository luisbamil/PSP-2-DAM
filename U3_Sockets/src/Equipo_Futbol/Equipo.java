package Equipo_Futbol;

public class Equipo {
	
	protected String id;
	protected String nombre;
	protected int puntos = 0;
	
	
	public Equipo(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	

}
