package ejemplo1;
class SalaMensajes {
    private String mensaje;
    private boolean disponible = false;

    // Método para producir un mensaje
    public synchronized void producir(String msg) {
        while (disponible) {
            try {
                wait(); // Espera a que el mensaje anterior sea consumido
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        mensaje = msg;
        
        disponible = true;
        System.out.println("📤 Mensaje producido: " + msg);
        notifyAll(); // Despierta a todos los consumidores
    }

    // Método para consumir un mensaje
    public synchronized String consumir() {
        while (!disponible) {
            try {
                wait(); // Espera a que haya un mensaje disponible
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        disponible = false;
        notify(); // Despierta al productor
        System.out.println("📥 Mensaje consumido por " + Thread.currentThread().getName());
        return mensaje;
    }
}
