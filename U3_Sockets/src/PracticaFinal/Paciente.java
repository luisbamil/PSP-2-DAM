package PracticaFinal;

public class Paciente {
    private String id;
    private String nombre;
    private int edad;
    private String direccion;

    public Paciente(String id, String nombre, int edad, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Nombre: %s, Edad: %d, Dirección: %s", id, nombre, edad, direccion);
    }
}
