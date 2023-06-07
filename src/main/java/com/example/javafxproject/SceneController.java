package com.example.javafxproject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SceneController extends Calculations implements Initializable {

    //declares all attributes, connects them with FXML file using @FXML
    @FXML
    TextArea inputField;
    @FXML
    ChoiceBox<String> difficultyChoice;
    @FXML
    ColorPicker colorPicker;
    @FXML
    Label promptLabel, timerLabel, accuracyLabel, wpmLabel, wpmResult, accuracyResult, bestWPM, bestAccuracy;
    @FXML
    RadioButton button1, button2, button3;
    @FXML
    Button popupButton, startButton, resetButton;
    @FXML
    Pane popupPane, resultsPane;
    @FXML
    AnchorPane anchorPane;

    private double wpm = 0;
    private double accuracy = 0;
    private final String[] choices = {"Easy", "Medium", "Extreme"};
    private ArrayList<Effect> effects = new ArrayList<>();
    private static int timeRemaining = 60;
    private static int timeElapsed = 0;
    private static double minutes = 1;
    private static String contents;
    private int promptIndex = 104;
    private String promptContents;
    private Color colorTheme;
    private double wpmRecord = 0;
    private double accuracyRecord = 0;

    //timeline is called once per second
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        if (timeRemaining > 0) {
            contents = inputField.getText();
            accuracy = Calculations.getAccuracy(promptContents.substring(0, contents.length()), contents);
            wpm = getWPM(timeElapsed);
            timeElapsed += 1;
            timeRemaining -= 1;
            setTimerLabel(timeRemaining);
            wpmLabel.setText(Math.round(wpm) + " WPM");
            try {
                accuracyLabel.setText(Math.round(accuracy) + "% ACCURATE");
            }
            catch(Exception exception){
                reset();
            }

        }
        if (timeRemaining <= 0) {
            stop();
            showResults();
        }
    }));

    //initialize method is called before anything else in the program
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultsPane.setVisible(false);
        popupPane.setVisible(true);
        difficultyChoice.getItems().addAll(choices);
        difficultyChoice.setValue("Easy");
        difficultyChoice.setOnAction(this::setDifficulty);
        promptLabel.setText(PromptText.generatePrompt("Easy"));
        promptContents = promptLabel.getText();
        colorTheme = colorPicker.getValue();
        for(Node node : anchorPane.getChildrenUnmodifiable()) {
            if(node.getEffect() != null) {
                effects.add(node.getEffect());
            } else {
                effects.add(null);
            }
        }
        blurBackground(true);
    }

    //calculates and displays timer
    public void setTimerLabel(int num) {
        if(num < 60) {
            if(num < 10) {
                timerLabel.setText("0:0" + num);
            } else timerLabel.setText("0:" + num);
        }
        else if(num % 60 == 0) {
            timerLabel.setText((num / 60) + ":00");
        } else if(num % 60 < 10) {
            timerLabel.setText(num/60 + ":0" + num % 60);
        } else timerLabel.setText(num/60 + ":" + num % 60);
    }

    //displays the results pane
    public void showResults() {
        blurBackground(true);
        resultsPane.setVisible(true);
        resultsPane.setDisable(false);
        resultsPane.setOpacity(100);
        if(wpm > wpmRecord) {
            wpmRecord = wpm;
            bestWPM.setText(Math.round(wpm) + " WPM");
        } else {
            bestWPM.setText(String.valueOf(Math.round(wpmRecord) + " WPM"));
        }
        if(accuracy > accuracyRecord) {
            accuracyRecord = accuracy;
            bestAccuracy.setText(Math.round(accuracy) + "% accuracy");
        } else {
            bestAccuracy.setText(String.valueOf(Math.round(accuracyRecord) + "% accuracy"));
        }
        wpmResult.setText(Math.round(wpm) + " WPM");
        accuracyResult.setText(Math.round(accuracy) + "% accuracy");
    }

    //auto-scrolls the prompt text based on length of user input
    public void updatePromptLabel() {
        promptLabel.setText(promptContents.substring(promptIndex, promptIndex + 208));
        promptIndex += 104;
    }

    public void beginTimer() {
        inputField.clear();
        timeRemaining = (int) Math.round(minutes * 60);
        setTimerLabel(timeRemaining);
        timeElapsed = 0;
        setDifficulty(null);
        timeline.setCycleCount(timeRemaining);
        timeline.play();
    }

    //updates color theme for several nodes
    public void changeColorTheme(ActionEvent e) {
        colorTheme = colorPicker.getValue();
        inputField.setEffect(new DropShadow(20, colorTheme));
        accuracyLabel.setTextFill(new LinearGradient(
                1.0, 0.3744, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, colorTheme),
                new Stop(1.0, new Color(0.0, 0.0, 0.0, 1.0))));
        wpmLabel.setTextFill(new LinearGradient(
                1.0, 0.3744, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, colorTheme),
                new Stop(1.0, new Color(0.0, 0.0, 0.0, 1.0))));
        button1.setTextFill(colorTheme);
        button2.setTextFill(colorTheme);
        button3.setTextFill(colorTheme);
    }

    //connected to the exit button on the initial popup pane
    public void popupButtonPressed() {
        blurBackground(false);
        popupPane.setDisable(true);
        popupPane.setOpacity(0);
        resultsPane.setDisable(true);
        resultsPane.setOpacity(0);
        reset();
    }

    //loops through and blurs every node in the pane except for the popup pane
    public void blurBackground(boolean b) {
        GaussianBlur gaussianBlur = new GaussianBlur(10);
        if(b) {
            for(Node node : anchorPane.getChildrenUnmodifiable()) {
                if(node != popupPane && node != resultsPane) {
                    if(node.getEffect() == null) {
                        node.setEffect(gaussianBlur);
                    } else {
                        gaussianBlur.setInput(node.getEffect());
                        node.setEffect(gaussianBlur);
                    }
                    node.setDisable(true);
                    node.setOpacity(100);
                }
            }
        } else {
            int i = 0;
            for(Node node : anchorPane.getChildrenUnmodifiable()) {
                node.setEffect(effects.get(i));
                node.setDisable(false);
                i++;
            }
        }
    }

    //stops timeline from being called
    public void stop() {
        timeline.stop();
        timeRemaining = (int) Math.round(minutes * 60);
        setTimerLabel(timeRemaining);
        promptIndex = 104;
    }
    public void reset() {
        stop();
        inputField.clear();
    }
    public void button1Pressed() {
        minutes = 0.5;
        setTimerLabel(30);
    }
    public void button2Pressed() {
        minutes = 1.0;
        setTimerLabel(60);
    }
    public void button3Pressed() {
        minutes = 3.0;
        setTimerLabel(180);
    }

    //sets prompt difficulty
    public void setDifficulty(ActionEvent e) {
        reset();
        promptLabel.setText((PromptText.generatePrompt(difficultyChoice.getValue())));
        promptContents = promptLabel.getText();
    }

    //updates every time a key is typed in text area
    public void onKeyTyped() {
        if (inputField.getText().length() > promptIndex) {
            updatePromptLabel();
        }
    }
    public static String getUserContent() {return contents;}
}
