package com.hashcode.model;

import lombok.Getter;

import java.util.List;

@Getter
public class LibraryProcess {

    private final int libraryId;
    private final List<Book> booksScanned;

    public LibraryProcess(int libraryId, List<Book> booksScanned) {
        this.libraryId = libraryId;
        this.booksScanned = booksScanned;
    }
}
