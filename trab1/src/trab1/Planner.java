package trab1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by João on 07/10/2014.
 */
public class Planner implements Runnable {
    private BlockingQueue<Command> dispatcherQueue;
    public BlockingQueue<Command> plannerQueue;
    private BufferData bufferData;

    private static final boolean ISPUTTING = false;
    private static final boolean ISGETTING = true;
    private static final boolean ISTRAY = true;
    private static final boolean ISCELL = false;




    public Planner(BlockingQueue<Command> dQueue, BufferData _bd){
        this.dispatcherQueue = dQueue;
        this.bufferData = _bd;
        this.plannerQueue = new LinkedBlockingQueue<Command>();
    }

    @Override
    public void run() {
        Command cmd;
        if(plannerQueue.peek() != null){
            cmd = plannerQueue.poll();
            if(cmd.order=="putPiece"){
                if(!bufferData.hasPiece())
                {
                    //(String _order, int _x, int _z, int _tray, boolean _isGetting)
                    dispatcherQueue.add(new Command("trab1.GotoPosition", 0, 0, ISGETTING));
                    dispatcherQueue.add(new Command("trab1.PutGet", ISTRAY, ISGETTING));

                }
                dispatcherQueue.add(new Command("trab1.GotoPosition",cmd.x, cmd.z, ISPUTTING));
                dispatcherQueue.add(new Command("trab1.PutGet", ISCELL, ISPUTTING));

            }
            if(cmd.order=="getPiece"){
                dispatcherQueue.add(new Command("trab1.GotoPosition",cmd.x, cmd.z, ISGETTING));
                dispatcherQueue.add(new Command("trab1.PutGet", ISCELL, ISGETTING));
                dispatcherQueue.add(new Command("trab1.GotoPosition",0, 0, ISPUTTING));
                dispatcherQueue.add(new Command("trab1.PutGet", ISTRAY, ISPUTTING));

            }
            if(cmd.order=="switchPiece"){

            }


        }

    }
}
