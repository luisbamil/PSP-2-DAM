package HilosVSProcesos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CicloProcesoSO {
    public static void main(String[] args) {
        try {
            System.out.println("🟡 Estado: Creación del proceso...");
            // Lanzamos un proceso externo (por ejemplo, ping)
            Process proceso = Runtime.getRuntime().exec("ping -n 3 www.google.com");

            System.out.println("🟢 Estado: Ejecución...");
            // Capturamos la salida del proceso
            BufferedReader salida = new BufferedReader(
                new InputStreamReader(proceso.getInputStream())
            );

            String linea;
            while ((linea = salida.readLine()) != null) {
                System.out.println("📤 Salida: " + linea);
            }

            // Esperamos a que el proceso termine
            int estadoFinal = proceso.waitFor();
            System.out.println("🔵 Estado: Finalización con código " + estadoFinal);

        } catch (IOException e) {
            System.out.println("❌ Error al crear o ejecutar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("⏸️ El proceso fue interrumpido.");
        }
    }
}
