package com.hashcode.utils;

import com.hashcode.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String SEPARATOR = " ";

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
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("input/" + filename + ".txt");
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
     * Lit une ligne
     *
     * @return
     * @throws IOException
     */
    public String readLine()
            throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * Lit le fichier
     *
     * @throws IOException
     */
    public InputFile readFile()
            throws IOException {
        final InputFile inputFile = new InputFile();

        String line = readLine();
        if (line != null) {
            final List<Integer> information = parseLine(line);

            inputFile.setInformation(information);
        }

        line = readLine();
        if (line != null) {
            final List<Integer> scores = parseLine(line);

            inputFile.setScores(scores);
        }

        while ((line = readLine()) != null && !line.isEmpty()) {
            final List<Integer> information = parseLine(line);

            line = readLine();
            final List<Integer> ids = parseLine(line);

            inputFile.getSections().add(new Section(information, ids));
        }

        bufferedReader.close();

        return inputFile;
    }

    public void writeFile(final OutputFile outputFile)
            throws IOException {
        writeLine(outputFile.getNumber().toString());

        for (Section section : outputFile.getDescriptions()) {
            writeLine(convertLine(section.getInformation()));
            writeLine(convertLine(section.getIds()));
        }

        bufferedWriter.close();
    }

    private void writeLine(final String line)
            throws IOException {
        bufferedWriter.write(line + "\n");
    }

    private String getProgramPath() {
        String currentdir = System.getProperty("user.dir");
        currentdir = currentdir.replace("\\", "/");
        return currentdir + "/";
    }

    private List<Integer> parseLine(final String line) {
        final List<Integer> parsedLine = new ArrayList<>();
        final String[] splitLine = line.split(SEPARATOR);

        for (String value : splitLine) {
            parsedLine.add(Integer.parseInt(value));
        }

        return parsedLine;
    }

    private String convertLine(final List<Integer> integers) {
        final StringBuilder builder = new StringBuilder();

        for (Integer value : integers) {
            builder.append(value);
            if (integers.indexOf(value) < integers.size() - 1) {
                builder.append(SEPARATOR);
            }
        }
        return builder.toString();
    }
}
