package trab1;

/**
 * Created by João on 03/10/2014.
 */
public class Command {
    public String order;

    public int x;
    public int y;
    public boolean isGetting;

    public Command(String _order, int _x, int _y, boolean _isgetting){
        this.isGetting = isGetting;
        this.order = _order;
        this.x = _x;
        this.y = _y;
    }

    public Command(String _order){
        this.order = _order;
    }
}
