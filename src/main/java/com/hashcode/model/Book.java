package com.hashcode.model;

import lombok.Getter;

@Getter
public class Book {

    private int id;
    private int score;

    private int nbOccurrences = 1;
    private boolean scanned = false;

    public Book(final int id, final int score) {
        this.id = id;
        this.score = score;
    }

    public void addOccurrence() {
        nbOccurrences++;
    }

    public void scan() {
        scanned = true;
    }
}
