package com.hashcode;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        final FileUtils fileUtils = new FileUtils(args[0]);
        fileUtils.getInputFile();
        fileUtils.createOutputFile();
    
        try {
            fileUtils.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
