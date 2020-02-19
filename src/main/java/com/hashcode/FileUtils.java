package com.hashcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {
    
    private String filename;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    
    public FileUtils(final String filename) {
        this.filename = filename;
    }
    
    /**
     * Récupère le fichier d'entrée et ouvre un reader
     */
    public void getInputFile() {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("input/" + filename + ".in");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }
    
    /**
     * Créer le fichier de sortie et ouvre un writer
     */
    public void createOutputFile() {
        File file = new File(getProgramPath() + filename + ".out");
        
        try {
            file.createNewFile();
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Lit un ligne
     * @return
     * @throws IOException
     */
    public String readLine()
            throws IOException {
        return bufferedReader.readLine();
    }
    
    /**
     * Ecrit une ligne
     * @param line
     * @throws IOException
     */
    public void writeLine(final String line)
            throws IOException {
        bufferedWriter.write(line + "\n");
    }
    
    /**
     * Traite le fichier
     * @throws IOException
     */
    public void process()
            throws IOException {
        String line;
        while ((line = readLine()) != null) {
            writeLine(line);
        }
        bufferedWriter.close();
    }
    
    private String getProgramPath() {
        String currentdir = System.getProperty("user.dir");
        currentdir = currentdir.replace("\\", "/");
        return currentdir + "/";
    }
}
