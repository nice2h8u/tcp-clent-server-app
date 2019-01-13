package com.nice2h8u.tcpclientserver.server;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток записи в сокет
    private JSONObject json;

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
                word =in.readLine();
                if (word!=null)
                    json = new JSONObject(word);
                else json = null;


                    if (json != null) {

                        if (json.get("word").equals("stop")) {
                            break;
                        }
                        System.out.println(json.get("word"));
                        for (ServerThread vr : TcpServer.serverList) {
                            vr.send(json.toString()); // отослать принятое сообщение с
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
        } catch (IOException ignored) {
        }
    }

    private Dictionary deserialiazing(JSONObject json){
        Long id = Long.parseLong( json.get("id").toString());
        String word = json.get("word").toString();
        String description = json.get("description").toString();

        return new Dictionary(id,word,description);
    }


}
