package PracticaFinal;

public class Consulta {
    private String descripcion;
    private String fecha;

    public Consulta(String descripcion, String fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return String.format("Descripción: %s, Fecha: %s", descripcion, fecha);
    }
}
