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

    native public void setBit (int port, int bit);
    native public void resetBit(int port, int bit);
    native public boolean readBit(int port, int bit);
    /***************************************************************************/
    native public int readPort (int port);
    native public void writePort (int port, int value);
    
}
