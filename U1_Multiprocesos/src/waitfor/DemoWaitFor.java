package waitfor;
import java.io.IOException;

public class DemoWaitFor {
    public static void main(String[] args) {
        try {
            // En Windows: "ping -n 3 127.0.0.1"
            // En Linux/Mac: "ping -c 3 127.0.0.1"
            String[] comando = {"cmd.exe", "/c", "ping -n 3 127.0.0.1"};
            ProcessBuilder pb = new ProcessBuilder(comando);

            Process p = pb.start();
            System.out.println("Proceso lanzado, esperando que termine...");

            // Bloquea hasta que el proceso finalice
            int exitCode = p.waitFor();

            System.out.println("Proceso terminado con código: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
