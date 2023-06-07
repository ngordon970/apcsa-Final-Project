package com.example.javafxproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.net.URL;
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("C:\\Users\\24gordonno\\IdeaProjects\\apcsa-Final-Project\\src\\main\\java\\com\\example\\javafxproject\\scene1.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("TurboType");
        stage.setScene(scene);
        stage.show();

    }
}
