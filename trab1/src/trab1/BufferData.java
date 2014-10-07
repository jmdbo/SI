package trab1;

/**
 * Created by João on 30/09/2014.
 */
public class BufferData {

    public boolean[][] bits;
    public boolean[][] oldBits;
    private final Updater updt;
    public final Hardware hardware;
    public boolean actionOn;

    boolean getBit(int port, int bit) throws Exception {
        if (port >= 6 || bit >= 8) {
            Exception e = new Exception();
            throw e;
        }
        return bits[port][bit];
    }

    public int getx(){
        for (int i = 0; i <8; i++) {
            if(!bits[0][i]){
                return i;
            }
        }
        for (int i = 0; i <2 ; i++) {
            if(!bits[1][i]){
                return i+8;
            }
        }
        return -1;
    }

    public int getz(){
        if(!bits[1][5]||!bits[1][6])
            return 4;
        if(!bits[1][7] || !bits[2][0])
            return 3;
        if(!bits[2][1] || !bits[2][2])
            return 2;
        if(!bits[2][3] || !bits[2][4])
            return 1;
        if(!bits[2][5] || !bits[2][6])
            return 0;
        return -1;
    }
    /*
    If above Z position return 1
    Below return 0
    Error -1
     */
    public int getPut(){
        if(!bits[1][6]|| !bits[2][0]|| !bits[2][2]|| !bits[2][4]|| !bits[2][6])
            return 0;
        if(!bits[1][5]|| !bits[1][7]|| !bits[2][1]|| !bits[2][3]|| !bits[2][5])
            return 1;
        return -1;
    }

    public BufferData(Hardware hardware){
        this.hardware = hardware;
        bits = new boolean[6][8];
        oldBits = new boolean[6][8];
        updt = new Updater(this, hardware);
        updt.start();
    }

    public void moveXZ(int directionX, int directionZ){
        if(!bits[1][3] || directionX==0){
            if(directionX > 0){
                bits[4][0]=true;
                bits[4][1]=false;
            } else if(directionX < 0){
                bits[4][1]=true;
                bits[4][0]=false;
            } else{
                bits[4][1]=false;
                bits[4][0]=false;
            }

            if(directionZ>0){
                bits[4][5]=true;
                bits[4][6]=false;
            }else if(directionZ < 0){
                bits[4][6]=true;
                bits[4][5]=false;
            } else{
                bits[4][5]=false;
                bits[4][6]=false;
            }

        }
    }

    public void moveY(int direction){
        if(direction>0){
            bits[4][4] = true;
            bits[4][3] = false;
        }
        if(direction<0){
            bits[4][3] = true;
            bits[4][4] = false;
        }
    }

    public void stopZ(){
        bits[4][5]=false;
        bits[4][6]=false;
    }

    public void stopX(){
        bits[4][1]=false;
        bits[4][0]=false;
    }

    public void stopY(){
        bits[4][3] = false;
        bits[4][4] = false;
    }


    public int gety() {
        if(!bits[1][2]){
            return 0;
        }
        if(!bits[1][3]){
            return 1;
        }
        if (!bits[1][4]){
            return 2;
        }
        return -1;
    }

    public boolean hasPiece(){
        return bits[2][7];
    }
}
