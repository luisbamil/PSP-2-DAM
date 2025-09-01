package Alternativa_Synchronized_wait_notify;
import java.util.concurrent.locks.*;

public class SalaMensajes {
    private String mensaje;
    private boolean disponible = false;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition hayMensaje = lock.newCondition();
    private final Condition espacioLibre = lock.newCondition();

    public void producir(String msg) {
        lock.lock();
        try {
            while (disponible) {
                espacioLibre.await(); // Espera a que el mensaje sea consumido
            }
            mensaje = msg;
            disponible = true;
            System.out.println("📤 Mensaje producido: " + msg);
            hayMensaje.signalAll(); // Despierta a todos los consumidores
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public String consumir() {
        lock.lock();
        try {
            while (!disponible) {
                hayMensaje.await(); // Espera a que haya un mensaje disponible
            }
            disponible = false;
            System.out.println("📥 Mensaje consumido por " + Thread.currentThread().getName());
            espacioLibre.signal(); // Despierta al productor
            return mensaje;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
