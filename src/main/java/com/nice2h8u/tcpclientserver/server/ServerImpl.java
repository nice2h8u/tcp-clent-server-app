package com.nice2h8u.tcpclientserver.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@Component
public class ServerImpl implements Server {

    private ServerSocket serverSocket;
    //all threads can change this value
    private volatile boolean isStop;





    public void start(Integer port){

        setPort(port);

        new Thread(() -> {
            while (!isStop) {
                try {
                    Socket socket = serverSocket.accept();
                    if (socket.isConnected()) {
                        //TcpConnection tcpConnection = new TcpConnection(socket);
                        OneConnection connection = new OneConnection(socket);
                        connection.start();

                        System.out.println("New connection " + connection.getAddress().getCanonicalHostName());


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();

         }

    public void stop() {
        isStop=true;
    }


    public void setPort(Integer port) {
        try {
            if (port == null) {
                log.info("Error! Port has null value! Server will use default port 22222");
                port = 22222;
            }
            serverSocket = new ServerSocket(port);
            log.info("Server start at port " + port);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("May be port " + port + " is busy ><.");
        }

}}
