package EntradaSalida;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DemoTimeout {
    public static void main(String[] args) {
        try {
            // Ejemplo: ping de 10 intentos (tardará varios segundos)
            String[] comando = {"cmd.exe", "/c", "ping -n 10 127.0.0.1"};
            ProcessBuilder pb = new ProcessBuilder(comando);

            Process p = pb.start();
            System.out.println("Proceso iniciado, esperando máximo 3 segundos...");

            // Espera con timeout
            if (p.waitFor(3, TimeUnit.SECONDS)) {
                int exitCode = p.exitValue();
                System.out.println("Proceso finalizó con código: " + exitCode);
            } else {
                System.out.println("Tiempo excedido, terminando proceso...");
                p.destroy(); // intento suave
                if (p.isAlive()) {
                    p.destroyForcibly(); // si no muere, forzar
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
