package com.nice2h8u.tcpclientserver.repository;

import com.nice2h8u.tcpclientserver.entity.Dictionary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends CrudRepository <Dictionary,Long> {

    Dictionary findByWord(String word);

}
