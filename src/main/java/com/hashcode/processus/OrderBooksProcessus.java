package com.hashcode.processus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hashcode.model.Book;
import com.hashcode.model.Library;

public class OrderBooksProcessus {
    public List<Book> orderBooksByScore(List<Book> allBooks) {
        List<Book> copyBooks = new ArrayList<>(allBooks);
        Collections.sort(copyBooks, new Comparator<Book>() {
            @Override
            public int compare(final Book book1, final Book book2) {
                return book1.getScore() - book2.getScore();
            }
        });
        return copyBooks;
    }
}
