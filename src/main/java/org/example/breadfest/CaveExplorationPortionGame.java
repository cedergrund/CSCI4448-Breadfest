package org.example.breadfest;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CaveExplorationPortionGame {

    private final Player player;
    private Room curr_room;

    CaveExplorationPortionGame(Player player){
        this.player = player;
        try {
            this.curr_room = Room.enterRoom0();
        }
        catch (Exception e){
            System.out.println("Error " + e);
        }
    }

    public void playerFightsDinosaur(Dinosaur dinosaur){
        this.player.fightDinosaur(dinosaur);
    }

    FXMLCavePortion getRoom(){
        return new CaveGameAdaptor(this);
    }

    public String getBackgroundImage() {
        return this.curr_room.getBackgroundImage();
    }

    public void playerCollectsIngredient(Ingredient ingredient, int location) {
        this.player.addIngredientToInventory(ingredient);
        this.curr_room.removeObjectFromLocation(location);
    }

    public List<Character> getRoomExitDirections(){
        List<Character> room_exit_directions = new ArrayList<>();

        if (this.curr_room.getRoomNeighbor('N') != null){
            room_exit_directions.add('N');
        }
        if (this.curr_room.getRoomNeighbor('W') != null){
            room_exit_directions.add('W');
        }
        if (this.curr_room.getRoomNeighbor('E') != null){
            room_exit_directions.add('E');
        }
        if (this.curr_room.getRoomNeighbor('S') != null){
            room_exit_directions.add('S');
        }

        return room_exit_directions;

    }

    public List<String> getObjectsByLocation(){

        Dinosaur[] dinosaur_locations = this.curr_room.getRoomDinosaurs();
        Ingredient[] ingredient_locations = this.curr_room.getRoomIngredients();

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

    public boolean clickLocation(int location){

        if (this.curr_room.getRoomDinosaurs()[location] != null){
            this.player.fightDinosaur(this.curr_room.getRoomDinosaurs()[location]);
            return true;
        }
        else if (this.curr_room.getRoomIngredients()[location] != null){
            this.player.addIngredientToInventory(this.curr_room.getRoomIngredients()[location]);
            return true;
        }

        return true;

    }

    public List<String[]> getIngredientInventory(){
        List<String[]> ingredient_inventory = new ArrayList<>();
        for (Ingredient curr_ingredient : this.player.getIngredientInventory()){
            String[] curr_ingredient_information = new String[3];
            curr_ingredient_information[0] = curr_ingredient.getName();
            curr_ingredient_information[1] = curr_ingredient.getType().toString();
            curr_ingredient_information[2] = curr_ingredient.getRarity().toString();
            ingredient_inventory.add(curr_ingredient_information);
        }
        return ingredient_inventory;
    }
}
