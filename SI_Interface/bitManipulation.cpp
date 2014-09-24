#include <iostream>
#include <stdio.h>
#include <conio.h>
#include <interface.h>
#include "bitManipulation.h"


bool getBit(uInt8 variable, uInt8 n_bit)
{
	int result = variable & (1<<n_bit);
	return( result!=0  ); // a value not zero is TRUE
}

void setBit(uInt8  &variable, int bit, bool value)
{
	uInt8  mask_on = (uInt8)(1<<bit);
	uInt8  mask_off = (uInt8)0xff - mask_on; 
	if(value)  variable |=    mask_on;
	else         variable &=    mask_off;
}

void write_port(int porto, uInt8 value)
	{
		uInt8 aa =0;		 
		WriteDigitalU8(porto, value);
	}

uInt8 read_port(int porto)
{
	uInt8 aa =0;	 
	aa = ReadDigitalU8(porto);
	return(aa);
}

