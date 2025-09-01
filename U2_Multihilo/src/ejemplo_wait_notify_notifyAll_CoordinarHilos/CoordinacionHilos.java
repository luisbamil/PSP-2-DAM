package ejemplo_wait_notify_notifyAll_CoordinarHilos;
public class CoordinacionHilos {
    public static void main(String[] args) {
        SalaMensajes sala = new SalaMensajes();

        // Crea consumidores
        for (int i = 1; i <= 3; i++) {
            Thread consumidor = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    sala.consumir();
                }
            }, "Consumidor-" + i);
            consumidor.start();
        }

        // Crea productor
        Thread productor = new Thread(() -> {
            String[] mensajes = { "Hola", "¿Cómo estás?", "Java rocks!", "Multihilo", "Final" };
            for (String msg : mensajes) {
                sala.producir(msg);
                try {
                    Thread.sleep(500); // Simula tiempo de producción
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Productor");
        productor.start();
    }
}
