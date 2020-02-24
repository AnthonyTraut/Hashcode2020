package com.hashcode.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Library {

    private final int id;
    private final int nbBooks;
    private final int signedUpDays;
    private final int booksShippedPerDay;
    private final List<Book> books;

    public Library(final int id, final int nbBooks, final int signedUpDays, final int booksShippedPerDay) {
        this.id = id;
        this.nbBooks = nbBooks;
        this.signedUpDays = signedUpDays;
        this.booksShippedPerDay = booksShippedPerDay;

        books = new ArrayList<>();
    }

    public void addBook(final Book book) {
        books.add(book);
    }
}
