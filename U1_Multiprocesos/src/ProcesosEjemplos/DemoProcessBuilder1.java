package ProcesosEjemplos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DemoProcessBuilder1 {
    public static void main(String[] args) {
        // Crear el ProcessBuilder con el comando
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "ipconfig");

        // Combina salida estándar y de error en un solo stream
        pb.redirectErrorStream(true);

        try {
            // Inicia el proceso
            Process p = pb.start();

            // Captura la salida del proceso
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Espera a que termine el proceso
            int exitCode = p.waitFor();
            System.out.println("Proceso finalizado con código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
