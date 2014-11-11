package si.api.utils;

/**
 * Created by dd.fernandes on 22/10/2014.
 */
public class Instruction {

    private final int x, y, z;
    private final String op;

    public Instruction(int x, int y, int z, String op) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.op = op;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getOp() {
        return op;
    }
}
