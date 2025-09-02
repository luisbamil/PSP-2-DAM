package TiposPatrones;
// Clase que representa una tarea
class Task {
    private final int number;

    public Task(int number) {
        this.number = number;
    }

    public int execute() {
        return number * number; // Por ejemplo, calcular el cuadrado
    }

    public int getNumber() {
        return number;
    }
}

// Clase Worker que ejecuta una tarea
class Worker extends Thread {
    private final Task task;
    private int result;

    public Worker(Task task) {
        this.task = task;
    }

    public void run() {
        result = task.execute();
        System.out.println("Worker procesó " + task.getNumber() + " → " + result);
    }

    public int getResult() {
        return result;
    }
}

// Clase Master que coordina los Workers
public class MasterWorkerDemo {
    public static void main(String[] args) throws InterruptedException {
        Task[] tasks = {
            new Task(2),
            new Task(4),
            new Task(6),
            new Task(8)
        };

        Worker[] workers = new Worker[tasks.length];

        // Crear y lanzar los workers
        for (int i = 0; i < tasks.length; i++) {
            workers[i] = new Worker(tasks[i]);
            workers[i].start();
        }

        // Esperar a que todos terminen
        for (Worker worker : workers) {
            worker.join();
        }

        // Recoger resultados
        int total = 0;
        for (Worker worker : workers) {
            total += worker.getResult();
        }

        System.out.println("Resultado total: " + total);
    }
}
