package com.hashcode.processus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hashcode.model.Book;
import com.hashcode.model.Library;

public class OrderLibraryProcessus {
    public List<Library> orderLibrariesByTotalScore(List<Library> allLibraries) {
        ArrayList<Library> orderedLibrairies = new ArrayList<>(allLibraries);
        Collections.sort(orderedLibrairies, new Comparator<Library>() {
            @Override
            public int compare(final Library lib1, final Library lib2) {
                return getScoreForLib(lib1) - getScoreForLib(lib2);
            }
        });
        return orderedLibrairies;
    }
    
    private int getScoreForLib(Library library) {
        return library.getAllBooks().stream().map(Book::getScore).mapToInt(i -> i).sum();
    }
}
