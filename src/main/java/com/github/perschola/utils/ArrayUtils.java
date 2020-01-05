package com.github.perschola.utils;

import java.util.Objects;

public class ArrayUtils {

    /**
     * returns a `Double` representative of the likeness of two arrays;
     * 1 being completely same elements and ordinal, 0 being no same elements with same ordinal
     * @param baseArray - base array to compare with
     * @param deltaArray - array to compare against
     * @return a value in range of 0 to 1 inclusively, 0.5 being half elements are same with same ordinal.
     */
    public static Double getSimilarity(String[] baseArray, String[] deltaArray) {
        Double similarity = 0.0;
        for (int i = 0; i < baseArray.length; i++) {
            String baseArrayElement = baseArray[i];
            String deltaArrayElement = deltaArray[i];
            Boolean sameElement = baseArrayElement.equals(deltaArrayElement);
            similarity = sameElement ? similarity+1 : similarity;
        }
        return similarity/baseArray.length;
    }
}
