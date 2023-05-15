package com.example.javafxproject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    @FXML
    TextArea field;
    @FXML
    Label timerLabel = new Label("60");
    @FXML
    RadioButton button30s;
    @FXML
    RadioButton button60s;
    @FXML
    RadioButton button180s;
    @FXML
    Label wpmLabel = new Label("0" + "WPM");
    private static int timeRemaining = 60;
    private static int timeElapsed = 0;
    private static double minutes = 1;
    private static String contents;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        if (timeRemaining > 0) {
            timeElapsed += 1;
            timeRemaining -= 1;
            setTimerLabel(timeRemaining);
            contents = field.getText();
            wpmLabel.setText((int) (Calculations.getWPM(timeElapsed)) + " WPM");
        }
        if (timeRemaining <= 0) {
            System.out.println(Calculations.getAccuracy("Hello There", contents));
        }
    }));
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        field.setPromptText("Begin typing here...");
    }
    public void beginTimer() {
        field.clear();
        timeRemaining = (int) Math.round(minutes * 60);
        setTimerLabel(timeRemaining);
        timeElapsed = 0;
        timeline.setCycleCount(timeRemaining);
        timeline.play();
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

    public void setTimerLabel(int num) {
        if(num < 60) {
            if(num < 10) {
                timerLabel.setText("0:0" + String.valueOf(num));
            } else timerLabel.setText("0:" + String.valueOf(num));
        }
        else if(num % 60 == 0) {
            timerLabel.setText((num / 60) + ":00");
        } else if(num % 60 < 10) {
            timerLabel.setText(num/60 + ":0" + num % 60);
        } else timerLabel.setText(num/60 + ":" + num % 60);
    }

}
