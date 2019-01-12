package com.nice2h8u.tcpclientserver.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start(); // вызываем run()
    }

    public void run() {
        String word;
        try {

            while (true) {
                word = in.readLine();
                if (word != null) {
                    if (word.equals("stop")) {
                        break;
                    }
                    System.out.println(word);
                    for (ServerThread vr : TcpServer.serverList) {
                        vr.send(word); // отослать принятое сообщение с
                        // привязанного клиента всем остальным включая его
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }


}
