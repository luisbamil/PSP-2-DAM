package ProcesosEjemplos;
public class DemoRuntimeExec {
    public static void main(String[] args) {
        try {
            // En Windows: "notepad"
            // En Linux/Mac: "gedit" o "ls"
            Process p = Runtime.getRuntime().exec("notepad");

            // Espera a que el proceso termine
            int exitCode = p.waitFor();
            System.out.println("Proceso terminó con código: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
