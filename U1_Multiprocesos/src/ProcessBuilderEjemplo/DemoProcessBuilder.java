package ProcessBuilderEjemplo;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DemoProcessBuilder {
    public static void main(String[] args) {
        try {
            // Comando a ejecutar (ejemplo: listar directorio)
            // En Windows -> "cmd", "/c", "dir"
            // En Linux/Mac -> "bash", "-c", "ls -la"
            String[] comando = {"cmd", "/c", "dir"};

            // Crear el ProcessBuilder con el comando
            ProcessBuilder pb = new ProcessBuilder(comando);

            // Iniciar el proceso
            Process proceso = pb.start();

            // Leer salida del proceso
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que termine y mostrar código de salida
            int exitCode = proceso.waitFor();
            System.out.println("Proceso finalizado con código: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
