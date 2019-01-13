package com.nice2h8u.tcpclientserver;


import com.nice2h8u.tcpclientserver.server.ResponseGenerator;
import com.nice2h8u.tcpclientserver.server.Server;
import com.nice2h8u.tcpclientserver.service.DictionaryService;
import com.nice2h8u.tcpclientserver.service.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

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


        // System.out.println(dictionaryService.getDescriptionByWord("Java").getDescription());

        System.out.println(
                responseGenerator.test("Java"));


        //("{\"typeOfMessage\":\"getDescription\",\"dictionaries\":[{\"id\":5,\"word\":\"Java\",\"description\":\"circle\"}]}")


    }
}
