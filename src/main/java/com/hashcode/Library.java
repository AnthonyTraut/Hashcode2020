package com.hashcode;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Library {
    private int nbBooks;
    private int signedUpDays;
    private int booksShippedPerDays;
    private List<Book> allBooks;
    
    public Library(final int nbBooks, final int signedUpDays, final int booksShippedPerDays) {
        this.nbBooks = nbBooks;
        this.signedUpDays = signedUpDays;
        this.booksShippedPerDays = booksShippedPerDays;
        allBooks = new ArrayList<>(nbBooks);
    }
}
