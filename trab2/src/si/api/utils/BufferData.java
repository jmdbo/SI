package si.api.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by dd.fernandes on 01/10/2014. This class should be used as the
 * default template for the First Work SI_2014_2015
 * <p/>
 * Main Goal: Keep all the necessary information from the hardware in memory, so
 * that the hardware is used the less times possible. Sec Goal: Keep extra
 * information to help all the other SCSI modules.
 */
public class BufferData {

    /**
     * Public API Variables ( useful for other Modules )
     */
    public boolean keepUpdates;
    public HwByte[] newState = new HwByte[6];
    /**
     * Custom Code ( BEGIN )
     */

    public BlockingQueue<Instruction> SimpleInstruction;

    public BlockingQueue<Instruction> ComplexInstruction;

    public WorldState worldStateCurrent;
    public WorldState worldStateOld;

    public int posX = -1, posZ = -1, posY = -1;

    public boolean emergency;
    
    /**
     * Custom Code ( END )
     */
    /**
     * API variables to use from inside the class
     */
    private HwByte[] state = new HwByte[6];

    public BufferData() {
        for (HwByte h : newState) {
            h = new HwByte();
        }

        this.ComplexInstruction = new ArrayBlockingQueue<>(100);
        this.SimpleInstruction = new ArrayBlockingQueue<>(100);
        this.emergency = false;
        
        this.keepUpdates = true;
        new Thread(new BufferUpdater(this)).start();
    }

    public HwByte getState(int port) {
        return state[port];
    }

    public void setState(HwByte[] state) {
        this.state = state;
    }

    public void updateStatePort(int port, int val) {
        this.state[port] = HwByte.parseInt(val);
    }

    public void setNewStatePort(int port, int val) {
        this.newState[port] = HwByte.parseInt(val);
    }

    public void runAtUpdate() {
        System.out.println(" Run at Update Not yet Implemented!");
    }

    public int getDx(int x) {
        if (x == -1) {
            return 0;
        }
        try {
            return (x - posX) / Math.abs(x - posX);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getDz(int z) {
        if (z == -1) {
            return 0;
        }
        try {
            return (z - posZ) / Math.abs(z - posZ);
        } catch (Exception e) {
            return 0;
        }
    }

    public int getDy(int y) {
        if (y == -1) {
            return 0;
        }
        try {
            return (y - posY) / Math.abs(y - posY);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean pieceAtLift() {
        return getState(2).getBit(7) == 1;
    }

}
