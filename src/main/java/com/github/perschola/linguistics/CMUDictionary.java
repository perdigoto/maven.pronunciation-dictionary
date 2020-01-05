package com.github.perschola.linguistics;

import com.github.perschola.utils.FileParser;

import java.util.*;
import java.util.stream.Collectors;

public enum CMUDictionary {
    INSTANCE;

    private final List<Word> wordList;

    CMUDictionary() {
        this("cmudict.0.7a.txt");
    }

    CMUDictionary(String filePath) {
        FileParser fileParser = new FileParser(filePath);
        this.wordList = new ArrayList<>();
        for (String line : fileParser
                .toListOfLines()
                .stream()
                .filter(line -> !line.startsWith(";;;"))
                .collect(Collectors.toList())) {
            String[] wordAndPhoneme = line.split(" {2}");
            String wordAsString = wordAndPhoneme[0];
            String phonemes = wordAndPhoneme[1];
            String[] phonemeArray = phonemes.split(" ");
            Word word = new Word(wordAsString, phonemeArray);
            wordList.add(word);
        }
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public Phonetic getPhonetic(String word) {
        return getWord(word).getPhonetic();
    }

    public List<List<String>> getPhonemesRemoved(String word) {
        return getWord(word).getPhonemesRemoved();
    }

    public List<List<String>> getPhonemesAdded(String word) {
        return getWord(word).getPhonemesAdded();
    }

    private Word getWord(String wordSpelling) {
        return wordList
                .stream()
                .filter(word -> word
                        .getSpelling()
                        .toUpperCase()
                        .equals(wordSpelling.toUpperCase()))
                .findFirst()
                .get();
    }

    public List<String> getPronunciation(String string) {
        return Arrays.asList(getPhonetic(string).getPhonemes());
    }

    public List<String> getWordsSimilarPronunciation(String string) {
        return null;
    }

    public List<String> getWordsIdenticalPronunciation(String string) {
        return null;
    }
}
