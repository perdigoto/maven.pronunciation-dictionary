package com.github.perschola.linguistics;

import com.github.perschola.utils.ArrayUtils;
import com.github.perschola.utils.PowerSet;

import java.util.*;

public class Word {
    private final String wordSpelling;
    private final String[] phonemeArray;

    public Word(String wordAsString, String[] phonemeArray) {
        this.wordSpelling = wordAsString;
        this.phonemeArray = phonemeArray;
    }

    public String getSpelling() {
        return wordSpelling;
    }

    public Phonetic getPhonetic() {
        return new Phonetic(phonemeArray);
    }

    public List<List<String>> getPhonemesRemoved() {
        Set<Set<String>> powerSet =  new PowerSet<>(getPhonetic().getPhonemes()).getPowerSet();
        List<List<String>> phonemesRemoved = new ArrayList<>();
        for(Set<String> subset : powerSet) {
            String[] array = subset.toArray(new String[0]);
            phonemesRemoved.add(Arrays.asList(array));
        }
        return phonemesRemoved;
    }

    public List<List<String>> getPhonemesAdded() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word wordToCompareAgainst = (Word) o;
        Double similarity = ArrayUtils.getSimilarity(phonemeArray, wordToCompareAgainst.phonemeArray);

        Boolean sameWord = this.wordSpelling == wordToCompareAgainst.wordSpelling;
        Boolean samePronunciation = Arrays.equals(phonemeArray, wordToCompareAgainst.phonemeArray);
        Boolean similarPronunciation = similarity > .9;
        Boolean isEqual = sameWord || samePronunciation || similarPronunciation;

        return isEqual;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(wordSpelling);
        result = 31 * result + Arrays.hashCode(phonemeArray);
        return result;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + wordSpelling + '\'' +
                ", phonemeArray=" + Arrays.toString(phonemeArray) +
                '}';
    }
}
