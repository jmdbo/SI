package si.api.utils;

/**
 * Created by João Barata on 16-11-2014.
 */
public class ComplexInstruction extends Instruction {

    private final int x_dest, z_dest;

    public ComplexInstruction(int x, int y, int z, String op, int x_dest, int z_dest) {
        super(x, y, z, op);
        this.x_dest = x_dest;
        this.z_dest = z_dest;
    }


    public int getX_dest() {
        return x_dest;
    }

    public int getZ_dest() {
        return z_dest;
    }


}

