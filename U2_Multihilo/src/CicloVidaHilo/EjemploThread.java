package CicloVidaHilo;
class MiHilo extends Thread {
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                if (Thread.interrupted()) break;
                System.out.println("Hilo ejecutando: " + i);
                Thread.sleep(500); // pausa 0.5 seg
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }
}

public class EjemploThread {
    public static void main(String[] args) throws InterruptedException {
        MiHilo hilo = new MiHilo();
        hilo.start();          // Inicia hilo
        Thread.sleep(1500);    // Espera 1.5 segundos
        hilo.interrupt();      // Solicita interrupción
        hilo.join();           // Espera que termine
        System.out.println("¿Sigue vivo? " + hilo.isAlive()); // Falso
    }
}
