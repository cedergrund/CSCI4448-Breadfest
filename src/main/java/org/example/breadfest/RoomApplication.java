package org.example.breadfest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RoomApplication.class.getResource("scene1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 2000, 2000);
        stage.setTitle("Welcome to the Breadfest video game!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}