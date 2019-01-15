package com.nice2h8u.tcpclientserver.server;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

@Component
public class TcpServer implements Server {
    public static LinkedList<ServerThread> serverList = new LinkedList<>();

    public void start(int port) {

        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {


                while (true) {



                    serverList.add(new ServerThread(serverSocket.accept()));
                }

            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }

    }).start();
    }


    public void stop() {

    }
}