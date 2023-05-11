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
        URL url = new File("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\main\\java\\com\\example\\javafxproject\\sample.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

//        Image icon = new Image("C:\\Users\\Noah\\IdeaProjects\\javaFXPROJECT\\src\\minimal circle pfp.png");
//        stage.setTitle("Demo Program");
//        stage.getIcons().add(icon);


    //********* SCENE METHODS**********
//        Text text = new Text();
//        text.setText("sick brooo");
//        text.setX(50);
//        text.setY(50);
//        text.setFont(Font.font("Verdana",50));
//        text.setFill(Color.PURPLE);
//
//        Line line = new Line();
//        line.setStartX(200);
//        line.setStartY(200);
//        line.setEndX(400);
//        line.setEndY(200);
//        line.setStrokeWidth(10);
//        line.setStroke(Color.ORANGE);
//        line.setOpacity(0.4);
//
//        Rectangle rectangle = new Rectangle();
//        rectangle.setX(100);
//        rectangle.setY(100);
//        rectangle.setWidth(100);
//        rectangle.setHeight(100);
//        rectangle.setFill(Color.BLUEVIOLET);
//        rectangle.setStrokeWidth(5);
//        rectangle.setStroke(Color.BLACK);
//
//        Polygon triangle = new Polygon();
//        triangle.getPoints().setAll(200.0,200.0,  300.0, 300.0,  200.0, 300.0);
//        triangle.setFill(Color.BROWN);
//
//        Circle circle = new Circle();
//        circle.setCenterX(350);
//        circle.setCenterY(350);
//        circle.setFill(Color.GREENYELLOW);
//
//        ImageView imageView = new ImageView(icon);
//        imageView.setX(100);
//        imageView.setY(100);
//        imageView.setScaleX(0.5);
//        imageView.setScaleY(0.5);

    //*******STAGE METHODS*******
//        stage.setWidth(500);
//        stage.setHeight(500);
//        stage.setResizable(true);
//        stage.setX(stage.getX());
//        stage.setY(stage.getY());
//        stage.setFullScreen(false);

//        root.getChildren().add(text);
//        root.getChildren().add(line);
//        root.getChildren().add(rectangle);
//        root.getChildren().add(triangle);
//        root.getChildren().add(circle);
//        root.getChildren().add(imageView);

    }
}