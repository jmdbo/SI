package trab1;

/**
 * Created by João on 01/10/2014.
 */
public class GotoPosition extends Action {
    private BufferData buffer;
    int x;
    int z;
    boolean isGetting;

    public GotoPosition(Command _cmd, BufferData _buff) {
        super(_cmd, _buff);
        this.buffer = _buff;
        this.x = cmd.x;
        this.z = cmd.z;
        this.isGetting= cmd.isGetting;
    }
    @Override
    protected void doAction() throws InterruptedException {
        int act_x=buffer.getx();
        int act_z=buffer.getz();
        int x_dir;
        int z_dir;

        if(act_x==-1 || act_z==-1){
            calibrate();
            Thread.sleep(200);
            act_x=0;
            act_z=0;
        }

        x_dir= x - act_x;
        z_dir= z - act_z;
        buffer.moveXZ(x_dir,z_dir);
        boolean inPositionX=false;
        boolean inPositionZ=false;

        while(!(inPositionX && inPositionZ)){
            if(buffer.getx()==x){
                buffer.stopX();
                System.out.println("In position X!");
                inPositionX=true;
            }
            if(buffer.getz()==z && isGetting && buffer.getPut()==0){
                buffer.stopZ();
                System.out.println("In position Z!");
                inPositionZ =true;
            }
            if(buffer.getz()==z && !isGetting && buffer.getPut()==1){
                buffer.stopZ();
                System.out.println("In position Z!");
                inPositionZ =true;
            }
            Thread.sleep(100);
        }

        System.out.println("Implemented!");
    }

    private void calibrate(){
        boolean inPosition=false;
        boolean inPositionY=false;
        int dir_x=0;
        int dir_z=0;

        if(buffer.gety()==-1 || buffer.gety()==2){
            buffer.moveY(-1);
        }else if (buffer.gety()==0){
            buffer.moveY(1);
        }

        while(!inPositionY){
            if(buffer.gety()==1){
                buffer.stopY();
                inPositionY = true;
            } if(buffer.gety()==0){
                buffer.moveY(1);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(buffer.getx()!=0){
            dir_x=-1;//-1
        }
        if(buffer.getz()!=0){
            dir_z=-1;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buffer.moveXZ( dir_x, dir_z);

        while(!inPosition){
            int getX =buffer.getx();
            int getPut =buffer.getPut();
            int getZ = buffer.getz();
            int getY = buffer.gety();
            if(buffer.getx()==0){
                buffer.stopX();
            }
            if(buffer.getz()==0 && buffer.getPut()==0){
                buffer.stopZ();
            }
            if(buffer.getz()==0 && getPut==0 && getX==0){
                inPosition=true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
