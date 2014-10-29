package trab1;
import CLIPSJNI.Environment;

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

    static public void main(String[] args){

        Environment environment = new Environment();

        environment.load("rule.CLP");
        environment.reset();
        System.out.println("OLA" + environment.eval("(get-defrule-list)").toString());
    }
}
