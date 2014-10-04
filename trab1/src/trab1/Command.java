package trab1;

/**
 * Created by João on 03/10/2014.
 */
public class Command {
    public String order;

    public int x;
    public int z;
    public int tray;
    public boolean isGetting;

    public Command(String _order, int _x, int _z, int _tray, boolean _isgetting){
        this.isGetting = isGetting;
        this.order = _order;
        this.x = _x;
        this.z = _z;
        this.tray = _tray;
    }

    public Command(String _order){
        this.order = _order;
    }
}
