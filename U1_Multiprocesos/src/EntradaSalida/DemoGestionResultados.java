package EntradaSalida;
import java.io.*;

public class DemoGestionResultados {
    public static void main(String[] args) {
        try {
            // En Windows: "ipconfig /all"
            // En Linux/Mac: "ifconfig"
            String[] comando = {"cmd.exe", "/c", "ipconfig /all"};
            ProcessBuilder pb = new ProcessBuilder(comando);

            // Combina stdout y stderr en un único stream
            pb.redirectErrorStream(true);

            Process p = pb.start();

            // Leer la salida (stdout + stderr)
            try (BufferedReader reader = new BufferedReader(
                     new InputStreamReader(p.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // Esperar finalización y obtener código de salida
            int exitCode = p.waitFor();
            System.out.println("Proceso finalizado con código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
