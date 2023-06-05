package com.example.javafxproject;
import java.util.ArrayList;
import java.util.List;

public class Calculations {

    private static ArrayList<Character> missedChars = new ArrayList<>();

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
        boolean match = false;
            for (int j = i; j < passage.length(); j++) {
                if (!alreadyMatched[j] && userInput.substring(i, i + 1).equals(passage.substring(j, j + 1))) {
                    alreadyMatched[j] = true;
                    charsCorrect++;
                    match = true;
                    break;
                }
            }
            if(!match) {
                missedChars.add(userInput.charAt(i));
            }
        }
        return ((double) charsCorrect / userInput.length()) * 100;
    }
    public void clearCharList() {
        missedChars.clear();
    }
}
