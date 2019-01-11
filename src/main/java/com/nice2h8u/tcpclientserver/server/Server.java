package com.nice2h8u.tcpclientserver.server;


public interface Server {

    void setPort(Integer port);
    void start(Integer port);
    void stop();

}
