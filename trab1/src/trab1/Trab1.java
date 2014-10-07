/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1;

/**
 *
 * @author João
 */


public class Trab1 {


    private static final boolean ISPUTTING = false;
    private static final boolean ISGETTING = true;
    private static final boolean ISTRAY = true;
    private static final boolean ISCELL = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Ola....");
        Hardware h = new Hardware();
        h.initialize_kit();
        BufferData bufferData = new BufferData(h);
        Dispatcher d = new Dispatcher(bufferData);
        new Thread(d).start();

        /*
        d.queue.add(new Command("trab1.GotoPosition", 4, 4, 1,isGetting));
        d.queue.add(new Command("trab1.PutGet",4,4,1, isGetting));

        d.queue.add(new Command("trab1.GotoPosition", 2, 2, 1,isPutting));
        d.queue.add(new Command("trab1.PutGet",2,2,1, isPutting));
*/

        //(String _order, int _x, int _z, int _tray, boolean _isGetting)
        d.queue.add(new Command("trab1.GotoPosition", 0, 0, ISGETTING));
        d.queue.add(new Command("trab1.PutGet", ISTRAY, ISGETTING));


        d.queue.add(new Command("trab1.GotoPosition",4, 4, ISPUTTING));
        d.queue.add(new Command("trab1.PutGet", ISCELL, ISPUTTING));


    }
    
}
