package org.example.breadfest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class RoomApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLContent.writeToFxmlFile("dynamic_room.fxml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<?import javafx.scene.layout.StackPane?>\n" +
                "<?import javafx.scene.control.Label?>\n" +
                "\n" +
                "<StackPane xmlns=\"http://javafx.com/javafx\"\n" +
                "            xmlns:fx=\"http://javafx.com/fxml\"\n" +
                "            fx:controller=\"com.example.Controller\"\n" +
                "            prefWidth=\"400\" prefHeight=\"300\">\n" +
                "    <Label text=\"Hello, World!\" />\n" +
                "</StackPane>");

        // Load the FXML file
        FXMLLoader root = new FXMLLoader(getClass().getResource("dynamic_room.fxml"));

        // Set up the scene
        Scene scene = new Scene(root.load(), 400, 300);

        // Set the stage title
        stage.setTitle("Room Application");

        // Set the scene
        stage.setScene(scene);

        // Show the stage
        stage.show();



//        FXMLLoader fxmlLoader = new FXMLLoader(RoomApplication.class.getResource("template_scene.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 2000, 2000);
//        stage.setTitle("Welcome to the Breadfest video game!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}