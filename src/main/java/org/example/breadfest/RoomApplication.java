package org.example.breadfest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class RoomApplication extends Application {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void populateAndLoad(Stage stage) {
        // Generate FXML content
        FXMLBuilder my_fxml_Builder = new FXMLBuilder();
        List<Character> sample_room_direction = List.of('N', 'S');
        StringBuilder contentBuilder = my_fxml_Builder.generateFXMLContent(sample_room_direction);

        // Convert the StringBuilder to a String
        String content = contentBuilder.toString();
        // Write content to FXML file
        executor.execute(() -> {
            FXMLContent.wipeFileContent("dynamic_room_test.fxml");
            FXMLContent.writeToFxmlFile("dynamic_room_test.fxml", content);
            // Load FXML file after content is written
            Platform.runLater(() -> loadFXML(stage));
        });
    }

    private void loadFXML(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home_room.fxml"));
            Parent root = loader.load();

            // Set up the scene
            Scene scene = new Scene(root, 2000, 2000);

            // Set the stage title
            stage.setTitle("Room Application Test");

            // Set the scene
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        // Populate and load FXML
        populateAndLoad(stage);
    }

    public static void main(String[] args) {
        launch(args);
        executor.shutdown();
    }
}