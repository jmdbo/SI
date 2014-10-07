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
        d.queue.add(new Command("trab1.GotoPosition", 2, 2, 1,false));
        d.queue.add(new Command("trab1.PutGet",2,2,1, false));
        //h.move_x_left();
    }
    
}
