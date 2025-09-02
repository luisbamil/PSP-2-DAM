package semaforo_otras_formas_Sincronizacion;
import java.util.concurrent.*;

public class SincronizacionDemoCompleta {

    private static final int NUM_TRABAJADORES = 4;
    private static final Semaphore semaphore = new Semaphore(2); // Solo 2 pueden trabajar a la vez
    private static final CountDownLatch latch = new CountDownLatch(NUM_TRABAJADORES);
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_TRABAJADORES);
    private static final BlockingQueue<String> resultados = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_TRABAJADORES);

        for (int i = 0; i < NUM_TRABAJADORES; i++) {
            int id = i;
            executor.submit(() -> {
                try {
                    System.out.println("Trabajador " + id + " esperando en la barrera...");
                    barrier.await(); // Espera a que todos estén listos

                    semaphore.acquire(); // Acceso limitado al recurso
                    System.out.println("Trabajador " + id + " accediendo al recurso...");
                    Thread.sleep(1000); // Simula trabajo
                    resultados.put("Resultado de trabajador " + id);
                    semaphore.release();

                    latch.countDown(); // Señala que ha terminado
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            latch.await(); // Espera a que todos terminen
            System.out.println("Todos los trabajadores han terminado. Procesando resultados...");
            resultados.forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
