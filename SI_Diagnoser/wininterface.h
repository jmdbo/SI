#ifndef WININTERFACE_H
#define WININTERFACE_H

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <signal.h>
#include <winsock.h>


#define SERVER_PORT 9999
#define SERVER_HOST "127.0.0.1"
#define PACKET_MAX_SIZE 1024


#define ERROR_SOCKET_CREATE -1
#define ERROR_SOCKET_CREATE_MSG "Error Creating Socket"
#define ERROR_SOCKET_CLOSE -3
#define ERROR_SOCKET_CLOSE_MSG "Error while Closing Socket"
#define ERROR_CONNECT -2
#define ERROR_CONNECT_MSG "Server not Found!"
#define ERROR_SOCKET_SEND -1
#define ERROR_SOCKET_SEND_MSG "Error while sending a Packet"
#define ERROR_SOCKET_RECEIVE -1
#define ERROR_SOCKET_RECEIVE_MSG "Error while receiving a Packet"


#define SUCCESS_CONNECT_MSG "Server Has been Found!"
#define SUCCESS_SOCKET_CLOSE_MSG "Socket has been Closed!"

class WinInterface
{
public:
    WinInterface(int use_kit,void (*)(int));
    WinInterface();

    ~WinInterface();

    // General Code
    int createDIChannel(UINT32 port);
    int createDOChannel(UINT32 port);
    UINT8 readDigitalU8(UINT32 port);
    void writeDigitalU8(UINT32 port, UINT8 data);
    int closeChannels();

private:

    int socket_on;
    int useKit;
    SOCKET 	sockfd;
    struct 	sockaddr_in	serv_addr;
    WSADATA	WSAData;
    int		WSAErr;

    void signalHandler(int signum);

    void closeConnection();
    void startConnection();
    void sendMessage(char * message);
    void receiveMessage(char * message);

    // Simulation Code
    int simCreateDIChannel(UINT32 port);
    int simCreateDOChannel(UINT32 port);
    UINT8 simReadDigitalU8(UINT32 port);
    void simWriteDigitalU8(UINT32 port,UINT8 data);
    int simCloseChannels();

    // NiDaQ-mx Code

};

#endif // WININTERFACE_H
