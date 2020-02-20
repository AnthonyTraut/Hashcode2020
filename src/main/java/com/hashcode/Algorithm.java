package com.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hashcode.model.AlgoInfo;
import com.hashcode.model.Book;
import com.hashcode.model.Library;

public class Algorithm {
    
    final AlgoInfo algoInfo;
    final List<Book> books;
    final List<Library> libraries;
    
    int nbLibrariesToProcess = 0;
    
    public Algorithm(final AlgoInfo algoInfo, final List<Book> books, final List<Library> libraries) {
        this.algoInfo = algoInfo;
        this.books = books;
        this.libraries = libraries;
    }
    
    public OutputFile process() {
        nbLibrariesToProcess = algoInfo.getNbLib();
        
        final List<Section> descriptions = new ArrayList<>();
        for (Library library : libraries) {
            final int id = library.getId();
            final int nbBooksToScan = library.getNbBooks();
            final List<Integer> information = Arrays.asList(id, nbBooksToScan);
            
            final List<Integer> ids = new ArrayList<>();
            for (Book book : library.getAllBooks()) {
                ids.add(book.getId());
            }
            
            descriptions.add(new Section(information, ids));
        }
        
        return new OutputFile(nbLibrariesToProcess, descriptions);
    }
}
