package HilosRunnable_2Formas;
public class EjemploAnonima {
    public static void main(String[] args) {
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                System.out.println("Ejecutando hilo con clase anónima: " + Thread.currentThread().getName());
            }
        };

        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}
