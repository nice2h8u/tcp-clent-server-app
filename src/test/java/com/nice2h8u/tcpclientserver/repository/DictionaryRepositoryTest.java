package com.nice2h8u.tcpclientserver.repository;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DictionaryRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DictionaryRepository dictionaryRepository;



    @Test
    public void findByWord() {
        String word = "Sing";
        Dictionary tempDictionary = new Dictionary(5L,word,"words+music");
        entityManager.persist(tempDictionary);
        entityManager.flush();

        Dictionary dictionary = dictionaryRepository.findByWord(word);

        assertEquals(dictionary.getDescription(),
                tempDictionary.getDescription());


    }
}