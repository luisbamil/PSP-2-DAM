// ---- Congreso.java ----

package Congreso_Sockets;

public class Congreso {

    private final String id;
    private final String desc;
    private final String fecha;

    public Congreso(String id, String desc, String fecha) {
        this.id = id;
        this.desc = desc;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return id + " " + desc + " " + fecha;
    }
}