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


    //private static final boolean isPutting = false;
    //private static final boolean isGetting = true;


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
<<<<<<< HEAD
        d.queue.add(new Command("trab1.GotoPosition", 2, 2, 1,isPutting));
        d.queue.add(new Command("trab1.PutGet",2,2,1, isPutting));

        d.queue.add(new Command("trab1.GotoPosition", 4, 4, 1,isGetting));
        d.queue.add(new Command("trab1.PutGet",4,4,1, isGetting));
=======
        //d.queue.add(new Command("trab1.GotoPosition", 2, 2, 1,isPutting));
        //d.queue.add(new Command("trab1.PutGet",2,2,1, isPutting));
>>>>>>> 0277023033433af8c3cdd3e22ea84836de4ed85f
        //h.move_x_left();
    }
    
}
