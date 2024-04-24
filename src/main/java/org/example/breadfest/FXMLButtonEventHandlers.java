package org.example.breadfest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.util.Arrays;
import java.util.Objects;

public class FXMLButtonEventHandlers {

    public static void openInventory(FXMLCaveApplication application, String locationOfButton) {
        application.generateInventory(locationOfButton);
    }

    public static void openBakingScene(FXMLCaveApplication application, String locationOfButton) {
        application.generateBakingScene(locationOfButton);
    }

    public static void removeSelectedRows(TableView<String[]> table) {
        ObservableList<String[]> selectedRows = FXCollections.observableArrayList();
        System.out.println("button pressed");
        // Iterate through the table to find selected rows
        for (String[] row : table.getItems()) {
            System.out.println("new row");
            for (int i = 0; i < row.length; i++) {
                System.out.println(row[i]);
            }
            if (row[0].equals("true")) { //the box was checked
                System.out.println("it was true");
                // we need to check if the column is 1 or not!
//                if(row[1].equals(1)){
//                    selectedRows.add(row);
//                    // Print the content of the row
//                    System.out.print("Row content: ");
//                    for (String cell : row) {
//                        System.out.print(cell + " ");
//                    }
//                    System.out.println();
//                }
//                else{ // this means there was more than 1 instance
//                    row[1] -= 1;
//                    selectedRows.add(row);
//                    System.out.print("Row content: ");
//                    for (String cell : row) {
//                        System.out.print(cell + " ");
//                    }
//                    System.out.println();
//                }

            }
        }

        // Remove selected rows from the table
        table.getItems().removeAll(selectedRows);
    }

    public static void enterMaze(FXMLCaveApplication application) {
        application.getAdaptor().enterRoom0();
        application.generateCaveRoom();
    }

    public static void moveRoom(FXMLCaveApplication application, Event event) {
        char direction = ((Button) event.getSource()).getId().charAt(0);
        if (application.getAdaptor().moveRoom(direction)){
            application.getAdaptor().regenerateCaveSystem();
            application.generateCaveEntrance();
        }
        else{
            application.generateCaveRoom();
        }
    }

    public static void fightDinosaur(FXMLCaveApplication application, int location) {
        System.out.println("Fight Dinosaur " + Arrays.toString(application.getAdaptor().getObjectByLocation(location)));
        application.getAdaptor().clickLocation(location);
        application.generateCaveRoom();
//        application.generateFightRoom();
    }

    public static void rollDie(FXMLCaveApplication application, int die_rolled) {
        int[] returned_ints = application.getAdaptor().fightDinosaur(die_rolled);
        int player_roll = returned_ints[0];
        int dinosaur_roll = returned_ints[1];
        int result = returned_ints[2];

        application.generateFightRoomResults(player_roll, dinosaur_roll, result);
    }

    public static void switchDie(FXMLCaveApplication application,int die_to_switch){
        application.getAdaptor().updateActiveDice(die_to_switch);
        application.generateCaveRoom();
    }

    public static void collectIngredient(FXMLCaveApplication application, int location) {
        System.out.println("Collect ingredient " + Arrays.toString(application.getAdaptor().getObjectByLocation(location)));
        application.getAdaptor().clickLocation(location);
        application.generateCaveRoom();
    }

    public static void returnHome(FXMLCaveApplication application) {
        application.getAdaptor().regenerateCaveSystem();
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
