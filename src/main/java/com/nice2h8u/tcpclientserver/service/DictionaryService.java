package com.nice2h8u.tcpclientserver.service;

import com.nice2h8u.tcpclientserver.entity.Dictionary;

public interface DictionaryService {
    public String getDescriptionByWord(String word);
    public Iterable <Dictionary> getWordsByMask(String mask);
    public void addNewDictionary(Dictionary dictionary);
    public void changeDictionary(String word,String newWord, String newDescription);
    public void deleteWord(String word);
}
