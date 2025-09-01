package CicloVidaHilo;
public class ContadorHilos extends Thread {
    private String nombre;
    public ContadorHilos(String nombre) {
        this.nombre = nombre;
    }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre + ": " + i);
            try {
                Thread.sleep(1000);  // espera de 1 segundo
            } catch (InterruptedException e) {
                System.out.println(nombre + " interrumpido");
            }
        }
    }

    public static void main(String[] args) {
        new ContadorHilos("Hilo 1").start();
        new ContadorHilos("Hilo 2").start();
    }
}
