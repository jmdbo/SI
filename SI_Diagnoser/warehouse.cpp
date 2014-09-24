#include "warehouse.h"


WareHouse::WareHouse(bool use_kit)
{
    hardware = WinInterface();

    hardware.createDOChannel(0);
    hardware.createDOChannel(1);
    hardware.createDOChannel(2);
    hardware.createDOChannel(3);

    hardware.createDIChannel(4);
    hardware.createDIChannel(5);
}

WareHouse::~WareHouse(){
    hardware.~WinInterface();
}

void WareHouse::test(){
    UINT8 byte = hardware.readDigitalU8(1);
    util.PrintByte(byte);


    if(util.getBit(byte,3))
        liftPlatformMiddle();

    hardware.writeDigitalU8(4,0x20);
    Sleep(1000);
    hardware.writeDigitalU8(4,0);
}

void WareHouse::liftPlatformMiddle(){
    this->hardware.writeDigitalU8(4,0x08);
    while(this->util.getBit(this->hardware.readDigitalU8(1),3)){
        Sleep(500);
        if(!this->util.getBit(this->hardware.readDigitalU8(1),2))
            this->hardware.writeDigitalU8(4,0x10);
        if(!this->util.getBit(this->hardware.readDigitalU8(1),4))
            this->hardware.writeDigitalU8(4,0x08);
    }
    this->hardware.writeDigitalU8(4,0x00);
}

