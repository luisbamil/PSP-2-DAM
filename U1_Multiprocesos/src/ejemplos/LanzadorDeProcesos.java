package ejemplos;
import java.io.IOException;

public class LanzadorDeProcesos {
    public static void main(String[] args) {
        try {
            // Intentamos ejecutar un comando del sistema
            Process proceso = Runtime.getRuntime().exec("notepad.exe");
            System.out.println("✅ Proceso iniciado correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al intentar ejecutar el proceso.");
            System.out.println("Detalles técnicos: " + e.getMessage());
        } catch (SecurityException se) {
            System.out.println("🚫 Permiso denegado para ejecutar el proceso.");
        } catch (Exception ex) {
            System.out.println("⚠️ Se produjo un error inesperado.");
            ex.printStackTrace();
        }
    }
}
