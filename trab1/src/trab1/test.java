package trab1;

/**
 * Created by João on 30/09/2014.
 */
public class test extends Action {
    test(){
        super();
    }

    @Override
    public void run(){
        while(true) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
