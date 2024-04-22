package org.example.breadfest;

import javafx.event.Event;
import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.Objects;

public class FXMLButtonEventHandlers {

    public static void openInventory(FXMLCaveApplication application, String locationOfButton) {
        application.generateInventory(locationOfButton);
    }

    public static void openBakingScene(FXMLCaveApplication application, String locationOfButton) {
        application.generateBakingScene(locationOfButton);
    }

    public static void enterMaze(FXMLCaveApplication application) {
        application.getAdaptor().enterRoom0();
        application.generateCaveRoom();
    }

    public static void moveRoom(FXMLCaveApplication application, Event event) {
        char direction = ((Button) event.getSource()).getId().charAt(0);
        if (application.getAdaptor().moveRoom(direction)){
            System.out.println("should move back to cave entrance");
            application.generateCaveEntrance();
        }
        else{
            application.generateCaveRoom();
        }
    }

    public static void fightDinosaur(FXMLCaveApplication application, int location) {
        System.out.println("Fight Dinosaur " + Arrays.toString(application.getAdaptor().getObjectByLocation(location)));
//        cave_game_adaptor.clickLocation(location);
    }

    public static void collectIngredient(FXMLCaveApplication application, int location) {
        System.out.println("Collect ingredient " + Arrays.toString(application.getAdaptor().getObjectByLocation(location)));
        application.getAdaptor().clickLocation(location);
        application.generateCaveRoom();
    }

    public static void returnHome(FXMLCaveApplication application) {
        application.generateCaveEntrance();
    }

    public static void exitInventory(FXMLCaveApplication application, String location_to_return_to) {
        if (Objects.equals(location_to_return_to, "Cave")){
            application.generateCaveRoom();
        }
        else{
            application.generateCaveEntrance();
        }
    }
}
