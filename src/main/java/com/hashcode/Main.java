package com.hashcode;

import com.hashcode.mapper.DataMapper;
import com.hashcode.model.Book;
import com.hashcode.model.Library;
import com.hashcode.model.LibraryProcess;
import com.hashcode.processus.Algorithm;
import com.hashcode.utils.FileUtils;
import com.hashcode.utils.InputFile;
import com.hashcode.utils.OutputFile;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Main {

    public static void main(String[] args) {
        final List<Integer> results = new ArrayList<>();

        Arrays.asList(args).parallelStream().forEach(file -> {
            results.add(Main.process(file));
        });

        AtomicInteger scoreTotal = new AtomicInteger();
        results.forEach(scoreTotal::addAndGet);
        log.info("Score total : " + scoreTotal.get());
    }

    private static int process(final String file) {
        long startTime = System.currentTimeMillis();
        final FileUtils fileUtils = new FileUtils(file);
        fileUtils.getInputFile();

        InputFile inputFile = null;
        try {
            inputFile = fileUtils.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final DataMapper dataMapper = new DataMapper();

        List<Book> books = dataMapper.mapBooks(inputFile.getScores());
        List<Library> libraries = dataMapper.mapLibraries(inputFile.getSections(), books);

        final Algorithm algorithm = new Algorithm(inputFile.getInformation(), libraries);

        final List<LibraryProcess> libraryProcesses = algorithm.process();

        final OutputFile outputFile = dataMapper.mapLibrariesProcess(libraryProcesses);
        fileUtils.createOutputFile();
        try {
            fileUtils.writeFile(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int score = algorithm.calculateScore();
        log.info(file + " score : " + score);

        return score;
    }
}
