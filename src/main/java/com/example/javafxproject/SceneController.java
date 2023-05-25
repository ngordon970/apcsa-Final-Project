package com.example.javafxproject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

import java.awt.*;
import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController extends Calculations implements Initializable {

    @FXML
    private TextArea inputField;
    @FXML
    private Label timerLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label wpmLabel;
    @FXML
    private ChoiceBox<String> difficultyChoice;
    @FXML
    ColorPicker colorPicker;
    @FXML
    Label promptLabel;
    @FXML
    RadioButton button1, button2, button3;

    private final String[] choices = {"Easy", "Medium", "Extreme"};
    private static int timeRemaining = 60;
    private static int timeElapsed = 0;
    private static double minutes = 1;
    private static String contents;
    private int promptIndex = 104;
    private String promptContents;
    private Color colorTheme;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        if (timeRemaining > 0) {
            timeElapsed += 1;
            timeRemaining -= 1;
            setTimerLabel(timeRemaining);
            contents = inputField.getText();
            wpmLabel.setText((int) (getWPM(timeElapsed)) + " WPM");
            try {
                accuracyLabel.setText(Math.round((Calculations.getAccuracy(promptContents.substring(0, contents.length()), contents))) + "% ACCURATE");
            }
            catch(Exception exception){
                reset();
            }

        }
        if (timeRemaining <= 0) {
            stop();
        }
    }));
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficultyChoice.getItems().addAll(choices);
        difficultyChoice.setValue("Easy");
        difficultyChoice.setOnAction(this::setDifficulty);
        promptLabel.setText(PromptText.generatePrompt("Easy"));
        promptContents = promptLabel.getText();
        colorTheme = colorPicker.getValue();
    }
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
        promptLabel.setTextFill(colorTheme);
    }

    public void setDifficulty(ActionEvent e) {
        reset();
        promptLabel.setText((PromptText.generatePrompt(difficultyChoice.getValue())));
        promptContents = promptLabel.getText();
    }

    public void onKeyTyped() {
        if (inputField.getText().length() > promptIndex) {
            updatePromptLabel();
        }
    }

    public void testingTimer() {
        inputField.clear();
        timeRemaining = 5;
        timeline.setCycleCount(timeRemaining);
        timeline.play();
    }

    public void stop() {
        timeline.stop();
        timeRemaining = (int) Math.round(minutes * 60);
        setTimerLabel(timeRemaining);
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

    public static String getUserContent() {
        return contents;
    }

}
