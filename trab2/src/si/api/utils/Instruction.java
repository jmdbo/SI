package si.api.utils;

/**
 * Created by dd.fernandes on 22/10/2014.
 */
public class Instruction {

    private final int x, y, z, put;
    private final String op;

    public Instruction(int x, int y, int z,int put, String op) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.put = put;
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
    
    public int getPut(){
        return put;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Instruction)) return false;
        Instruction inst = (Instruction) obj;
        if (!(getX() == inst.getX()))
            return false;
        // TODO : take care of this equals
        return true;
    }

}


