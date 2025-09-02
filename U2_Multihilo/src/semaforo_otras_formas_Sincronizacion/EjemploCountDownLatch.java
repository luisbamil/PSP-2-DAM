package semaforo_otras_formas_Sincronizacion;
import java.util.concurrent.CountDownLatch;

public class EjemploCountDownLatch {
    private static final CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            int id = i;
            new Thread(() -> {
                System.out.println("Hilo " + id + " haciendo trabajo...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                System.out.println("Hilo " + id + " terminado.");
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            System.out.println("Todos los hilos han terminado. Continuando...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
