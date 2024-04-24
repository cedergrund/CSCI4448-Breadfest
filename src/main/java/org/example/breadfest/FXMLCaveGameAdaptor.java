package org.example.breadfest;

import javafx.scene.control.TableView;
import org.example.breadfest.ingredients.Ingredient;

import java.util.List;

public class FXMLCaveGameAdaptor implements FXMLCave {

    private final CaveGame adapted_game_state;

    public FXMLCaveGameAdaptor(CaveGame game_state) {
        this.adapted_game_state = game_state;
    }

    public String getBackgroundImage(){
        return this.adapted_game_state.getBackgroundImage();
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

    public Ingredient removeIngredientFromInventory(String ingredient_name) {
        return this.adapted_game_state.removeIngredientFromInventory(ingredient_name);
    }

    public boolean isValidIngredientList(List<String> ingredient_type_list){
        return this.adapted_game_state.isValidIngredientList(ingredient_type_list);
    }

    public void bakeIngredientsFromTable(TableView<String[]> table){
        this.adapted_game_state.bakeIngredientsFromTable(table);
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

    public int changeCurrHonor(int honor_change){
        return adapted_game_state.changeCurrHonor(honor_change);
    }

    public int getMaxPlayerPatience() { return adapted_game_state.getMaxPlayerPatience(); }

    public void regenerateCaveSystem() {
        adapted_game_state.regenerateCaveSystem();
    }

    public int[] fightDinosaur(int dice_rolled){
        return adapted_game_state.fightDinosaur(dice_rolled);
    }

    public boolean dinosaurBeaten(){
        return adapted_game_state.dinosaurBeaten();
    }

    public String getDinoImage() {return adapted_game_state.getDinoImage(); }

    public void updateActiveDice(int die_to_switch){
        adapted_game_state.updateActiveDice(die_to_switch);
    }

    public String[] getFightersInformation(){
        return adapted_game_state.getFightersInformation();
    }

    public String[] getDieInformation(int die_index){
        return adapted_game_state.getDieInformation(die_index);
    }



}
