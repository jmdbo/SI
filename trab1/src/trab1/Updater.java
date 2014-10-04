package trab1;

/**
 * Created by João on 30/09/2014.
 */
public class Updater extends Thread {

    private boolean running;
    private BufferData b;
    private final Hardware h;


    public Updater(BufferData buf, Hardware hard){
        b = buf;
        h = hard;
    }

    @Override
    public void run(){
        running = true;
        while(running) {
            //Check for changes
            for (int i = 0; i < 6; i++) {
                boolean writeBits=false;
                for (int j = 0; j < 8 ; j++) {
                    if(b.bits[i][j]!=b.oldBits[i][j]){
                        writeBits=true;
                    }
                }
                //If there are changes write them to the port
                if(writeBits){
                    int portValue = getByte(b.bits[i]);
                    h.writePort(i,portValue);
                }
            }
            //Reads all ports and wirtes them to the matrix bits
            for (int i = 0; i < 6; i++) {
                int portValue=h.readPort(i);
                for (int j = 0; j < 8 ; j++) {
                    b.bits[i][j]=readBit(portValue,j);
                    b.oldBits[i][j]=b.bits[i][j];
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //Read a bit at a given position from V
    private boolean readBit(int v, int bit){
        int mask = 1 << bit;
        if((mask & v)!=0)
            return true;
        else return false;
    }

    //From a boolean vector of 8 returns an integer that represents the vector
    private int getByte(boolean[] port){
        int value = 0;
        for (int i = 0; i < 8; i++) {
            if(port[i]){
                value=setBit(value, i);
            }
        }
        return value;
    }
    //Makes a bit at a given position 1
    private int setBit(int value, int bit){
        int mask = 1 << bit;
        value = value | mask;
        return value;
    }
}
