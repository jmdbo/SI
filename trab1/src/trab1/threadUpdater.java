package trab1;

/**
 * Created by Joao on 30/09/2014.
 */
public class threadUpdater implements Runnable {

    private int[] ports;
    private Hardware h;

    threadUpdater(Hardware ha){
        super();
        ports = new int[6];
        h = ha;
    }

    public int sensorport( int i){return ( ports[i]);}

    @Override
    public void run() {
        ports[0] =  h.read_port(0);
        ports[1] =  h.read_port(1);
        ports[2] =  h.read_port(2);
        ports[3] =  h.read_port(3);
        ports[4] =  h.read_port(4);
        ports[5] =  h.read_port(5);
        try {
            Thread.sleep(100);
        }catch (Exception e ){
            System.out.println("hello");
        }
    }
}
