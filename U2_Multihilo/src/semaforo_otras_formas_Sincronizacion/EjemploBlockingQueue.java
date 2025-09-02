package semaforo_otras_formas_Sincronizacion;
import java.util.concurrent.*;

public class EjemploBlockingQueue {
    private static final BlockingQueue<String> cola = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // Productor
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String item = "Item " + i;
                    System.out.println("Produciendo " + item);
                    cola.put(item);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Consumidor
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String item = cola.take();
                    System.out.println("Consumiendo " + item);
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
