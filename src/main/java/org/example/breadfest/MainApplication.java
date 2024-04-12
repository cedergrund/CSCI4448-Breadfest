package org.example.breadfest;

import javafx.application.Application;
import javafx.stage.Stage;

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
