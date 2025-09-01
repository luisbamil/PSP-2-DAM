package HilosRunnable_2Formas;
public class EjemploLambda {
    public static void main(String[] args) {
        Runnable tarea = () -> {
            System.out.println("Ejecutando hilo con lambda: " + Thread.currentThread().getName());
        };

        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}
