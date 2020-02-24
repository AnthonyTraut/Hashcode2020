package com.hashcode.mapper;

import com.hashcode.model.Book;
import com.hashcode.model.Library;
import com.hashcode.model.LibraryProcess;
import com.hashcode.utils.OutputFile;
import com.hashcode.utils.Section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DataMapper {

    public List<Book> mapBooks(List<Integer> scores) {
        final List<Book> books = new ArrayList<>();

        AtomicInteger cpt = new AtomicInteger();
        scores.forEach(score -> books.add(new Book(cpt.getAndIncrement(), score)));

        return books;
    }

    public List<Library> mapLibraries(List<Section> sections, List<Book> books) {
        final List<Library> libraries = new ArrayList<>();

        AtomicInteger cpt = new AtomicInteger();
        sections.forEach(section -> {
            final List<Integer> info = section.getInformation();
            final Library library = new Library(cpt.getAndIncrement(), info.get(0), info.get(1), info.get(2));

            final List<Integer> ids = section.getIds();
            ids.forEach(id -> {
                final Book book = getBookById(id, books);
                library.addBook(book);
                book.addOccurrence();
            });

            libraries.add(library);
        });

        return libraries;
    }

    public OutputFile mapLibrariesProcess(List<LibraryProcess> librariesProcess) {
        final List<Section> sections = new LinkedList<>();
        librariesProcess.forEach(libraryProcess -> {
            final List<Integer> information = Arrays.asList(libraryProcess.getLibraryId(), libraryProcess.getBooksScanned().size());
            final List<Integer> ids = libraryProcess.getBooksScanned().stream().map(Book::getId).collect(Collectors.toList());
            sections.add(new Section(information, ids));
        });

        return new OutputFile(librariesProcess.size(), sections);
    }

    private Book getBookById(final int id, final List<Book> books) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }
}
