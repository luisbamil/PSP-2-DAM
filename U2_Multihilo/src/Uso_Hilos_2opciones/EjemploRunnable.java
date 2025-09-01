package Uso_Hilos_2opciones;

//Opción 2: Implementar Runnable

class MiTarea implements Runnable {
    public void run() {
        System.out.println("Ejecutando hilo con Runnable");
    }
}
public class EjemploRunnable {
    public static void main(String[] args) {
        Thread hilo = new Thread(new MiTarea());
        hilo.start();
    }
}
