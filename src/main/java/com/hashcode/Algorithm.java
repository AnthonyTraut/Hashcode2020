package com.hashcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hashcode.model.AlgoInfo;
import com.hashcode.model.Book;
import com.hashcode.model.Library;
import com.hashcode.processus.OrderLibraryProcessus;

public class Algorithm {
    
    final AlgoInfo algoInfo;
    final List<Book> books;
    final List<Library> libraries;
    final List<Integer> booksProcessed = new ArrayList<>();
    
    int nbLibrariesToProcess = 0;
    
    public Algorithm(final AlgoInfo algoInfo, final List<Book> books, final List<Library> libraries) {
        this.algoInfo = algoInfo;
        this.books = books;
        this.libraries = libraries;
    }
    
    public OutputFile process() {
        nbLibrariesToProcess = 0;
        
        final List<Section> descriptions = new ArrayList<>();
        OrderLibraryProcessus orderLibraryProcessus = new OrderLibraryProcessus();
        for (Library library : orderLibraryProcessus.orderLibrariesByTotalScore(libraries)) {
            final int id = library.getId();
            int nbBooksToScan = 0;
            final List<Integer> information = Arrays.asList(id, nbBooksToScan);
            
            final List<Integer> ids = new ArrayList<>();
            for (Book book : library.getAllBooks()) {
                if (!booksProcessed.contains(book.getId())) {
                    ids.add(book.getId());
                    booksProcessed.add(book.getId());
                    ++nbBooksToScan;
                }
            }
            
            descriptions.add(new Section(information, ids));
            ++nbLibrariesToProcess;
        }
        
        return new OutputFile(nbLibrariesToProcess, descriptions);
    }
}
