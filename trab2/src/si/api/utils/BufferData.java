package si.api.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import si.GUI.PopUp;

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
    public HwByte[] oldState = new HwByte[6];
    /**
     * Custom Code ( BEGIN )
     */

    public BlockingQueue<Instruction> SimpleInstruction;
    public Instruction SimpleCurrentInstruction;
    public boolean SimpleCurrentInstructionDone;
    public Instruction SimpleOldInstruction;

    public BlockingQueue<ComplexInstruction> ComplexInstruction;
    public ComplexInstruction ComplexCurrentInstruction;
    public boolean ComplexCurrentInstructionDone;
    public ComplexInstruction ComplexOldInstruction;

    public WorldState worldStateOld;

    public int posX = -1, posZ = -1, posY = -1, posPut=-1;
    
    //array witch give you the occupied cells
    private boolean[][] ocuppiedCells; 
    
    
    public boolean emergency;
    public boolean diagnosed;
    public boolean corrected;
    public int errorID;
    public PopUp gui;
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
        
        //array witch give you the occupied cells (X,Z)
        ocuppiedCells = new boolean [10][5];
        for(int i=0; i<5; i++){
            for(int n=0; n<10; n++){
                ocuppiedCells[n][i] = false;
            }
        }
        
        
        this.ComplexInstruction = new ArrayBlockingQueue<>(100);
        this.SimpleInstruction = new ArrayBlockingQueue<>(100);
        this.emergency = false;
        this.diagnosed = false;
        this.errorID = -1;
        this.corrected = false;
        
        this.keepUpdates = true;
        new Thread(new BufferUpdater(this)).start();
    }

    public HwByte getState(int port) {
        return state[port];
    }

    public void setState(HwByte[] state) {
        this.state = state;
    }
    
    //give a integer array (X,Z) of an ocuppiedcell
    //return X=(1-10); Z=(1-4); return (0,0) if empty
    public int[] ocuppiedcell(){
        int[] aux = new int [2];
        for(int i=0; i<5; i++){
            for(int n=0; n<10; n++){
                if(ocuppiedCells[n][i]){
                    aux[0] = n+1;
                    aux[1] = i+1;
                    return (aux);
                }
            }
        }
        aux[0] = 0;
        aux[1] = 0;
        return (aux);
    }
    
    
    
    //give a integer array (X,Z) of an freecell
    //return X=(1-10); Z=(1-4); return (0,0) if full
    public int[] nextfreecell(){
        int[] aux = new int [2];
        for(int i=0; i<5; i++){
            for(int n=0; n<10; n++){
                if(!ocuppiedCells[n][i]){
                    aux[0] = n+1;
                    aux[1] = i+1;
                    return (aux);
                }
            }
        }
        aux[0] = 0;
        aux[1] = 0;
        return (aux);
    }
    
    
    public boolean cellisbusy (int x, int z){
        
        return ocuppiedCells[x][z];
    
    }
    

    public void BusyCell(int x, int z){
        ocuppiedCells[x][z]= true;
    }
    
    public void FreeCell(int x, int z){
        ocuppiedCells[x][z]= false;
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

    /* 0 ==> No piece in any station
    *  1 ==> Piece in left station
    *  2 ==> Piece in right station
    *  3 ==> Piece in both stations
    */
    public int pieceInStation() {
        if(getState(3).getBit(0)==0 && getState(3).getBit(1)==1){
            return 1;
        }
        if(getState(3).getBit(1) == 0 && getState(3).getBit(0) == 1){
            return 2;
        }
        if(getState(3).getBit(0) == 0 && getState(3).getBit(1) == 0){
            return 3;
        }
        return 0;
    }
}
