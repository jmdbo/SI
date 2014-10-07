package trab1;

/**
 * Created by João Barata Oliveira on 30/09/2014.
 */

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;


public class Dispatcher implements Runnable {
    public BlockingQueue<Command> queue;
    public BufferData bufferData;
    private boolean running;

    public Dispatcher(BufferData _bd){
        queue= new LinkedBlockingQueue<Command>();
        bufferData=_bd;
        running = true;
    }

    @Override
    public void run() {
        Command cmd;
        while(running) {
            if((queue.peek()) != null && !bufferData.actionOn) {
                cmd = queue.poll();
                // process msg
                Class c = null;
                try {
                    c = Class.forName(cmd.order);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Action a = null;
                try {
                    a = (Action) c.getConstructor(Command.class, BufferData.class)
                            .newInstance(cmd, bufferData);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                new Thread(a).start();

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void stop(){
        running = false;
    }
}
