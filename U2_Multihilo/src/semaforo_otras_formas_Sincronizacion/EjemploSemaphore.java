package semaforo_otras_formas_Sincronizacion;
import java.util.concurrent.Semaphore;

public class EjemploSemaphore {
    private static final Semaphore semaphore = new Semaphore(2); // Solo 2 hilos pueden acceder

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Hilo " + id + " accediendo al recurso...");
                    Thread.sleep(1000);
                    System.out.println("Hilo " + id + " liberando el recurso.");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
