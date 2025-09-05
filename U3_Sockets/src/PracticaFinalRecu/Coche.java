// Coche.java
package PracticaFinalRecu;

public class Coche {

    private String id;
    private String marca;
    private String modelo;
    private String anio;

    public Coche(String id, String marca, String modelo, String anio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + marca + " " + modelo + " (" + anio + ")";
    }
}