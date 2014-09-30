package trab1;

import java.util.concurrent.Semaphore;

/**
 * Created by João on 30/09/2014.
 */
public class hardwareThread extends Thread {
    private final Semaphore semKit;
    private final Hardware kit;
    private boolean threadSuspended;

    public hardwareThread(/*Semaphore sem,*/ Hardware kit1){
        super();
        semKit = new Semaphore(1);
        kit = kit1;
    }

    public void run(){
        while (true) {
            try {
                Thread.sleep(100);
                System.out.println("Thread Running");

                synchronized(this) {
                    while (threadSuspended) {

                        wait();

                        System.out.println("Thread Suspended");
                    }
                }
            } catch (InterruptedException e){
            }
        }
    }
}
