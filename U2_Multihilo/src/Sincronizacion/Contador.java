package Sincronizacion;

public class Contador {
    static int contador = 0;

    // Método sincronizado para proteger el acceso a la variable compartida
    public static synchronized void incrementar() {
        contador++; // ✅ Ahora es seguro para múltiples hilos
    }

    public static void main(String[] args) {
        Runnable tarea = () -> {
            for (int i = 0; i < 1000; i++) {
                incrementar(); // Usamos el método sincronizado
            }
        };

        Thread hilo1 = new Thread(tarea);
        Thread hilo2 = new Thread(tarea);

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Resultado esperado: 2000");
        System.out.println("Resultado real: " + contador); // ✅ Ahora sí será 2000
    }
}
