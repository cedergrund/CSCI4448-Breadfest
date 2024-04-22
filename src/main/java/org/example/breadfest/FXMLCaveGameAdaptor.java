package org.example.breadfest;

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

    @Override
    public String[] getObjectByLocation(int location) {
        return this.adapted_game_state.getObjectByLocation(location);
    }

    @Override
    public String getImageByLocation(int location_index) {
        return this.adapted_game_state.getImageByLocation(location_index);
    }


    /**
     * @param location
     * @return boolean -> if true reload all things in room.
     */
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

    public boolean moveRoom(char direction){
        return this.adapted_game_state.moveRoom(direction);
    }

    public void enterRoom0(){
        this.adapted_game_state.enterRoom0();
    }

    public int getCurrPlayerPatience(){
        return adapted_game_state.getCurrPlayerPatience();
    }

    public int getMaxPlayerPatience() { return adapted_game_state.getMaxPlayerPatience(); }


    // TODO: fight scene
}
