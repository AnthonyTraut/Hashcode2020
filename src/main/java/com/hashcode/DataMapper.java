package com.hashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataMapper {
    public List<Book> toBooks(List<Integer> scores) {
        List<Book> res = new ArrayList<>();
        for (int i = 0; i < scores.size(); ++i) {
            res.add(toBook(i, scores.get(i)));
        }
        return res;
    }
    
    private Book toBook (Integer id, Integer score) {
        return new Book(id, score);
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
