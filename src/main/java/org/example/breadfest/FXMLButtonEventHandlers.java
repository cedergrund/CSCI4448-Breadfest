package org.example.breadfest;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Objects;

public class FXMLButtonEventHandlers {

    public static void openInventory(FXMLCaveApplication application, Stage stage, String locationOfButton) {
        application.generateInventory(stage,locationOfButton);
    }

    public static void enterMaze(FXMLCaveApplication application, Stage stage) {
        application.getAdaptor().enterRoom0();
        application.generateCaveRoom(stage);
    }

    public static void moveRoom(FXMLCaveApplication application, Event event, Stage stage) {
        char direction = ((Button) event.getSource()).getId().charAt(0);
        if (application.getAdaptor().moveRoom(direction)){
            System.out.println("should move back to cave entrance");
            application.generateCaveEntrance(stage);
        }
        else{
            application.generateCaveRoom(stage);
        }
    }

    public static void fightDinosaur(FXMLCaveApplication application, Stage stage, int location) {
        System.out.println("Fight Dinosaur " + Arrays.toString(application.getAdaptor().getObjectByLocation(location)));
//        cave_game_adaptor.clickLocation(location);
    }

    public static void collectIngredient(FXMLCaveApplication application, Stage stage, int location) {
        System.out.println("Collect ingredient " + Arrays.toString(application.getAdaptor().getObjectByLocation(location)));
        application.getAdaptor().clickLocation(location);
        application.generateCaveRoom(stage);
    }

    public static void returnHome(FXMLCaveApplication application, Stage stage) {
        application.generateCaveEntrance(stage);
    }

    public static void exitInventory(FXMLCaveApplication application, Stage stage, String location_to_return_to) {
        if (Objects.equals(location_to_return_to, "Cave")){
            application.generateCaveRoom(stage);
        }
        else{
            application.generateCaveEntrance(stage);
        }
    }
}
