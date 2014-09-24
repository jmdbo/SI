#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <signal.h>
#include <winsock.h>
#include "wininterface.h"

WinInterface::WinInterface(int use_kit,void (*sighandle)(int))
{
    socket_on = 0;
    useKit = use_kit;

    signal(SIGABRT, sighandle);
    signal(SIGFPE, sighandle);
    signal(SIGILL, sighandle);
    signal(SIGINT, sighandle);
    signal(SIGSEGV, sighandle);
    signal(SIGTERM, sighandle);
    signal(SIGBREAK, sighandle);
}

WinInterface::WinInterface(){
    socket_on = 0;
    useKit = 0;
}

WinInterface::~WinInterface(){
    closeChannels();
}


int WinInterface::createDIChannel(UINT32 port){
    if(!useKit)
       return simCreateDIChannel(port);
    return 0;
}

int WinInterface::createDOChannel(UINT32 port){
    if(!useKit)
       return simCreateDOChannel(port);
    return 0;
}

UINT8 WinInterface::readDigitalU8(UINT32 port){
    if(!useKit)
       return simReadDigitalU8(port);
    return 0;
}

void WinInterface::writeDigitalU8(UINT32 port, UINT8 data){
    if(!useKit)
        simWriteDigitalU8(port,data);
}

int WinInterface::closeChannels(){
    if(!useKit)
       return simCloseChannels();
    return 0;
}

void WinInterface::signalHandler(int signum){
    if(signum){};

    closeConnection();

    exit(0);
}

void WinInterface::closeConnection()
{
    socket_on = 0;
    if(sockfd){
        closesocket(sockfd);
    }
    WSACleanup();
}

void WinInterface::startConnection()
{


    serv_addr.sin_family			= AF_INET;
    serv_addr.sin_addr.s_addr	= inet_addr(SERVER_HOST);
    serv_addr.sin_port			= htons(SERVER_PORT);
    if((WSAErr=WSAStartup(0x101,(LPWSADATA)&WSAData))!=0)
    {
        printf("\nKit not found (%d). Turn it on...",WSAErr);
        exit(-1);
    }
    sockfd=socket( AF_INET , SOCK_STREAM , 0 );
    if( (int)sockfd < 0 )
    {
        printf("\nKit not found (%d). Turn it on...",WSAErr);
        exit(-1);
    }
    if( connect( sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0 )
    {
        printf("\nKit not found (%d). Turn it on...",WSAErr);
        exit(-1);
    }
    else
    {
        printf("\nKit has been found :-) ...");
        socket_on = 1;
    }

}

void WinInterface::sendMessage(char *message){
    if(!socket_on)
        startConnection();

    Sleep(1);
    if( send(sockfd, message, strlen( message), 0) == SOCKET_ERROR){
        WSAErr = WSAGetLastError();
        printf("\n Error sending data (%d)...", WSAErr);
    }

}

void WinInterface::receiveMessage(char *message){
    int n;
    char buf[100];

    if(!socket_on)
        startConnection();

    Sleep(1);
    if( (n = recv( sockfd, buf, sizeof(buf),0)) > 0){
        buf[n] = '\0';
        strcpy(message,buf);
    } else if (n == SOCKET_ERROR) {
        WSAErr = WSAGetLastError();
        printf("\n Error receiving data (%d)...",WSAErr);
    }
}


/* *********************************
 * ***        Simulator Code     ***
 * *********************************
 */


int WinInterface::simCreateDIChannel(UINT32 port){
    char buff[100];
    sprintf(buff,"%s %d %d","create_DI_channel", port, 0);
    sendMessage(buff);
    receiveMessage(buff); //receive 'done'
    return(1);
}

int WinInterface::simCreateDOChannel(UINT32 port){
    char buff[100];
    sprintf(buff,"%s %d %d","create_DO_channel", port, 0);
    sendMessage(buff);
    receiveMessage(buff); //receive 'done'
    return(1);
}

UINT8 WinInterface::simReadDigitalU8(UINT32 port){
    char buff[100];
    char lixo[100];
    UINT32 value;
    sprintf(buff,"%s %02X %02X","ReadDigitalU8", port, 0);

    sendMessage(buff);

    receiveMessage(buff);


    sscanf(buff,"%s %02X %02X",lixo, &port, &value);
    return((UINT8) value);
}

void WinInterface::simWriteDigitalU8(UINT32 port, UINT8 data){
    char buff[100];
    sprintf(buff,"%s %02X %02X","WriteDigitalU8", port, data);
    sendMessage(buff);

    receiveMessage(buff); //receive 'done'
}

int WinInterface::simCloseChannels(){
    closeConnection();
    return(1);
}

/* *********************************
 * ***        NiDAQ-mx  Code     ***
 * *********************************
 */
