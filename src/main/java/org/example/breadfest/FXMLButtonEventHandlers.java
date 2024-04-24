package org.example.breadfest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.example.breadfest.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FXMLButtonEventHandlers {

    public static void openInventory(FXMLCaveApplication application, String locationOfButton) {
        application.generateInventory(locationOfButton);
    }

    public static void openBakingScene(FXMLCaveApplication application, String locationOfButton) {
        application.generateBakingScene(locationOfButton);
    }

    public static void bakeIngredients(FXMLCaveApplication application, TableView<String[]> table) {
        ObservableList<String[]> selectedRows = FXCollections.observableArrayList();
        int cumulative_score = 0;
        List<String> ingredient_type_list = new ArrayList<>();
        // Iterate through the table to find selected rows
        for (String[] row : table.getItems()) {
            if (row[4].equals("true")) { //the box was checked
                Ingredient baked_ingredient = application.getAdaptor().removeIngredientFromInventory(row[1]);
                int ingredient_score = baked_ingredient.getScore();
                cumulative_score += ingredient_score;

                ingredient_type_list.add(row[2]); // this collects all types of selected ingredients

                // we need to check if the column is 1 or not!
                if(row[0].equals("1")){
                    selectedRows.add(row);
                    // Print the content of the row

                }
                else{ // this means there was more than 1 instance
                    int curr_count = Integer.parseInt(row[0]);
                    curr_count -= 1;
                    row[0] = String.valueOf(curr_count);
                }
            }
        }

        //ingredient_list contains the types of all selected ingredients
        // check if the list contains at least 1 of the following:
        // flour,water,salt,yeast

        Boolean valid_ingredient = isValidIngredientList(ingredient_type_list);

        if (valid_ingredient){
            table.getItems().removeAll(selectedRows);
            application.getAdaptor().changeCurrHonor(cumulative_score);
        }
        else {
            table.getItems().removeAll(selectedRows);
            application.getAdaptor().changeCurrHonor(0);
        }



        // Remove selected rows from the table

    }

    private static Boolean isValidIngredientList(List<String> ingredient_type_list){
        Boolean contains_flour = false;
        Boolean contains_water = false;
        Boolean contains_salt = false;
        Boolean contains_yeast = false;

        for (String ingredientType : ingredient_type_list) {
            switch (ingredientType) {
                case "Flour":
                    // Handle Type1
                    contains_flour = true;
                    break;
                case "Water":
                    // Handle Type2
                    contains_water = true;
                    break;
                case "Salt":
                    // Handle Type3
                    contains_salt = true;
                    break;
                // Add more cases as needed
                case "Yeast":
                    // Handle yeast
                    contains_yeast = true;
                    break;
                default:
                    // do nothing! since it didn't match :D
                    // this will be the case for all Toppings
                    break;
            }
        }
        if (contains_flour && contains_water && contains_salt && contains_yeast){
            // then we have a valid bread!
            return true;
        }
        else {
            // our bread we've tried to bake is not valid, since it doesn't contain at least 1 of every essential ingredient type
            return false;
        }
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
//        application.generateCaveRoom();
        application.generateFightRoom();
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
