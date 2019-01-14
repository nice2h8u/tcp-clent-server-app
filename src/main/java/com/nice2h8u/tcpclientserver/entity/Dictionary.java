package com.nice2h8u.tcpclientserver.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Dictionary {

    @Id
    private Long id;

    private String word;

    //large object
    @Lob //@Type(type = "org.hibernate.type.TextType")
    private String description;

    public Dictionary() {
    }

    public Dictionary(Long id, String word, String description) {
        this.id = id;
        this.word = word;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
