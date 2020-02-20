package com.hashcode.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hashcode.Section;
import com.hashcode.model.AlgoInfo;
import com.hashcode.model.Book;
import com.hashcode.model.Library;

public class DataMapper {
    public List<Book> toBooks(List<Integer> scores) {
        return scores.stream().map(score -> toBook(score)).collect(Collectors.toList());
    }
    
    private Book toBook (Integer score) {
        return new Book(score);
    }
    
    public List<Library> toLibraries(List<Section> sections, List<Book> allBooks) {
        return sections.stream().map(section -> toLibrary(section, allBooks)).collect(Collectors.toList());
    }
    
    private Library toLibrary(Section section, List<Book> allBooks) {
        Library library = new Library(section.getInformation().get(0), section.getInformation().get(1), section.getInformation().get(2));
        List<Book> books = section.getIds().stream().map(id -> allBooks.get(id)).collect(Collectors.toList());
        library.setAllBooks(books);
        return library;
    }
}