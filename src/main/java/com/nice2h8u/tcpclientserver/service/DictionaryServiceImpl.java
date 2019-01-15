package com.nice2h8u.tcpclientserver.service;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import com.nice2h8u.tcpclientserver.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DictionaryServiceImpl implements DictionaryService {


    DictionaryRepository dictionaryRepository;

    ArrayList<Dictionary> dictionaries;
    Dictionary dictionary;
    Pattern pattern;
    Matcher matcher;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public ArrayList<Dictionary> getAllDictionaries() {
        dictionaries = new ArrayList<>();

        dictionaryRepository.findAll().forEach(dictionaries::add);

        return dictionaries;
    }

    public Dictionary getDescriptionByWord(String word) {


        return dictionaryRepository.findByWord(word);
    }

    public ArrayList<Dictionary> getWordsByMask(String mask) {

        pattern = Pattern.compile(mask);
        dictionaries = new ArrayList<>();


        dictionaryRepository.findAll().forEach(dic -> {

            matcher = pattern.matcher(dic.getWord());

            if (matcher.find()) {
                dictionaries.add(dic);
            }


        });


        return dictionaries;
    }

    public void addNewDictionary(Dictionary dictionary) {

        dictionaries = new ArrayList<>();

        dictionaryRepository.findAll().iterator().forEachRemaining(dictionaries::add);

        dictionaryRepository.save(new Dictionary
                (dictionaries.get(dictionaries.size() - 1).getId() + 1, dictionary.getWord(), dictionary.getDescription()));
    }

    public void changeDictionary(String word, String newWord, String newDescription) {
        dictionary = dictionaryRepository.findByWord(word);
        if (!newWord.equals(""))
            dictionary.setWord(newWord);
        if (!newDescription.equals(""))
            dictionary.setDescription(newDescription);
        dictionaryRepository.save(dictionary);

    }

    public void deleteWord(String word) {
        Dictionary dictionary = dictionaryRepository.findByWord(word);
        dictionaryRepository.delete(dictionary);

    }
}
