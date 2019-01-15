package com.nice2h8u.tcpclientserver.server;

import com.nice2h8u.tcpclientserver.helpClasses.BeanUtil;
import com.nice2h8u.tcpclientserver.helpClasses.ResponseGenerator;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.Socket;


public class ServerThread extends Thread {


    private ResponseGenerator responseGenerator;

    private ObjectMapper mapper; //jasckson
    private Socket socket;



    public ServerThread(Socket socket)  {
        this.socket = socket;
        System.out.println("New client connected");
        responseGenerator = BeanUtil.getBean(ResponseGenerator.class);
        mapper = new ObjectMapper();
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);


        start(); //  run()
    }

    public void run() {
        String word;
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {

            while (true) {
                word = in.readLine();

                if (word != null) {
                    //
                    System.out.println("Server get a request");
                            String sendingJson = responseGenerator.receiveAndSend(word);
                            if (!sendingJson.equals("exit"))
                                send(sendingJson,out);
                            else break;
                    }
                }


            System.out.println("Client disconnected");
        }  catch (IOException e) {
        }


        finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }



    private void send(String message, PrintWriter out) {






        System.out.println("Server return a response");
            out.write(message + "\n");
            out.flush();


    }





}
