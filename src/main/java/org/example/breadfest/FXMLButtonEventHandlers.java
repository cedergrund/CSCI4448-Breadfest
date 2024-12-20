package org.example.breadfest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientRarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FXMLButtonEventHandlers {

    public static void openInventory(FXMLCaveApplication application, String locationOfButton) {
        application.generateInventory(locationOfButton);
    }


    public static void openBakingScene(FXMLCaveApplication application, int upgrade) {
        application.generateBaseBaking(upgrade);
    }

    public static void enterMaze(FXMLCaveApplication application) {
        application.getAdaptor().enterRoom0();
        application.generateCaveRoom();
    }

    public static void moveRoom(FXMLCaveApplication application, Event event) {
        char direction = ((Button) event.getSource()).getId().charAt(0);

        if (application.getAdaptor().moveRoom(direction)){ // run out of patience
            application.popUpPatienceExhausted();
        }
        else{
            application.generateCaveRoom();
        }
    }

    public static void fightDinosaur(FXMLCaveApplication application, int location) {
        System.out.println("Fight Dinosaur " + Arrays.toString(application.getAdaptor().getObjectByLocation(location)));
        application.getAdaptor().clickLocation(location);
        application.generateFightRoom();
    }

    private static void rollDie(FXMLCaveApplication application, int die_rolled) {
        String[] fight_results = application.getAdaptor().fightDinosaur(die_rolled);
        application.generateFightRoomResults(fight_results);
    }

    public static void startNextFightTurn(FXMLCaveApplication application, String last_turn_result){
        if (Objects.equals(last_turn_result, "dino")){
            application.generateFightRoomRewards(application.getAdaptor().stopFight(true));
        }
        else if (Objects.equals(last_turn_result, "player")){
            application.getAdaptor().stopFight(false);
            application.popUpPatienceExhausted();
        }
        else{
            application.generateFightRoom();
        }
    }

    public static void switchDieScene(FXMLCaveApplication application){

        application.generateDieSelectorForMergeConflict();
    }

    public static void exitFight(FXMLCaveApplication application){
        application.generateCaveRoom();
    }

    public static void switchDieAndExit(FXMLCaveApplication application,Event event){
        int button_pushed = Integer.parseInt(((Button) event.getSource()).getId());
        application.getAdaptor().updateActiveDice(button_pushed);
        application.generateCaveRoom();
    }

    public static void fightButtonPushed(FXMLCaveApplication application, Event event){
        int button_pushed = Integer.parseInt(((Button) event.getSource()).getId());
        if (button_pushed == 3){
            fleeFight(application);
        }
        else{
            rollDie(application, button_pushed);
        }
    }

    private static void fleeFight(FXMLCaveApplication application){

        if (application.getAdaptor().stopFight(false)){ // player ran out of patience while trying to flee
            application.popUpPatienceExhausted();
        }
        else { // player was able to flee
            application.generateCaveRoom();
        }

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


    public static void rawrdoughValleyExplodes(FXMLCaveApplication application) {
        application.playExplodeVideo();
    }

    public static void creditsScreen(FXMLCaveApplication application) {
        System.out.println("should play video");
        application.playCreditsScreen();
    }

    public static void bakingNextClicked(FXMLCaveApplication application, ActionEvent event, List<String[]> baked_ingredients, TableView<String[]> table) {

        if (table != null){
            // baked_ingredients
            for (String[] row : table.getItems()) {
                if (row[4].equals("true")) { //the box was checked
                    baked_ingredients.add(row);
                }
            }
        }

        String button_pushed = ((Button) event.getSource()).getId();
        String next_ingredient;
        switch(button_pushed){
            case "start":{
                next_ingredient = "Flour";
                break;
            }
            case "Flour":{
                next_ingredient = "Water";
                break;
            }
            case "Water":{
                next_ingredient = "Yeast";
                break;
            }
            case "Yeast":{
                next_ingredient = "Salt";
                break;
            }
            case "Salt":{
                next_ingredient = "Topping";
                break;
            }
            default:{
                next_ingredient = "Done";
            }
        }

        if (next_ingredient.equals("Done")){
            String[] bread_result = application.getAdaptor().bakeIngredientsFromTable(baked_ingredients);
            application.generateBreadResults(bread_result);
        }
        else{
            application.popUpBakingNextTable(next_ingredient, baked_ingredients);
        }

    }
}
