package EntradaSalida;
import java.io.*;

public class DemoPipesExternos {
    public static void main(String[] args) throws Exception {
        // Proceso 1: dir
        ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "/c", "dir");
        Process p1 = pb1.start();

        // Proceso 2: findstr
        ProcessBuilder pb2 = new ProcessBuilder("cmd.exe", "/c", "findstr txt");
        Process p2 = pb2.start();

        // Conectar salida de p1 → entrada de p2
        try (InputStream is = p1.getInputStream();
             OutputStream os = p2.getOutputStream()) {
            is.transferTo(os);
        }

        // Leer salida final del pipe (p2)
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(p2.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        }

        p1.waitFor();
        p2.waitFor();
    }
}
