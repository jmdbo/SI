package trab1;

/**
 * Created by João on 02/10/2014.
 */
public abstract class Action implements Runnable {

    BufferData bd;
    Command cmd;

    public Action(Command _cmd, BufferData _bd) {
        this.bd = _bd;
        this.cmd = _cmd;
    }



    @Override
    public void run() {
        bd.actionOn = true;
        try {
            doAction();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bd.actionOn = false;
    }

    protected abstract void doAction() throws InterruptedException;
}

