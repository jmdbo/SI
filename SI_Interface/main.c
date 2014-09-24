#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include "interface.h"


int main(void)
{
    create_DO_channel(0);
    create_DO_channel(1);
    create_DO_channel(2);
    create_DO_channel(3);

    create_DI_channel(4);
    create_DI_channel(5);

    out_port(4,0x20);
    system("PAUSE");
    out_port(4,0);

    close_channels();

    printf("Hello World!\n");
    return 0;
}

