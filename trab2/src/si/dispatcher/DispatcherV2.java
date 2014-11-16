package si.dispatcher;

import java.util.logging.Level;
import java.util.logging.Logger;
import si.api.utils.BufferData;

/**
 * Created by dd.fernandes on 04/11/2014.
 */
public class DispatcherV2 extends Dispatcher implements Runnable {

    public DispatcherV2(BufferData bufferdata) {
        super(bufferdata);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used to
     * create a thread, starting the thread causes the object's <code>run</code>
     * method to be called in that separately executing thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may take
     * any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {

        int x = 0, y = 0, z = 0;

        try {
            calibrate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("System Calibrated");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DispatcherV2.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {

            if (data.emergency) {
                axis_xz(0, 0);
                axis_y(0);
                continue;
            }

            try {
                data.SimpleOldInstruction = data.SimpleCurrentInstruction;
                data.SimpleCurrentInstruction = data.SimpleInstruction.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Starting Instruction : " + data.SimpleCurrentInstruction.getOp());
            if(!data.SimpleCurrentInstruction.getOp().equals("FINISHED_COMPLEX") && data.ComplexCurrentInstructionDone){
                data.ComplexCurrentInstructionDone = false;
            }

            while (!data.emergency) {

                x = data.getDx(data.SimpleCurrentInstruction.getX());
                y = data.getDy(data.SimpleCurrentInstruction.getY());
                z = data.getDz(data.SimpleCurrentInstruction.getZ());

                axis_xz(x, z);
                axis_y(y);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (x == 0 && y == 0 && z == 0) {
                    break;
                }
            }

            System.out.println("Finished Instruction : " + data.SimpleCurrentInstruction.getOp());
            data.SimpleCurrentInstructionDone = true;
            if(data.SimpleCurrentInstruction.getOp().equals("FINISHED_COMPLEX")){
                data.ComplexCurrentInstructionDone = true;
                data.ComplexOldInstruction = data.ComplexCurrentInstruction;
            }
        }
    }

    private void calibrate() throws InterruptedException {
        int x = 0, z = 0, y = 0;

        while (!data.emergency) {

            if (data.posY == 1) {
                axis_y(0);
                break;
            }

            if (data.posY == -1) {
                y = 1;
            }

            if (data.posY == 0) {
                y = 1;
            }

            if (data.posY == 2) {
                y = -1;
            }

            axis_y(y);

            Thread.sleep(50);
        }

        while (!data.emergency) {

            if (data.posX != -1) {
                x = 0;
            }
            if (data.posZ != -1) {
                z = 0;
            }

            if (data.posZ == -1) {
                z = -1;
            }

            if (data.posX == -1) {
                x = 1;
            }

            axis_xz(x, z);

            if (x == 0 && z == 0) {
                break;
            }

            Thread.sleep(50);
        }

        if (data.emergency) {
            axis_xz(0, 0);
            axis_y(0);
        }
    }
}
