package TiposPatrones;
public class Single_Threaded_Execution {

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

    // Clase interna que representa un cajero (hilo)
    class Cajero extends Thread {
        private final String nombre;
        private final String cliente;
        private final double monto;

        public Cajero(String nombre, String cliente, double monto) {
            this.nombre = nombre;
            this.cliente = cliente;
            this.monto = monto;
        }

        @Override
        public void run() {
            cobrar(nombre, cliente, monto);
        }
    }

    // Método principal
    public static void main(String[] args) {
    	Single_Threaded_Execution demo = new Single_Threaded_Execution();

        Cajero c1 = demo.new Cajero("Cajero 1", "Luis", 23.50);
        Cajero c2 = demo.new Cajero("Cajero 2", "Ana", 42.10);
        Cajero c3 = demo.new Cajero("Cajero 3", "Carlos", 17.80);

        c1.start();
        c2.start();
        c3.start();
    }
}
