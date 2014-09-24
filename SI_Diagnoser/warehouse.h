#ifndef WAREHOUSE_H
#define WAREHOUSE_H

#include "bitmanipulation.h"
#include "wininterface.h"

class WareHouse
{
public:
    WareHouse(bool use_kit);
    ~WareHouse();



    void test();
    void moveToX(int pos);
    void moveToZ(int pos);
    void moveToY(int pos);

private:
    BitManipulation util;
    WinInterface hardware;

    void liftPlatformMiddle();
};

#endif // WAREHOUSE_H
