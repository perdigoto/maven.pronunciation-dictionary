package com.github.perschola.linguistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public enum CMUDictionaryDecision {
    GET_PRONUNCIATION((String string) -> Collections.singletonList(CMUDictionary.INSTANCE
            .getPronunciation(string))),

    GET_SIMILAR_WORDS((string) -> Collections.singletonList(CMUDictionary.INSTANCE
            .getWordsSimilarPronunciation(string))),

    GET_IDENTICAL_PRONUNCIATION((string) -> Collections.singletonList(CMUDictionary.INSTANCE
            .getWordsIdenticalPronunciation(string))),

    GET_SIMILAR_WORDS_WITH_SUB_PHONEMES(CMUDictionary.INSTANCE::getPhonemesRemoved),
    GET_SIMILAR_WORDS_WITH_PHONEMES(CMUDictionary.INSTANCE::getPhonemesAdded),
    GET_SPELLING((string) -> Collections.singletonList(Collections.singletonList(string)));

    private final Function<String, List<List<String>>> fetchFunction;

    CMUDictionaryDecision(Function<String, List<List<String>>> fetchFunction) {
        this.fetchFunction = fetchFunction;
    }

    public static CMUDictionaryDecision getValueOf(String userInput) {
        return valueOf(userInput
                .replaceAll(" ", "_")
                .toUpperCase());
    }

    public static List<String> names() {
        List<String> names = new ArrayList<>();
        for (CMUDictionaryDecision decision : values()) {
            names.add(decision.name());
        }
        return names;
    }

    public List<List<String>> find(String word) {
        return fetchFunction.apply(word);
    }
}
