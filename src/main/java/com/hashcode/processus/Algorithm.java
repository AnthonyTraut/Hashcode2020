package com.hashcode.processus;

import com.hashcode.model.Book;
import com.hashcode.model.Library;
import com.hashcode.model.LibraryProcess;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class Algorithm {
    private final List<Library> libraries;
    private int nbDays;
    private int currentDay = 0;

    private List<Library> librariesToProcess = new ArrayList<>();
    private List<LibraryProcess> librariesProcess = new ArrayList<>();

    public Algorithm(final List<Integer> info, List<Library> libraries) {
        this.nbDays = info.get(2);
        this.libraries = libraries;
    }

    public List<LibraryProcess> process() {
        librariesToProcess = new ArrayList<>(libraries);

        while (currentDay < nbDays && !librariesToProcess.isEmpty()) {
            iterate();
        }

        return librariesProcess;
    }

    private void iterate() {
        if (!librariesToProcess.isEmpty()) {
            Library nextLibrary;
            if ((nextLibrary = pickBestLibrary()) != null) {
                final List<Book> booksToScan = getBooksScannable(nextLibrary);
                final LibraryProcess libraryProcess = new LibraryProcess(nextLibrary.getId(), booksToScan);
                booksToScan.forEach(Book::scan);

                librariesProcess.add(libraryProcess);
                librariesToProcess.remove(nextLibrary);

                currentDay += nextLibrary.getSignedUpDays();
            } else {
                librariesToProcess = new ArrayList<>();
            }
        }
    }

    private Library pickBestLibrary() {
        Library bestLibrary = null;
        int maxScore = 0;
        for (Library library : librariesToProcess) {
            final int score = getScore(library);
            if (score > maxScore) {
                bestLibrary = library;
                maxScore = score;
            }
        }

        return bestLibrary;
    }

    private int getScore(Library library) {
        int scoreTotal = 0;

        final List<Book> booksScannable = getBooksScannable(library);
        for (Book bookToScan : booksScannable) {
            scoreTotal += Math.ceil(bookToScan.getScore());
        }

        return (int) Math.ceil(scoreTotal / library.getSignedUpDays());
    }

    private List<Book> getBooksScannable(Library library) {
        final int timeRemainingToScan = nbDays - currentDay - library.getSignedUpDays();

        if (timeRemainingToScan > 0) {
            List<Book> booksToScan = library.getBooks().stream().filter(book -> !book.isScanned()).sorted(Comparator.comparing(Book::getScore, Comparator.reverseOrder())).collect(Collectors.toList());

            final int nbBooksScannable = timeRemainingToScan * library.getBooksShippedPerDay();
            if (nbBooksScannable > 0 && booksToScan.size() > nbBooksScannable) {
                booksToScan = booksToScan.subList(0, nbBooksScannable);
            }

            return booksToScan;
        }

        return new ArrayList<>();
    }

    public int calculateScore() {
        AtomicInteger score = new AtomicInteger();
        librariesProcess.forEach(libraryProcess -> {
            libraryProcess.getBooksScanned().forEach(book -> score.addAndGet(book.getScore()));
        });

        return score.get();
    }

}
