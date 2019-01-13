package com.nice2h8u.tcpclientserver.server;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.Socket;

//@Component
public class ServerThread extends Thread {


    //ResponseGenerator responseGenerator;
    private ObjectMapper mapper; //jasckson
    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private PrintWriter out; // поток записи в сокет


    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        //responseGenerator = new ResponseGenerator();
        mapper = new ObjectMapper();
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        start(); //  run()
    }

    public void run() {
        String word;
        try {

            while (true) {
                word = in.readLine();

                if (word != null) {




                    for (ServerThread vr : TcpServer.serverList) {
                        //vr.send(responseGenerator.receiveAndSend(word)); // отослать принятое сообщение с
                        // привязанного клиента всем остальным включая его
                    }
                }
            }
        } catch (IOException e) {
        }

    }



    private void send(String message) {




            System.out.println(message);


            out.write(message + "\n");
            out.flush();


    }





}
