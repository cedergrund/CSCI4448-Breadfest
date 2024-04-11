package org.example.breadfest;

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


    public void playerFightsDinosaur(Dinosaur dinosaur){
        this.player.fightDinosaur(dinosaur);
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
            this.player.fightDinosaur(this.curr_cave.getRoomDinosaurs()[location]);
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
}