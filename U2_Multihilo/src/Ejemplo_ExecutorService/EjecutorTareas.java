package Ejemplo_ExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EjecutorTareas {
    public static void main(String[] args) {
        // Crea un pool de 3 hilos reutilizables
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Enviamos 5 tareas al pool
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            pool.execute(() -> {
                System.out.println("🔧 Hilo " + Thread.currentThread().getName() + " ejecutando tarea " + id);
                try {
                    // Simula trabajo con una pausa
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("⚠️ Tarea " + id + " fue interrumpida");
                }
            });
        }

        // Cerramos el pool: no se aceptan más tareas
        pool.shutdown();

        try {
            // Esperamos hasta que todas las tareas finalicen (máximo 5 segundos)
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("⏳ Algunas tareas no terminaron a tiempo. Forzando cierre...");
                pool.shutdownNow(); // Fuerza la interrupción de tareas pendientes
            } else {
                System.out.println("✅ Todas las tareas han finalizado correctamente.");
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("❌ El hilo principal fue interrumpido.");
        }
    }
}
