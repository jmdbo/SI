#ifndef UTILITIES_H
#define	UTILITIES_H




bool getBit(uInt8 variable, uInt8 n_bit);
void setBit(uInt8  &variable, int bit, bool value);
void write_port(int porto, uInt8 value);
uInt8 read_port(int porto);



#endif	/* UTILITIES_H */