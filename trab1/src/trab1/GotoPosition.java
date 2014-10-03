package trab1;

/**
 * Created by João on 01/10/2014.
 */
public class GotoPosition extends Action {
    private BufferData buffer;
    int x;
    int z;
    int isGetting;

    public GotoPosition(Command _cmd, BufferData _buff) {
        super(_cmd, _buff);
        this.buffer = _buff;
        this.x = cmd.x;
        this.z = cmd.y;
    }
    @Override
    protected void doAction() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Implemented!");
    }
}
