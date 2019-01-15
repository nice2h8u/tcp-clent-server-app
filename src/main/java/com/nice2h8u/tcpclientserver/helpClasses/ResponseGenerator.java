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
        Dictionary ifExistDictionary;
        Message result = null;

        switch (incomingMessage.getTypeOfMessage()) {


            case "getAllDictionaries":
                dictionaries = dictionaryService.getAllDictionaries();
                if (dictionaries.size() != 0)
                    result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
                else result = new Message("Error. No words in server db", dictionaries);
                break;

            case "getDescription":

                dictionary = dictionaryService.getDescriptionByWord(incomingMessage.getDictionaries().get(0).getWord());
                //if word exist
                if (dictionary != null) {
                    dictionaries.add(dictionary);
                    result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
                } else result = new Message("No such word in db", dictionaries);
                break;

            case "addNewDictionary":

                dictionary = incomingMessage.getDictionaries().get(0);

                ifExistDictionary = dictionaryService.getDescriptionByWord(dictionary.getWord());
                //if word exist
                if (ifExistDictionary == null) {
                    dictionaryService.addNewDictionary(dictionary);

                    result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
                } else
                    result = new Message("This word is already exist in db", dictionaries);
                break;

            case "changeDictionary":
                dictionary = incomingMessage.getDictionaries().get(0);

                ifExistDictionary = dictionaryService.getDescriptionByWord(dictionary.getWord());

                if (ifExistDictionary != null &&
                   dictionaryService.getDescriptionByWord(incomingMessage.getDictionaries().get(1).getWord())==null ) {

                    dictionaryService.changeDictionary(dictionary.getWord(), incomingMessage.getDictionaries().get(1).getWord(),
                            incomingMessage.getDictionaries().get(1).getDescription());
                    result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);

                } else result = new Message("No such word in db", dictionaries);

                break;

            case "deleteDictionary":

                dictionary = incomingMessage.getDictionaries().get(0);
                ifExistDictionary = dictionaryService.getDescriptionByWord(dictionary.getWord());

                if (ifExistDictionary != null) {

                    dictionaryService.deleteWord(dictionary.getWord());
                    result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
                } else result = new Message("No such word in db", dictionaries);

                break;
            case "getWordsByMask":
                dictionary = incomingMessage.getDictionaries().get(0);

                dictionaries = dictionaryService.getWordsByMask(dictionary.getWord());
                if (dictionaries.size() != 0)
                    result = new Message(incomingMessage.getTypeOfMessage(), dictionaries);
                else result = new Message("Error. No words in server db", dictionaries);
                break;

            case "exit":
                return "exit";




        }

        return mapper.writeValueAsString(result);
    }


    public Message deserializeJson(String json) {
        Message message = null;
        try {
            message = mapper.readValue(json, Message.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }


}
