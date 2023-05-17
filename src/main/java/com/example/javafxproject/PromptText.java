package com.example.javafxproject;
import java.io.FileReader;


public class PromptText {
    
    public static String generateEasyPrompt() {
        StringBuilder easyPrompt = new StringBuilder();
        try {
            FileReader reader = new FileReader("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\main\\java\\com\\example\\javafxproject\\easyPrompt.txt");
            int data = reader.read();
            while(data != -1) {
                easyPrompt.append((char)data);
                data = reader.read();
            }
            reader.close();
            int randomLoc = (int)(Math.random() * easyPrompt.length() - 150);
            easyPrompt = new StringBuilder(easyPrompt.substring(randomLoc));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return easyPrompt.toString();
    }
    
        public static String generateMedPrompt() {
        StringBuilder medPrompt = new StringBuilder();
        try {
            FileReader reader = new FileReader("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\main\\java\\com\\example\\javafxproject\\medPrompt.txt");
            int data = reader.read();
            while(data != -1) {
                medPrompt.append((char)data);
                data = reader.read();
            }
            reader.close();
            int randomLoc = (int)(Math.random() * medPrompt.length() - 150);
            medPrompt = new StringBuilder(medPrompt.substring(randomLoc));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return medPrompt.toString();
    }
    
       public static String generateExtremePrompt() {
        StringBuilder extremePrompt = new StringBuilder();
        try {
            FileReader reader = new FileReader("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\main\\java\\com\\example\\javafxproject\\extremePrompt.txt");
            int data = reader.read();
            while(data != -1) {
                extremePrompt.append((char)data);
                data = reader.read();
            }
            reader.close();
            int randomLoc = (int)(Math.random() * extremePrompt.length() - 150);
            extremePrompt = new StringBuilder(extremePrompt.substring(randomLoc));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return extremePrompt.toString();
    }
}
