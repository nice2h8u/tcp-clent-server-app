package com.nice2h8u.tcpclientserver.server;


import com.nice2h8u.tcpclientserver.helpClasses.ResponseGenerator;
import com.nice2h8u.tcpclientserver.server.Server;
import com.nice2h8u.tcpclientserver.service.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TcpServerAutoStarterApplicationListener implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private Server server;

    @Autowired
    ResponseGenerator responseGenerator;
    @Autowired
    DictionaryServiceImpl dictionaryService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


        server.start(20205);



    }
}
