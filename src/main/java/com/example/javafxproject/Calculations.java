package com.example.javafxproject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calculations {

    private static ArrayList<Character> charsMissed = new ArrayList<Character>();
    public static double getWPM(int timeElapsed) {

        ArrayList<String> words = new ArrayList<>(List.of(SceneController.getUserContent().split(" ")));
        int length = words.size();
        return Math.round(length * ((double) 60 / timeElapsed));
    }

    public static ArrayList<String> getPromptWords(String prompt) {
        return new ArrayList<>(List.of(prompt.split(" ")));
    }

    public static double getAccuracy(String passage, String userInput) {
        boolean[] alreadyMatched = new boolean[userInput.length()];
        int charsCorrect = 0;

        for (int i = 0; i < userInput.length(); i++) {

            for (int j = 0; j < passage.length(); j++) {
                if (!alreadyMatched[j] && userInput.substring(i, i + 1).equals(passage.substring(j, j + 1))) {
                    alreadyMatched[j] = true;
                    charsCorrect++;
                    break;
                }
            }
        }
        return ((double) charsCorrect / userInput.length()) * 100;
    }

    public int getCurrentPromptRow(int currIndex) { return currIndex / 66 + 1; }
}