package com.example.relation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Words {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String word1;
    private String word2;
    private String relation;

    public Long getId() {
        return id;
    }

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }

    public String getRelation() {
        return relation;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
