package com.nice2h8u.tcpclientserver.helpClasses;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import com.nice2h8u.tcpclientserver.entity.Message;
import com.nice2h8u.tcpclientserver.service.DictionaryService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseGenerator {


    @Autowired
    private DictionaryService dictionaryService;

    private ObjectMapper mapper;
    private List<Dictionary> dictionaries;

    ResponseGenerator() {
        mapper = new ObjectMapper();
        dictionaries = new ArrayList<>();
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
    }


    public String receiveAndSend(String json) throws IOException {

        return generateMessageToSend(deserializeJson(json));
    }

    public String generateMessageToSend(Message incomingMessage) throws IOException {
        dictionaries = new ArrayList<>();
        Dictionary dictionary;
        Message result = null;

        switch (incomingMessage.getTypeOfMessage()) {
            case "getDescription":

                dictionary = dictionaryService.getDescriptionByWord(incomingMessage.getDictionaries().get(0).getWord());
                dictionaries.add(dictionary);
                result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
                break;
            case "addNewDictionary":

                dictionary = incomingMessage.getDictionaries().get(0);
                dictionaryService.addNewDictionary(dictionary);

                result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);

                break;
            case "changeDictionary":
                dictionary = incomingMessage.getDictionaries().get(0);

                dictionaryService.changeDictionary(dictionary.getWord(), incomingMessage.getDictionaries().get(1).getWord(),
                        incomingMessage.getDictionaries().get(0).getDescription());
                result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
                break;
            case "deleteWord":
                dictionary = incomingMessage.getDictionaries().get(0);

                dictionaryService.deleteWord(dictionary.getWord());
                result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
            case "getWordsByMask":
                dictionary = incomingMessage.getDictionaries().get(0);

                 dictionaries = dictionaryService.getWordsByMask(dictionary.getWord());
                 if (dictionaries.size()!=0)
                     result = new Message(incomingMessage.getTypeOfMessage(),dictionaries);

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
