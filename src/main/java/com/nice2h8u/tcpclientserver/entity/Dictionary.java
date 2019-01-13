package com.nice2h8u.tcpclientserver.entity;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    //large object
    @Lob //@Type(type = "org.hibernate.type.TextType")
    private String description;

    public Dictionary() {
    }

    public Dictionary(Long id, String word, String description) {
        this.word = word;
        this.description = description;
    }
}
