package trab1;

/**
 * Created by João on 21/10/2014.
 */
public class Monitor {
    private static Monitor ourInstance = new Monitor();

    public static Monitor getInstance() {
        return ourInstance;
    }

    private Monitor() {
    }
}
