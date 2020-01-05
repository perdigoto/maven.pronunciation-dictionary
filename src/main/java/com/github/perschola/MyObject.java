package com.github.perschola;

import com.github.perschola.linguistics.CMUDictionaryDecision;
import com.github.perschola.utils.Console;

import java.util.List;

public class MyObject implements Runnable {
    Console CONSOLE = Console.INSTANCE;

    public void setup() {
        CONSOLE.println("Welcome to my dictionary app");
    }

    public void run() {
        String userInputWord;
        String userInputDecision;
        do {
            userInputWord = CONSOLE.getStringInput("What word would like to investigate?");
            do {
                CONSOLE.println("Select investigation technique");
                userInputDecision = CONSOLE.getStringInput(CMUDictionaryDecision.names().toString());
                CMUDictionaryDecision decision = CMUDictionaryDecision.getValueOf(userInputDecision);
                List<List<String>> output = decision.find(userInputWord);
                CONSOLE.println(output.toString());
            } while (!"quit".equals(userInputDecision));
        } while (!"quit".equals(userInputWord));
    }
}
