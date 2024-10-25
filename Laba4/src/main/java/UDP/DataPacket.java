package UDP;

import java.io.Serializable;

public class DataPacket implements Serializable {
    private static final long serialVersionUID = 1L;

    private double x;
    private double y;
    private double z;

    public DataPacket(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
