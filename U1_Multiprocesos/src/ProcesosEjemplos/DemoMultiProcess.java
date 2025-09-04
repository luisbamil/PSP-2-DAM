package ProcesosEjemplos;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DemoMultiProcess {
    public static void main(String[] args) {
        // Lista de comandos a ejecutar (Windows)
        List<String[]> comandos = Arrays.asList(
            new String[]{"cmd.exe", "/c", "echo Proceso 1 && ping -n 2 127.0.0.1"},
            new String[]{"cmd.exe", "/c", "echo Proceso 2 && ping -n 3 127.0.0.1"},
            new String[]{"cmd.exe", "/c", "echo Proceso 3 && ping -n 4 127.0.0.1"}
        );

        try {
            for (String[] comando : comandos) {
                ProcessBuilder pb = new ProcessBuilder(comando);
                pb.redirectErrorStream(true);
                Process p = pb.start();

                // Cada proceso se maneja en un hilo independiente
                new Thread(() -> {
                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(p.getInputStream()))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            System.out.println("[" + comando[2] + "] " + linea);
                        }
                        int exit = p.waitFor();
                        System.out.println("[" + comando[2] + "] terminado con código " + exit);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
