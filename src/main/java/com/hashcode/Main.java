package com.hashcode;

import java.io.IOException;

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
        
        fileUtils.createOutputFile();
    }
}
