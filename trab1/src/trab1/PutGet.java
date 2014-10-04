package trab1;

/**
 * Created by Joao on 04/10/2014.
 */
public class PutGet extends Action {

    private BufferData buffer;
    boolean isGetting;

    public PutGet(Command _cmd, BufferData _buff) {
        super(_cmd, _buff);
        this.buffer = _buff;
        this.isGetting=cmd.isGetting;
    }

    @Override
    protected void doAction() throws InterruptedException {

        int act_y=buffer.gety();

        //TAKE FROM CELL
        if(isGetting)
        {
            //GO TO ZZ BELLOW
            while (!(buffer.getPut()==0))
            {
                buffer.moveXZ(0,-1);
            }
            //Put part into Warehouse
            while (!(buffer.gety()==2))
            {
                buffer.moveY(1);
            }
            //GO TO ZZ ABOVE
            while (!(buffer.getPut()==1))
            {
                buffer.moveXZ(0,1);
            }
            //Get part from Warehouse
            while (!(buffer.gety()==1))
            {
                buffer.moveY(-1);
            }
        }

        //PUT IN CELL
        else
        {
            //GO TO ZZ ABOVE
            while (!(buffer.getPut()==1))
            {
                buffer.moveXZ(0,1);
            }//Put part into Warehouse
            while (!(buffer.gety()==2))
            {
                buffer.moveY(1);
            }
            //GO TO ZZ BELLOW
            while (!(buffer.getPut()==0))
            {
                buffer.moveXZ(0,-1);
            }
            //Get part from Warehouse
            while (!(buffer.gety()==1))
            {
                buffer.moveY(-1);
            }
        }
    }
}
