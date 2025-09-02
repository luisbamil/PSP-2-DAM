package semaforo_otras_formas_Sincronizacion;
import java.util.concurrent.CyclicBarrier;

public class EjemploCyclicBarrier {
    private static final int NUMERO_HILOS = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUMERO_HILOS, () ->
        System.out.println("¡Todos listos! Comenzamos juntos.")
    );

    public static void main(String[] args) {
        for (int i = 1; i <= NUMERO_HILOS; i++) {
            int id = i;
            new Thread(() -> {
                System.out.println("Hilo " + id + " preparado...");
                try {
                    Thread.sleep(500 + id * 200);
                    barrier.await();
                    System.out.println("Hilo " + id + " ejecutando tarea.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
