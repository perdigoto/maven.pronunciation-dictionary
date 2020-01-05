package com.github.perschola.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PowerSet<T> {
    private final Set<Set<T>> powerSet;

    public PowerSet(T... array) {
        this(new HashSet<>(Arrays.asList(array)));
    }
    public PowerSet(Set<T> set) {
        T[] element = (T[]) set.toArray();
        final int SET_LENGTH = 1 << element.length;
        this.powerSet = new HashSet<>();
        for (int binarySet = 0; binarySet < SET_LENGTH; binarySet++) {
            Set<T> subset = new HashSet<>();
            for (int bit = 0; bit < element.length; bit++) {
                int mask = 1 << bit;
                if ((binarySet & mask) != 0) {
                    subset.add(element[bit]);
                }
            }
            powerSet.add(subset);
        }
    }

    public Set<Set<T>> getPowerSet() {
        return powerSet;
    }
}
