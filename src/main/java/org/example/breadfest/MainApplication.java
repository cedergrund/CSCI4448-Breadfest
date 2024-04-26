package org.example.breadfest;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class MainApplication extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // raw game object
        CaveGame game = new CaveGame();

        // game object adapted for java fx output
        FXMLCave adapted_game = new FXMLCaveGameAdaptor(game);

        // java fx application that displays game
        FXMLCaveApplication javafx_application = new FXMLCaveApplication(adapted_game, stage);

        // run game
        javafx_application.runGame();
    }

}
