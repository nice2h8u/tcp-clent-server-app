package com.nice2h8u.tcpclientserver.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

@Slf4j
public class OneConnection {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;

    public OneConnection(Socket socket) {
        this.socket = socket;
        try {

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                byte buf[] = new byte[64 * 1024];
                try {
                    int count = inputStream.read(buf);
                    if (count > 0) {

                        byte[] bytes = Arrays.copyOf(buf, count);

                        String s = new String(bytes);
                        send(s.toUpperCase().getBytes());
                        System.out.println(s);


                    } else {
                        System.out.println("Disconnect " + getAddress().getCanonicalHostName());
                        socket.close();


                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();



                    break;
                }
            }
        }).start();
    }


    public void send(Object objectToSend) {
        if (objectToSend instanceof byte[]) {
            byte[] data = (byte[]) objectToSend;
            try {
                outputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void addClient(Client client){
//        client
//    }

    public InetAddress getAddress() {
        return socket.getInetAddress();
    }
}
