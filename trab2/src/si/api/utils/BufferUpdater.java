package si.api.utils;

import si.api.hardware;

/**
 * Created by dd.fernandes on 01/10/2014.
 */
public class BufferUpdater implements Runnable {

    BufferData bd;
    hardware hw;

    public BufferUpdater(BufferData BufferData) {
        this.bd = BufferData;
        this.hw = new hardware();
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
    public void run() {

        System.out.println("BufferUpdater Initialized...");

        while (bd.keepUpdates) {

            /**
             * Validating newState
             */
            if (bd.newState[0] == null) {
                bd.setNewStatePort(0, hw.readPort(0));
                bd.setNewStatePort(1, hw.readPort(1));
                bd.setNewStatePort(2, hw.readPort(2));
                bd.setNewStatePort(3, hw.readPort(3));
                bd.setNewStatePort(4, hw.readPort(4));
                bd.setNewStatePort(5, hw.readPort(5));
            }
            bd.updateStatePort(0, hw.readPort(0));
            bd.updateStatePort(1, hw.readPort(1));
            bd.updateStatePort(2, hw.readPort(2));
            bd.updateStatePort(3, hw.readPort(3));
            bd.updateStatePort(4, hw.readPort(4));
            bd.updateStatePort(5, hw.readPort(5));

            if (!bd.newState[4].equals(bd.getState(4))) {
                hw.writePort(4, bd.newState[4].toInt());
                System.out.println("New Val P4 " + bd.newState[4].toString());
            }

            if (!bd.newState[5].equals(bd.getState(5))) {
                hw.writePort(5, bd.newState[5].toInt());
                System.out.println("New Val P5 " + bd.newState[5].toString());
            }

            bd.runAtUpdate();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
