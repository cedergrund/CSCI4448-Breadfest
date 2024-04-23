package org.example.breadfest;

import java.util.List;

public interface FXMLCave {

    String getBackgroundImage();

    List<Character> getRoomExitDirections();

    List<String> getObjectsAtAllLocations();

    String[] getObjectByLocation(int location);

    String getImageByLocation(int locationIndex);

    void clickLocation(int location);

    boolean moveRoom(char direction);

    List<String[]> getIngredientInventory();

    int getCurrPlayerPatience();

    void enterRoom0();

    int getMaxPlayerPatience();

    int[] fightDinosaur(int dice_rolled);

    void regenerateCaveSystem();
    boolean dinosaurBeaten();

    void updateActiveDice(int die_to_switch);

    String[] getFightersInformation();

    String[][] getDieInformation();
}

