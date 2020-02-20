package com.hashcode.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Book {
    private static int cpt = 0;
    private int id;
    private int score;
    
    public Book(final int score) {
        this.id = cpt;
        this.score = score;
        ++cpt;
    }
}
