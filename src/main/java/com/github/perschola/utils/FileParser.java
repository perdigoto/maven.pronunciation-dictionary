package com.github.perschola.utils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileParser {
    private final String fileContent;
    public FileParser(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        StringBuilder result = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new Error(e);
        }
        fileContent = result.toString();
    }

    public List<String> toListOfLines() {
        return new ArrayList<>(Arrays.asList(fileContent.split("\n")));
    }

    public String getFileContent() {
        return fileContent;
    }
}
