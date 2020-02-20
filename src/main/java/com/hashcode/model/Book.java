package com.hashcode.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Book {
    private int id;
    private int score;
    
    public Book(final int id, final int score) {
        this.id = id;
        this.score = score;
    }
}
