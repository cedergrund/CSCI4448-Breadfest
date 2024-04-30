package org.example.breadfest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public List<String[]> getIngredientInventory(String type){
        return player.getIngredientInventory(type);
    }

    boolean isValidIngredientList(List<String> ingredient_type_list){
        // return 0 if no
        // returns 1 if yes

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

    public String[] bakeIngredientsFromTable(List<String[]> baked_ingredients){
        // returns an array of Strings of length 3
        // index 0 is upgrade ->
        //          -1 if nuclear ingredient baked
        //          0 if bake failed
        //          1 if player didn't upgrade
        //          x with player's new level
        // index 1 is how much honor was increased
        // index 2 is the name of the baked bread

        //Lets do it!

        String[] returned_array = new String[3];

        int cumulative_score = 0; // this will be our total score of all baked ingredients
        List<String> ingredient_type_list = new ArrayList<>();

        // iterate through ingredients that were baked with
        for (String[] ingredient : baked_ingredients) {

            Ingredient baked_ingredient = player.removeIngredientFromInventory(ingredient[1]);
            if (baked_ingredient.getRarity() == IngredientRarity.Nuclear){
                returned_array[0] = String.valueOf(-1);
                return returned_array;
            }
            int ingredient_score = baked_ingredient.getScore();
            cumulative_score += ingredient_score;

            ingredient_type_list.add(ingredient[2]); // this collects all types of selected ingredients
        }

        // check if bake is valid and then change honor
        boolean valid_ingredient = this.isValidIngredientList(ingredient_type_list);

        if (valid_ingredient){
            returned_array[0] = String.valueOf(player.changeCurrHonor(cumulative_score));
            returned_array[1] = String.valueOf(cumulative_score);

            Random random = new Random();
            int index1 = random.nextInt(baked_ingredients.size());
            int index2 = random.nextInt(baked_ingredients.size());
            while (index2 == index1) {
                index2 = random.nextInt(baked_ingredients.size());
            }
            returned_array[2] = "\"" +baked_ingredients.get(index1)[1] + " & " + baked_ingredients.get(index2)[1] + "\" Bread";
        }
        else {
            returned_array[0] = String.valueOf(0);
            returned_array[1] = String.valueOf(0);
            returned_array[2] = "Dust";
        }
        return returned_array;
    }

    public Ingredient removeIngredientFromInventory(String ingredient_name){
        return player.removeIngredientFromInventory(ingredient_name);
    }

    public boolean moveRoom(char direction){
        try {
            this.curr_cave = this.curr_cave.move(direction);
            if (this.player.changeCurrPatience(-20)){
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

    public int getCurrPlayerHonor(){
        return player.getCurrHonor();
    }

    public String getDinosaurImageByLocation(int location_index) {
        Dinosaur dinosaur_at_index = curr_cave.getRoomDinosaurs()[location_index];

        return switch (dinosaur_at_index.getDinosaurType()){
            case Common -> "file:src/main/resources/org/example/breadfest/images/dino1.100x.GIF";
            case Rare -> "file:src/main/resources/org/example/breadfest/images/dino2.100x.GIF";
            case Epic -> "file:src/main/resources/org/example/breadfest/images/dino3.100x.GIF";
            default -> "file:src/main/resources/org/example/breadfest/images/dino_button_image.png";
        };
    }

    public String getIngredientImageByLocation(int location_index){
        Ingredient ingredient_at_index = curr_cave.getRoomIngredients()[location_index];

        return getIngredientImageByString(ingredient_at_index.getType().toString());
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

    public String getIngredientImageByString(String type) {
        return switch (type){
            case "Flour" -> "file:src/main/resources/org/example/breadfest/images/flour_display.gif";
            case "Water" -> "file:src/main/resources/org/example/breadfest/images/water_display.gif";
            case "Salt" -> "file:src/main/resources/org/example/breadfest/images/salt_display.gif";
            case "Yeast" -> "file:src/main/resources/org/example/breadfest/images/yeast_display.gif";
            case "Topping" -> "file:src/main/resources/org/example/breadfest/images/topping_display.gif";
            default -> "file:src/main/resources/org/example/breadfest/images/Flour-Transparent.png";
        };
    }

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
