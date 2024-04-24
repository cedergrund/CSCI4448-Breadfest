package org.example.breadfest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CaveGame {

    private final Player player;
    private Cave curr_cave;
    private Cave cave0;

    public CaveGame(){
        this.player = Player.getInstance();
        try {
            this.curr_cave = Cave.enterRoom0();
            this.cave0 = curr_cave;
        }
        catch (Exception e){
            System.out.println("Error " + e);
        }
    }


    public String getBackgroundImage() {
        return this.curr_cave.getBackgroundImage();
    }

    public void playerCollectsIngredient(Ingredient ingredient, int location) {
        this.player.addIngredientToInventory(ingredient);
        this.curr_cave.removeObjectFromLocation(location);
    }

    public List<Character> getRoomExitDirections(){
        List<Character> room_exit_directions = new ArrayList<>();

        if (this.curr_cave.getRoomNeighbor('N') != null){
            room_exit_directions.add('N');
        }
        if (this.curr_cave.getRoomNeighbor('W') != null){
            room_exit_directions.add('W');
        }
        if (this.curr_cave.getRoomNeighbor('E') != null){
            room_exit_directions.add('E');
        }
        if (this.curr_cave.getRoomNeighbor('S') != null){
            room_exit_directions.add('S');
        }

        return room_exit_directions;

    }

    public List<String> getObjectsAtAllLocations(){

        Dinosaur[] dinosaur_locations = this.curr_cave.getRoomDinosaurs();
        Ingredient[] ingredient_locations = this.curr_cave.getRoomIngredients();

        List<String> objects_by_location = new ArrayList<>();

        for (int location_index = 0; location_index <= 7; location_index++){
            if (dinosaur_locations[location_index] != null){
                objects_by_location.add("dinosaur");
            }
            else if (ingredient_locations[location_index] != null){
                objects_by_location.add("ingredient");
            }
            else {
                objects_by_location.add("null");
            }
        }
        return objects_by_location;
    }

    public void clickLocation(int location){

        if (this.curr_cave.getRoomDinosaurs()[location] != null){
            this.player.setFightingDinosaur(this.curr_cave.getRoomDinosaurs()[location]);
            this.curr_cave.removeObjectFromLocation(location);
        }
        else if (this.curr_cave.getRoomIngredients()[location] != null){
            this.player.addIngredientToInventory(this.curr_cave.getRoomIngredients()[location]);
            this.curr_cave.removeObjectFromLocation(location);
        }

    }

    public List<String[]> getIngredientInventory(){

        return player.getIngredientInventory();
    }

    Boolean isValidIngredientList(List<String> ingredient_type_list){
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

    public void bakeIngredientsFromTable(TableView<String[]> table){
        //this essentially does two things by analyzing the selected rows in the table
        // it changes the player's honor, and it modifies the player's inventory
        //Lets do it!


        ObservableList<String[]> selectedRows = FXCollections.observableArrayList();
        int cumulative_score = 0; // this will be our total score of all baked ingredients
        List<String> ingredient_type_list = new ArrayList<>();
        // Iterate through the table to find selected rows
        for (String[] row : table.getItems()) {
            if (row[4].equals("true")) { //the box was checked
                Ingredient baked_ingredient = player.removeIngredientFromInventory(row[1]);
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

        // check if bake is valid and then change honor
        Boolean valid_ingredient = this.isValidIngredientList(ingredient_type_list);
        table.getItems().removeAll(selectedRows);
        if (valid_ingredient){
            player.changeCurrHonor(cumulative_score);
        }
        else {
            player.changeCurrHonor(0);
        }
        // there's no need to remove rows for the displayed table, just remove them from the inventory! then reload the scene :)
    }

    public Ingredient removeIngredientFromInventory(String ingredient_name){
        return player.removeIngredientFromInventory(ingredient_name);
    }

    public boolean moveRoom(char direction){
        try {
            this.curr_cave = this.curr_cave.move(direction);
            if (this.player.changeCurrPatience(-10)){
                System.out.println("player patience run out.");
                return true;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public void enterRoom0(){
        this.curr_cave = this.cave0;
        this.player.resetPatience();
    }

    public String[] getObjectByLocation(int location) {

        if (this.curr_cave.getRoomDinosaurs()[location] != null){
            Dinosaur dinosaur_at_location = this.curr_cave.getRoomDinosaurs()[location];
            String[] returned_list = new String[2];
            returned_list[0] = dinosaur_at_location.getName();
            returned_list[1] = dinosaur_at_location.getDinosaurType().toString();
            return returned_list;
        }
        else if (this.curr_cave.getRoomIngredients()[location] != null){
            Ingredient ingredient_at_location = this.curr_cave.getRoomIngredients()[location];
            String[] returned_list = new String[3];
            returned_list[0] = ingredient_at_location.getName();
            returned_list[1] = ingredient_at_location.getType().toString();
            returned_list[2] = ingredient_at_location.getRarity().toString();
            return returned_list;
        }

        return null;
    }

    public int getCurrPlayerPatience(){
        return this.player.getCurrPatience();
    }

    public int changeCurrHonor(int honor_change){
        return this.player.changeCurrHonor(honor_change);
    }

    public String getDinosaurImageByLocation(int location_index) {
        Dinosaur dinosaur_at_index = curr_cave.getRoomDinosaurs()[location_index];

        return switch (dinosaur_at_index.getDinosaurType()){
            case Common -> "file:src/main/resources/org/example/breadfest/Images/dino1.100x.GIF";
            case Rare -> "file:src/main/resources/org/example/breadfest/Images/dino2.100x.GIF";
            case Epic -> "file:src/main/resources/org/example/breadfest/Images/dino3.100x.GIF";
            default -> "file:src/main/resources/org/example/breadfest/Images/dino_button_image.png";
        };
    }

    public String getIngredientImageByLocation(int location_index){
        Ingredient ingredient_at_index = curr_cave.getRoomIngredients()[location_index];

        return switch (ingredient_at_index.getType()){
            case Flour -> "file:src/main/resources/org/example/breadfest/Images/flour_bag.gif";
            case Water -> "file:src/main/resources/org/example/breadfest/Images/water_bottle_test.gif";
            case Salt -> "file:src/main/resources/org/example/breadfest/Images/Flour-Transparent.png";
            case Yeast -> "file:src/main/resources/org/example/breadfest/Images/Flour-Transparent.png";
            case Topping -> "file:src/main/resources/org/example/breadfest/Images/Flour-Transparent.png";
            default -> "file:src/main/resources/org/example/breadfest/Images/Flour-Transparent.png";
        };
    }

    public int getMaxPlayerPatience(){
        return this.player.getBasePatience();
    }

    public String[] getFightersInformation(){
        return player.getFightersInformation();
    }

    public String[] getDieInformation(int die_index){
        return player.getActiveDieInventoryInformation(die_index);
    }

    public String[] fightDinosaur(int dice_rolled){
        return player.attackDinosaur(dice_rolled);
    }

    public boolean stopFight(boolean won_fight){
        return player.stopFight(won_fight);
    }

    public String getDinoImage() { return player.getDinoImage(); }

    public void updateActiveDice(int die_to_switch){
        player.solveDieMergeConflict(die_to_switch);
    }

    public void regenerateCaveSystem(){
        try {
            this.curr_cave = Cave.enterRoom0();
            this.cave0 = curr_cave;
        }
        catch (Exception e){
            System.out.println("Error " + e);
        }
    }

    public String[] getPreviousReward(){
        return player.getPreviousReward();
    }

}
