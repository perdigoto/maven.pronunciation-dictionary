package com.github.perschola.linguistics;

import java.util.Arrays;

/**
 * The intent of this class is to create a wrapper for an `array` so that separate arrays can be compared via `.equals`
 * Phonetic made of individual lexemes named phonemes.
 */
public class Phonetic implements Lexeme {
    private String[] phonemes;

    public Phonetic(String[] phonemes) {
        this.phonemes = phonemes;
    }

    public String[] getPhonemes() {
        return phonemes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phonetic phonetic = (Phonetic) o;
        return Arrays.equals(phonemes, phonetic.phonemes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(phonemes);
    }
}
