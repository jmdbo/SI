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


            }
            if(cmd.order=="getPiece"){

            }
            if(cmd.order=="switchPiece"){

            }


        }

    }
}
