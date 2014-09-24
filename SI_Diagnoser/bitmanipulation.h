/* 
 * File:   BitManipulation.h
 * Author: David dos Santos Fernandes
 *
 * Created on October 20, 2013, 2:44 AM
 */


#ifndef UTILITIES_H
#define	UTILITIES_H

class BitManipulation {
public:
    BitManipulation();
    BitManipulation(const BitManipulation& orig);
    virtual ~BitManipulation();

    int Power(int base, int exp);
    void PrintByte(short int _Byte);
    int getBit(int _Byte, int pos);
    int setBit(int _Byte, int pos, int value);


private:

};

#endif	/* UTILITIES_H */

