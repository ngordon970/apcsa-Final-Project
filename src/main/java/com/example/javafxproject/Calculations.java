package com.example.javafxproject;
import java.util.ArrayList;
import java.util.List;

public class Calculations {
    //calculates WPM
    public static double getWPM(int timeElapsed) {

        ArrayList<String> words = new ArrayList<>(List.of(SceneController.getUserContent().split(" ")));
        int length = words.size();
        return Math.round(length * ((double) 60 / timeElapsed));
    }

    //returns an arraylist of all words in the passage
    public static ArrayList<String> getPromptWords(String prompt) {
        return new ArrayList<>(List.of(prompt.split(" ")));
    }

    //calculates user accuracy
    public static double getAccuracy(String passage, String userInput) {
        //for each character in user input, true = exists in passage, false = does not exist in passage
        boolean[] alreadyMatched = new boolean[userInput.length()];
        int charsCorrect = 0;

        for (int i = 0; i < userInput.length(); i++) {

            for (int j = i; j < passage.length(); j++) {
                if (!alreadyMatched[j] && userInput.substring(i, i + 1).equals(passage.substring(j, j + 1))) {
                    alreadyMatched[j] = true;
                    charsCorrect++;
                    break;
                }
            }
        }
        return ((double) charsCorrect / userInput.length()) * 100;
    }
}
