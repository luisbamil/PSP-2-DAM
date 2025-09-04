package EntradaSalida;
import java.io.*;

public class DemoPipesInternos {
    public static void main(String[] args) throws Exception {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);

        // Hilo productor
        new Thread(() -> {
            try (OutputStreamWriter writer = new OutputStreamWriter(pos)) {
                writer.write("Mensaje enviado por pipe\n");
            } catch (IOException e) { e.printStackTrace(); }
        }).start();

        // Hilo consumidor
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(pis))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println("Recibido: " + linea);
                }
            } catch (IOException e) { e.printStackTrace(); }
        }).start();
    }
}
