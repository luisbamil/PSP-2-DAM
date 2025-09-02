package TiposPatrones;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CajeroThreadPoolDemo {

    // Recurso compartido: la caja registradora
    private final Object cajaRegistradora = new Object();

    // Método que simula el cobro sincronizado
    public void cobrar(String cajero, String cliente, double monto) {
        System.out.println(cajero + " quiere atender a " + cliente + " por $" + monto);

        synchronized (cajaRegistradora) {
            System.out.println(cajero + " está cobrando a " + cliente + "...");
            try {
                Thread.sleep(1000); // Simula el tiempo de cobro
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(cajero + " ha terminado con " + cliente);
        }
    }

    public static void main(String[] args) {
        CajeroThreadPoolDemo demo = new CajeroThreadPoolDemo();

        // Crear un ThreadPool con 3 hilos
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Enviar tareas (cajeros) al pool
        pool.submit(() -> demo.cobrar("Cajero 1", "Luis", 23.50));
        pool.submit(() -> demo.cobrar("Cajero 2", "Ana", 42.10));
        pool.submit(() -> demo.cobrar("Cajero 3", "Carlos", 17.80));
        pool.submit(() -> demo.cobrar("Cajero 4", "María", 31.25));
        pool.submit(() -> demo.cobrar("Cajero 5", "Pedro", 12.90));

        // Cerrar el pool cuando termine
        pool.shutdown();
    }
}
