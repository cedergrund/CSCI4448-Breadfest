package org.example.breadfest;

import java.util.ArrayList;
import java.util.List;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;

public class CaveGameAdaptor implements FXMLCavePortion {

    private final CaveExplorationPortionGame adapted_game_state;

    public CaveGameAdaptor(CaveExplorationPortionGame game_state) {
        this.adapted_game_state = game_state;
    }

    public String getBackgroundImage(){
        return this.adapted_game_state.getBackgroundImage();
    }

    public List<Character> getRoomExitDirections(){
        return this.adapted_game_state.getRoomExitDirections();
    }

    public List<String> getObjectsByLocation(){

        return this.adapted_game_state.getObjectsByLocation();
    }


    /**
     * @param location
     * @return boolean -> if true reload all things in room.
     */
    public boolean clickLocation(int location){

        return adapted_game_state.clickLocation(location);

    }

    /**
     * @return -> List of string arrays of length 3, 1 for each ingredient in inventory:
     *      [index 0 is ingredient name, index 1 is ingredient type, index 2 is ingredient rarity]
     */
    public List<String[]> getIngredientInventory(){
        return this.adapted_game_state.getIngredientInventory();
    }

    public boolean moveRoom(char direction){
        return this.adapted_game_state.moveRoom(direction);
    }


    // TODO: fight scene
}
