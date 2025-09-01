package Uso_Hilos_2opciones;

//Opción 1: Heredar de Thread
class MiHilo extends Thread {
    public void run() {
        System.out.println("Ejecutando hilo con Thread");
    }
}

public class EjemploThread {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo();
        hilo.start();
    }
}


