package org.example.breadfest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FlashlightApp extends Application {

    private final int SCENE_WIDTH = 600;
    private final int SCENE_HEIGHT = 400;
    private final int FLASHLIGHT_RADIUS = 100;

    private Circle flashlight;
    private double dx = 0;
    private double dy = 0;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flashlight App");

        // Background
        Rectangle background = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        background.setFill(Color.YELLOW);
        root.getChildren().add(background);

        // Black overlay
        Rectangle overlay = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        overlay.setFill(Color.BLACK);
        root.getChildren().add(overlay);

        // Create the flashlight
        flashlight = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, FLASHLIGHT_RADIUS);
        flashlight.setFill(Color.TRANSPARENT);
        flashlight.setStroke(Color.YELLOW);
        flashlight.setStrokeWidth(3);
        flashlight.setBlendMode(BlendMode.MULTIPLY); // This blend mode reveals background through overlay
        root.getChildren().add(flashlight);

        // Handle arrow key events to move the flashlight
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    dy = -10;
                    break;
                case DOWN:
                    dy = 10;
                    break;
                case LEFT:
                    dx = -10;
                    break;
                case RIGHT:
                    dx = 10;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                case DOWN:
                    dy = 0;
                    break;
                case LEFT:
                case RIGHT:
                    dx = 0;
                    break;
            }
        });

        // Move the flashlight continuously
        scene.setOnKeyPressed(event -> {
            flashlight.setCenterX(flashlight.getCenterX() + dx);
            flashlight.setCenterY(flashlight.getCenterY() + dy);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
