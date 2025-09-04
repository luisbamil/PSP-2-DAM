package EntradaSalida;
import java.io.*;

public class DemoComunicacion {
    public static void main(String[] args) {
        try {
            // Proceso que espera entrada: en Windows usa "findstr", en Linux/Mac usa "grep"
            String[] comando = {"cmd.exe", "/c", "findstr Hola"};
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process p = pb.start();

            // ---- ENVIAR datos al stdin del proceso ----
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(p.getOutputStream()))) {
                writer.write("Hola mundo\n");
                writer.write("Adiós mundo\n");
                writer.flush();
            }

            // ---- LEER stdout del proceso ----
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println("[Salida] " + linea);
                }
            }

            // ---- LEER stderr (si hay) ----
            try (BufferedReader err = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()))) {
                String linea;
                while ((linea = err.readLine()) != null) {
                    System.err.println("[Error] " + linea);
                }
            }

            int exitCode = p.waitFor();
            System.out.println("Código de salida: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
