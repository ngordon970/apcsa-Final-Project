package com.example.javafxproject;
import java.io.FileReader;


public class PromptText {
    public static String generatePrompt(String choice) {

        if (choice.equals("Easy")) //LEVEL 1 DIFFICULTY
        {
            StringBuilder easyPrompt = new StringBuilder();
            try {
                FileReader reader = new FileReader("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\main\\java\\com\\example\\javafxproject\\easyPrompt.txt");
                int data = reader.read();
                while (data != -1) {
                    easyPrompt.append((char) data);
                    data = reader.read();
                }
                reader.close();
                int randomLoc = (int) (Math.random() * (easyPrompt.length() - 150 + 1));
                easyPrompt = new StringBuilder(easyPrompt.substring(randomLoc));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return easyPrompt.toString();
        }

        else if (choice.equals("Medium")) //LEVEL 2 DIFFICULTY
        {
            StringBuilder medPrompt = new StringBuilder();
            try {
                FileReader reader = new FileReader("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\main\\java\\com\\example\\javafxproject\\mediumPrompt.txt");
                int data = reader.read();
                while (data != -1) {
                    medPrompt.append((char) data);
                    data = reader.read();
                }
                reader.close();
                int randomLoc = (int) (Math.random() * (medPrompt.length() - 150 + 1));
                medPrompt = new StringBuilder(medPrompt.substring(randomLoc));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return medPrompt.toString();
        }

        else //LEVEL 3 DIFFICULTY
        {
            StringBuilder extremePrompt = new StringBuilder();
            try {
                FileReader reader = new FileReader("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\main\\java\\com\\example\\javafxproject\\extremePrompt.txt");
                int data = reader.read();
                while (data != -1) {
                    extremePrompt.append((char) data);
                    data = reader.read();
                }
                reader.close();
                int randomLoc = (int) (Math.random() * (extremePrompt.length() - 150 + 1));
                extremePrompt = new StringBuilder(extremePrompt.substring(randomLoc));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return extremePrompt.toString();
        }
    }
}
