/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorialsi;

/**
 *
 * @author João
 */
public class Hardware {
    
    static{
        //C:\dev\SI\hardware\x64\Debug\hardware.dll
        System.load("C:\\dev\\SI\\hardware\\x64\\Debug\\hardware.dll");
    }

    native public void create_di(int port);

    native public void create_do(int port);

    native public void write_port(int port, int value);

    native public int read_port(int port);

}
