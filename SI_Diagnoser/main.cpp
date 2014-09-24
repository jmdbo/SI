#include <iostream>
#include "warehouse.h"


using namespace std;

void sighandle(int num){
    exit(0);
}

int main()
{
    WareHouse warehouse = WareHouse(false);
    warehouse.test();


    cout << "Hello World!" << endl;
    return 0;
}

