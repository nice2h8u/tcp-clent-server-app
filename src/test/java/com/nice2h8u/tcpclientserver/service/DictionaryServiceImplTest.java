package com.nice2h8u.tcpclientserver.service;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import com.nice2h8u.tcpclientserver.repository.DictionaryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class DictionaryServiceImplTest {

    DictionaryServiceImpl dictionaryService;

    @Mock
    DictionaryRepository dictionaryRepository;

    String result;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dictionaryService = new DictionaryServiceImpl(dictionaryRepository);
        result = new String();
    }

    @Test
    public void getDescriptionByWord() {

        Dictionary testDictionary = new Dictionary(2L,"Java","the main island of Indonesia");

        //when getDescription is calling, then return this testDictionary
        Mockito.when(dictionaryService.getDescriptionByWord("Java")).thenReturn(testDictionary);
        Dictionary dictionary = dictionaryService.getDescriptionByWord("Java");

        assertEquals(testDictionary,dictionary);



    }

    @Test
    public void getWordsByMask() {
    }

    @Test
    public void addNewDictionary() {

    }

    @Test
    public void changeDictionary() {
    }

    @Test
    public void deleteWord() {
    }
}