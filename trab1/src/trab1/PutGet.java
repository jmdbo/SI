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


    }
}
