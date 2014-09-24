/* 
 * File:   BitManipulation.cpp
 * Author: David dos Santos Fernandes
 * 
 * Created on October 20, 2013, 2:44 AM
 */


#include <iostream>
#include "BitManipulation.h"

BitManipulation::BitManipulation() {
}

BitManipulation::~BitManipulation() {
}

int BitManipulation::Power(int base, int exp) {
    int ret = 1;
    for (int i = 0; i < exp; i++) {
        ret *= base;
    }
    return ret;
}

void BitManipulation::PrintByte(short int _Byte) {
    int mask, val;
    for (int i = 7; i >= 0; i--) {
        mask = this->Power(2, i);
        val = (_Byte & mask) / mask;
        std::cout << val;
    }
    std::cout << std::endl;
}

int BitManipulation::getBit(int _Byte, int pos) {
    int mask;
    mask = this->Power(2, pos);
    return (_Byte & mask) ? 1 : 0;
}

int BitManipulation::setBit(int _Byte, int pos, int value) {
    int mask, val;
    mask = this->Power(2, pos);
    val = 255 - mask;
    return ( value == 1) ? (mask | _Byte) : (val & _Byte);
}
