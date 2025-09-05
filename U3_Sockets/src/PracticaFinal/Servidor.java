package PracticaFinal;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private Map<String, Paciente> pacientes = new HashMap<>();
    private Map<String, List<Consulta>> consultas = new HashMap<>();

    public static void main(String[] args) {
        new Servidor().iniciar();
    }

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(2033)) {
            System.out.println("Servidor iniciado en el puerto 2033");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new HiloServidor(clientSocket, this).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    public synchronized String addPaciente(String id, String nombre, String edad, String direccion) {
        if (pacientes.containsKey(id)) return "Error: El ID ya existe.";
        try {
            int edadNum = Integer.parseInt(edad);
            pacientes.put(id, new Paciente(id, nombre, edadNum, direccion));
            consultas.put(id, new ArrayList<>());
            return "Paciente añadido con éxito.";
        } catch (NumberFormatException e) {
            return "Error: La edad debe ser un número.";
        }
    }

    public synchronized String updatePaciente(String id, String nombre, String edad, String direccion) {
        Paciente paciente = pacientes.get(id);
        if (paciente == null) return "Error: Paciente no encontrado.";
        try {
            paciente.setNombre(nombre);
            paciente.setEdad(Integer.parseInt(edad));
            paciente.setDireccion(direccion);
            return "Paciente actualizado con éxito.";
        } catch (NumberFormatException e) {
            return "Error: La edad debe ser un número.";
        }
    }

    public synchronized String removePaciente(String id) {
        if (!pacientes.containsKey(id)) return "Error: Paciente no encontrado.";
        pacientes.remove(id);
        consultas.remove(id);
        return "Paciente eliminado con éxito.";
    }

    public synchronized String getPaciente(String id) {
        Paciente paciente = pacientes.get(id);
        return (paciente == null) ? "Error: Paciente no encontrado." : paciente.toString();
    }

    public synchronized String listPacientes() {
        if (pacientes.isEmpty()) return "No hay pacientes registrados.";
        StringBuilder sb = new StringBuilder();
        for (Paciente paciente : pacientes.values()) {
            sb.append(paciente).append("\n");
        }
        return sb.toString();
    }

    public synchronized String addConsulta(String id, String descripcion, String fecha) {
        List<Consulta> listaConsultas = consultas.get(id);
        if (listaConsultas == null) return "Error: Paciente no encontrado.";
        listaConsultas.add(new Consulta(descripcion, fecha));
        return "Consulta registrada con éxito.";
    }

    public synchronized String listConsultas(String id) {
        List<Consulta> listaConsultas = consultas.get(id);
        if (listaConsultas == null) return "Error: Paciente no encontrado.";
        if (listaConsultas.isEmpty()) return "No hay consultas registradas para este paciente.";
        StringBuilder sb = new StringBuilder();
        for (Consulta consulta : listaConsultas) {
            sb.append(consulta).append("\n");
        }
        return sb.toString();
    }
}
