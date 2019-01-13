package com.nice2h8u.tcpclientserver.service;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import com.nice2h8u.tcpclientserver.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public String getDescriptionByWord(String word) {

        String description = dictionaryRepository.findByWord(word).getDescription();
        return description;
    }
@Override
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
