package trab1;

/**
 * Created by Joao on 04/10/2014.
 */
public class PutGet extends Action {

    private BufferData buffer;
    boolean isGetting;
    boolean isTray;
    Command cmd;

    public PutGet(Command _cmd, BufferData _buff) {
        super(_cmd, _buff);
        this.buffer = _buff;
        this.cmd = _cmd;
        this.isGetting=_cmd.isGetting;
        this.isTray = _cmd.isTray;
    }

    @Override
    protected void doAction() throws InterruptedException {

        int act_y=buffer.gety();

        if(isTray) trayAction();
        else cellAction();
    }

    private void trayAction() throws InterruptedException {

        //TAKE FROM CELL
        if(isGetting)
        {
            //GO TO ZZ BELLOW
            buffer.moveXZ(0,-1);
            while (!(buffer.getPut()==0))
            {
                Thread.sleep(100);
            }
            buffer.stopZ();

            //Put part into Tray
            buffer.moveY(-1);
            while (!(buffer.gety()==0))
            {
                Thread.sleep(100);
            }
            buffer.stopY();

            //GO TO ZZ ABOVE
            buffer.moveXZ(0,1);
            while (!(buffer.getPut()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopZ();

            buffer.moveY(1);
            //Get part from Tray
            while (!(buffer.gety()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopY();
        }

        //PUT IN CELL
        else
        {
            //GO TO ZZ ABOVE
            buffer.moveXZ(0,1);
            while (!(buffer.getPut()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopZ();

            //Put part into Tray
            buffer.moveY(-1);
            while (!(buffer.gety()==0))
            {
                Thread.sleep(100);
            }
            buffer.stopY();

            Thread.sleep(100);

            //GO TO ZZ BELLOW
            buffer.moveXZ(0,1);
            while (!(buffer.getPut()==0))
            {

                Thread.sleep(100);
            }
            buffer.stopZ();

            //Get part from Tray
            buffer.moveY(-1);
            while (!(buffer.gety()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopY();
        }


    }

    private void cellAction() throws InterruptedException
    {
        //TAKE FROM CELL
        if(isGetting)
        {
            //GO TO ZZ BELLOW
            buffer.moveXZ(0,-1);
            while (!(buffer.getPut()==0))
            {
                Thread.sleep(100);
            }
            buffer.stopZ();

            //Put part into Warehouse
            buffer.moveY(1);
            while (!(buffer.gety()==2))
            {
                Thread.sleep(100);
            }
            buffer.stopY();

            //GO TO ZZ ABOVE
            buffer.moveXZ(0,1);
            while (!(buffer.getPut()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopZ();

            buffer.moveY(-1);
            //Get part from Warehouse
            while (!(buffer.gety()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopY();
        }

        //PUT IN CELL
        else
        {
            //GO TO ZZ ABOVE
            buffer.moveXZ(0,1);
            while (!(buffer.getPut()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopZ();

            //Put part into Warehouse
            buffer.moveY(1);
            while (!(buffer.gety()==2))
            {
                Thread.sleep(100);
            }
            buffer.stopY();

            Thread.sleep(100);

            //GO TO ZZ BELLOW
            buffer.moveXZ(0,-1);
            while (!(buffer.getPut()==0))
            {

                Thread.sleep(100);
            }
            buffer.stopZ();

            //Get part from Warehouse
            buffer.moveY(-1);
            while (!(buffer.gety()==1))
            {
                Thread.sleep(100);
            }
            buffer.stopY();
        }
    }
}
