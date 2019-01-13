package com.nice2h8u.tcpclientserver.server;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import com.nice2h8u.tcpclientserver.entity.Message;
import com.nice2h8u.tcpclientserver.service.DictionaryService;
import com.nice2h8u.tcpclientserver.service.DictionaryServiceImpl;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Component
public class ResponseGenerator {



    @Autowired
    private DictionaryServiceImpl dictionaryService;

    private ObjectMapper mapper;
    private List<Dictionary> dictionaries;

    ResponseGenerator() {
        mapper = new ObjectMapper();

        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public String test(String temp){
        return dictionaryService.getDescriptionByWord(temp).getWord();
    }
    public String receiveAndSend(String json) throws IOException {
       return generateMessageToSend(deserializeJson(json));
    }

    public String generateMessageToSend(Message incomingMessage) throws IOException {
        Dictionary dictionary = new Dictionary();
        Message result = null;
        System.out.println("typeb"+incomingMessage.getTypeOfMessage());
        switch (incomingMessage.getTypeOfMessage()) {
            case "getDescription":
                //incomingMessage.getDictionaries().get(0).getWord()
                dictionary = dictionaryService.getDescriptionByWord("Java");
                dictionaries.add(dictionary);
                result = new Message("success", dictionaries);
                break;

        }

        return mapper.writeValueAsString(result);
    }


    public Message deserializeJson(String json) {
        Message message = null;
        try {
            message = mapper.readValue(json, Message.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }


}
