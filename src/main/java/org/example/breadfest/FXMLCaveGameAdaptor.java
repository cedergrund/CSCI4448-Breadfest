package org.example.breadfest;

import javafx.scene.control.TableView;
import org.example.breadfest.ingredients.Ingredient;

import java.util.List;

public class FXMLCaveGameAdaptor implements FXMLCave {

    private final CaveGame adapted_game_state;

    public FXMLCaveGameAdaptor(CaveGame game_state) {
        this.adapted_game_state = game_state;
    }


    public List<Character> getRoomExitDirections(){
        return this.adapted_game_state.getRoomExitDirections();
    }

    public List<String> getObjectsAtAllLocations(){

        return this.adapted_game_state.getObjectsAtAllLocations();
    }

    public String[] getObjectByLocation(int location) {
        return this.adapted_game_state.getObjectByLocation(location);
    }

    public String getDinosaurImageByLocation(int location_index) {
        return this.adapted_game_state.getDinosaurImageByLocation(location_index);
    }

    public String getIngredientImageByLocation(int location_index) {
        return this.adapted_game_state.getIngredientImageByLocation(location_index);
    }

    public void clickLocation(int location){

        adapted_game_state.clickLocation(location);

    }

    /**
     * @return -> List of string arrays of length 3, 1 for each ingredient in inventory:
     *      [index 0 is ingredient name, index 1 is ingredient type, index 2 is ingredient rarity]
     */
    public List<String[]> getIngredientInventory(){
        return this.adapted_game_state.getIngredientInventory();
    }
    public List<String[]> getIngredientInventory(String type){
        return this.adapted_game_state.getIngredientInventory(type);
    }

    public String[] bakeIngredientsFromTable(List<String[]> baked_ingredients){
        return this.adapted_game_state.bakeIngredientsFromTable(baked_ingredients);
    }

    public boolean moveRoom(char direction){
        return this.adapted_game_state.moveRoom(direction);
    }

    public void enterRoom0(){
        this.adapted_game_state.enterRoom0();
    }

    public int getCurrPlayerPatience(){
        return adapted_game_state.getCurrPlayerPatience();
    }

    public int getCurrPlayerHonor(){
        return adapted_game_state.getCurrPlayerHonor();
    }

    public int getMaxPlayerPatience() { return adapted_game_state.getMaxPlayerPatience(); }

    public void regenerateCaveSystem() {
        adapted_game_state.regenerateCaveSystem();
    }

    public String[] fightDinosaur(int dice_rolled){
        return adapted_game_state.fightDinosaur(dice_rolled);
    }

    public boolean stopFight(boolean won_fight){
        return adapted_game_state.stopFight(won_fight);
    }

    public String getDinoImage() {return adapted_game_state.getDinoImage(); }

    public String getIngredientImageByType(String type){
        return adapted_game_state.getIngredientImageByString(type);
    }

    public void updateActiveDice(int die_to_switch){
        adapted_game_state.updateActiveDice(die_to_switch);
    }

    public String[] getFightersInformation(){
        return adapted_game_state.getFightersInformation();
    }

    public String[] getDieInformation(int die_index){
        return adapted_game_state.getDieInformation(die_index);
    }

    public String[] getPreviousReward(){
        return this.adapted_game_state.getPreviousReward();
    }



}
