package com.example.javafxproject;

import java.util.ArrayList;
import java.util.List;

public class Calculations {

    public static double getWPM(int timeElapsed) {

        ArrayList<String> words = new ArrayList<>(List.of(SceneController.getUserContent().split(" ")));
        int length = words.size();
        return Math.round(length * ((double) 60 / timeElapsed));
    }

    public static double getAccuracy(String passage, String userInput) {
        int accurate = 0; //tracks number of accurate characters
        int p = 0; //tracks index of passage text

        for (int i = 0; i < userInput.length(); i++) {
            if(userInput.substring(i,i+1).equals(passage.substring(p,p+1))) {
                accurate++;
                p++;
            }
        }
        return ((double)accurate / userInput.length()) * 100;
    }
}