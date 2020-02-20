package com.hashcode;

import java.io.IOException;
import java.util.ArrayList;

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
