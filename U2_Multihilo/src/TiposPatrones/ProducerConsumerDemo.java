package TiposPatrones;
import java.util.LinkedList;

public class ProducerConsumerDemo {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int CAPACIDAD = 5;

    public static void main(String[] args) {
        ProducerConsumerDemo demo = new ProducerConsumerDemo();
        demo.iniciar();
    }

    public void iniciar() {
        Thread productor = new Thread(new Productor());
        Thread consumidor = new Thread(new Consumidor());

        productor.start();
        consumidor.start();
    }

    class Productor implements Runnable {
        public void run() {
            int valor = 0;
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() == CAPACIDAD) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    buffer.add(valor);
                    System.out.println("Productor produjo: " + valor);
                    valor++;
                    buffer.notifyAll();
                }

                try {
                    Thread.sleep(500); // Simula tiempo de producción
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumidor implements Runnable {
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int valor = buffer.removeFirst();
                    System.out.println("Consumidor consumió: " + valor);
                    buffer.notifyAll();
                }

                try {
                    Thread.sleep(800); // Simula tiempo de consumo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
