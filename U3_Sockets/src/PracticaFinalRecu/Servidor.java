// Servidor.java
package PracticaFinalRecu;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Servidor {

    private Hashtable<String, Coche> coches = new Hashtable<>();
    private Hashtable<String, Reparacion> reparaciones = new Hashtable<>();
    private static final int PORT = 2033;

    public static void main(String[] args) {
        new Servidor().ejecutar();
    }

    public void ejecutar() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en el puerto " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new HiloServidor(socket, this).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized String addCoche(String id, String marca, String modelo, String anio) {
        if (!coches.containsKey(id)) {
            coches.put(id, new Coche(id, marca, modelo, anio));
            return "Coche añadido con éxito";
        }
        return "Error: El coche con ID " + id + " ya existe";
    }

    public synchronized String removeCoche(String id) {
        if (coches.containsKey(id)) {
            coches.remove(id);
            return "Coche eliminado con éxito";
        }
        return "Error: El coche con ID " + id + " no existe";
    }

    public synchronized String getCoche(String id) {
        Coche coche = coches.get(id);
        if (coche != null) {
            return coche.toString();
        }
        return "Error: El coche con ID " + id + " no existe";
    }

    public synchronized String listCoches() {
        if (coches.isEmpty()) {
            return "No hay coches en el taller";
        }
        StringBuilder listado = new StringBuilder("Listado de coches:\n");
        coches.values().forEach(coche -> listado.append(coche.toString()).append("\n"));
        return listado.toString();
    }

    public synchronized String addReparacion(String id, String problema, String costo) {
        if (coches.containsKey(id)) {
            try {
                double costoDouble = Double.parseDouble(costo);
                Reparacion reparacion = new Reparacion(id, problema, costoDouble);
                reparaciones.put(id + problema, reparacion);
                return "Reparación registrada con éxito para el coche ID: " + id;
            } catch (NumberFormatException e) {
                return "Error: El costo debe ser un número válido";
            }
        }
        return "Error: El coche con ID " + id + " no existe";
    }
}
