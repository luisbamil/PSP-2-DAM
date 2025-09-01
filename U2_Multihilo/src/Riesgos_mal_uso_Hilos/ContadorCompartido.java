package Riesgos_mal_uso_Hilos;
public class ContadorCompartido {
    static int contador = 0;

    public static void main(String[] args) {
        Runnable tarea = () -> {
            for (int i = 0; i < 1000; i++) {
                contador++; //  No es seguro para múltiples hilos
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
        System.out.println("Resultado real: " + contador); // ❌ Puede ser < 2000
    }
}
