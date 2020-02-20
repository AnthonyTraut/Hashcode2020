package com.hashcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hashcode.model.AlgoInfo;
import com.hashcode.model.Book;
import com.hashcode.model.Library;
import com.hashcode.tool.DataMapper;

public class Main {
    
    public static void main(String[] args) {
        final FileUtils fileUtils = new FileUtils(args[0]);
        fileUtils.getInputFile();
        
        InputFile inputFile = null;
        try {
            inputFile = fileUtils.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        DataMapper dataMapper = new DataMapper();
        AlgoInfo algoInfo = new AlgoInfo(inputFile.getInformation());
        List<Book> allBooksToScan = dataMapper.toBooks(inputFile.getScores());
        List<Library> allLibraries = dataMapper.toLibraries(inputFile.getSections(), allBooksToScan);
    
        final FileProcess fileProcess = new FileProcess(inputFile);
        
        final OutputFile outputFile = new OutputFile(1, new ArrayList<>());
        fileUtils.createOutputFile();
        try {
            fileUtils.writeFile(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
