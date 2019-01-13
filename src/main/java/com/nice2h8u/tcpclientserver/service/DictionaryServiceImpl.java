package com.nice2h8u.tcpclientserver.service;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import com.nice2h8u.tcpclientserver.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl  {

    @Autowired
    DictionaryRepository dictionaryRepository;



    public Dictionary getDescriptionByWord(String word) {

        dictionaryRepository.findAll().forEach(i-> System.out.println(i.getWord()));

        return dictionaryRepository.findByWord(word);
    }

    public Iterable<Dictionary> getWordsByMask(String mask) {
        return null;
    }

    public void addNewDictionary(Dictionary dictionary) {
        dictionaryRepository.save(dictionary);
    }

    public void changeDictionary(String word, String newWord, String newDescription) {
        Dictionary dictionary = dictionaryRepository.findByWord(word);
        dictionary.setWord(newWord);
        dictionary.setDescription(newDescription);
    }

    public void deleteWord(String word) {
        Dictionary dictionary = dictionaryRepository.findByWord(word);
        dictionaryRepository.delete(dictionary);

    }
}
