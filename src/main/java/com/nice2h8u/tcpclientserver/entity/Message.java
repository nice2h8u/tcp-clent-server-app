package com.nice2h8u.tcpclientserver.entity;

import java.util.ArrayList;
import java.util.List;

public class Message {
    String typeOfMessage;


    List<Dictionary> dictionaries = new ArrayList<>();

    public Message() {
    }

    public Message(String typeOfMessage, List<Dictionary> dictionaries) {
        this.typeOfMessage = typeOfMessage;
        this.dictionaries = dictionaries;
    }

    public String getTypeOfMessage() {
        return typeOfMessage;
    }

    public void setTypeOfMessage(String typeOfMessage) {
        this.typeOfMessage = typeOfMessage;
    }

    public List<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<Dictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }
}
