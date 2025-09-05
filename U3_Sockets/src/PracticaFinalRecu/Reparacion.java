// Reparacion.java
package PracticaFinalRecu;

public class Reparacion {

    private String idCoche;
    private String problema;
    private double costo;

    public Reparacion(String idCoche, String problema, double costo) {
        this.idCoche = idCoche;
        this.problema = problema;
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Reparación para coche ID: " + idCoche + " - Problema: " + problema + " - Costo: " + costo;
    }
}
