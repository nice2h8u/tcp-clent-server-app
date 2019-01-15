package com.nice2h8u.tcpclientserver.service;

import com.nice2h8u.tcpclientserver.entity.Dictionary;

import java.util.ArrayList;

public interface DictionaryService {

    ArrayList<Dictionary> getAllDictionaries();
    Dictionary getDescriptionByWord(String word);
     ArrayList<Dictionary> getWordsByMask(String mask) ;
     void addNewDictionary(Dictionary dictionary);
     void changeDictionary(String word, String newWord, String newDescription) ;
     void deleteWord(String word);
}
