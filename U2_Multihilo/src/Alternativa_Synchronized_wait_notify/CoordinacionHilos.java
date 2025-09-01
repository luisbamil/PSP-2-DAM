package Alternativa_Synchronized_wait_notify;
public class CoordinacionHilos {
    public static void main(String[] args) {
        SalaMensajes sala = new SalaMensajes();

        // Consumidores
        for (int i = 1; i <= 3; i++) {
            Thread consumidor = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    sala.consumir();
                }
            }, "Consumidor-" + i);
            consumidor.start();
        }

        // Productor
        Thread productor = new Thread(() -> {
            String[] mensajes = { "Hola", "¿Cómo estás?", "Java rocks!", "Multihilo", "Final" };
            for (String msg : mensajes) {
                sala.producir(msg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Productor");
        productor.start();
    }
}
