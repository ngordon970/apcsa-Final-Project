package com.example.javafxproject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController extends Calculations implements Initializable {

    @FXML
    private TextArea field;
    @FXML
    private Label timerLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label wpmLabel;
    @FXML
    private ChoiceBox<String> difficultyChoice;
    @FXML
    Label promptLabel;
    @FXML

    private final String[] choices = {"Easy", "Medium", "Extreme"};
    private static int timeRemaining = 60;
    private static int timeElapsed = 0;
    private static double minutes = 1;
    private static String contents;
    private int promptIndex = 104;
    private String promptContents;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        if (timeRemaining > 0) {
            if(field.getText().length() > promptIndex) {
                updatePromptLabel();
            }
            timeElapsed += 1;
            timeRemaining -= 1;
            setTimerLabel(timeRemaining);
            contents = field.getText();
            wpmLabel.setText((int) (getWPM(timeElapsed)) + " WPM");
            try {
                accuracyLabel.setText((Calculations.getAccuracy(promptContents.substring(0, contents.length()), contents)) + "%");
            }
            catch(Exception exception){
                timeRemaining = 0;
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
        field.clear();
        timeRemaining = (int) Math.round(minutes * 60);
        setTimerLabel(timeRemaining);
        timeElapsed = 0;
        setDifficulty(null);
        timeline.setCycleCount(timeRemaining);
        timeline.play();
    }

    public void setDifficulty(ActionEvent e) {
        promptLabel.setText((PromptText.generatePrompt(difficultyChoice.getValue())));
        promptContents = promptLabel.getText();
    }

    public void testingTimer() {
        field.clear();
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
        field.clear();
    }
    public void button30sPressed() {
        minutes = 0.5;
        setTimerLabel(30);
    }

    public void button60sPressed() {
        minutes = 1.0;
        setTimerLabel(60);
    }

    public void button180sPressed() {
        minutes = 3.0;
        setTimerLabel(180);
    }

    public static String getUserContent() {
        return contents;
    }

}