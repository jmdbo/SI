package trab1;

/**
 * Created by João on 03/10/2014.
 */
public class Command {
    public String order;

    public int x;
    public int z;
    public boolean isGetting;
    public boolean isTray;

    //constructor GotoXZ
    public Command(String _order, int _x, int _z, boolean _isGetting){
        this.isGetting = _isGetting;
        this.order = _order;
        this.x = _x;
        this.z = _z;
    }

    //constructor PutGet
    public Command(String _order, boolean _tray, boolean _isGetting){
        this.isGetting = _isGetting;
        this.order = _order;
        this.isTray = _tray;
    }

    public Command(String _order){
        this.order = _order;
    }
}
