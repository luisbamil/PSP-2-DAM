package EntradaSalida;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class DemoBidireccional {
    public static void main(String[] args) throws Exception {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        // Comando "eco" (lee stdin y lo reemite por stdout)
        // Windows: findstr con regex ".*" (todas las líneas con contenido)
        // Linux/Mac: cat -u (sin buffer)
        String[] cmd = isWindows
                ? new String[]{"cmd", "/c", "findstr", "/R", ".*"}
                : new String[]{"bash", "-lc", "cat -u"};

        ProcessBuilder pb = new ProcessBuilder(cmd);
        // No fusionamos streams para demostrar stdout y stderr por separado:
        // pb.redirectErrorStream(true);  // <- déjalo en false para leer stderr aparte
        Process p = pb.start();

        // --- Lector de STDOUT (hilo dedicado) ---
        Thread tOut = new Thread(() -> {
            try (BufferedReader r = new BufferedReader(
                    new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = r.readLine()) != null) {
                    System.out.println("[OUT] " + line);
                }
            } catch (IOException e) { e.printStackTrace(); }
        }, "stdout-reader");

        // --- Lector de STDERR (hilo dedicado) ---
        Thread tErr = new Thread(() -> {
            try (BufferedReader r = new BufferedReader(
                    new InputStreamReader(p.getErrorStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = r.readLine()) != null) {
                    System.err.println("[ERR] " + line);
                }
            } catch (IOException e) { e.printStackTrace(); }
        }, "stderr-reader");

        tOut.start();
        tErr.start();

        // --- Escritor a STDIN (en el hilo principal, pero podría ser otro hilo) ---
        try (BufferedWriter w = new BufferedWriter(
                new OutputStreamWriter(p.getOutputStream(), StandardCharsets.UTF_8))) {

            w.write("Hola proceso\n");
            w.flush();
            Thread.sleep(200); // simulamos actividad

            w.write("Otra línea desde Java\n");
            w.flush();
            Thread.sleep(200);

            w.write("FIN\n");
            w.flush();

            // Importante: cerrar stdin para que el proceso sepa que no hay más datos
        }

        // Esperamos a que el proceso termine y los lectores acaben
        int exit = p.waitFor();
        tOut.join();
        tErr.join();

        System.out.println("Proceso terminó con exitCode=" + exit);
    }
}
