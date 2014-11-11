package si.api;

/**
 * Created by dd.fernandes on 23/09/2014.
 */
public class hardware {

    public hardware() {

        System.loadLibrary("warehouse");

        createDO(0);
        createDO(1);
        createDO(2);
        createDO(3);

        createDI(4);
        createDI(5);
    }

    native public void createDI(int port);

    native public void createDO(int port);

    native public void writePort(int port, int data);

    native public int readPort(int port);


}
