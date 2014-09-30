/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1;

/**
 *
 * @author João Barata Oliveira
 */
public class Hardware {

    public Hardware(){
        initialize_kit();
    }

    static{
        //C:\dev\SI\hardware\x64\Debug\hardware.dll
        System.load("C:\\dev\\SI\\hardware\\x64\\Debug\\hardware.dll");
    }

    native public void initialize_kit();

    native public void create_di(int port);

    native public void create_do(int port);

    native public int read_port(int port);

    native public boolean readBit(int port, int pos);
    
    native public void move_x_left();
    
    native public void move_x_right();    
    
    native public void move_y_in();
    
    native public void move_y_out();
    
    native public void move_z_up();
    
    native public void move_z_down();
        
    native public void stop_x();
    
    native public void stop_y();
    
    native public void stop_z();
    
    native public void stop_emergency();    
    
}
