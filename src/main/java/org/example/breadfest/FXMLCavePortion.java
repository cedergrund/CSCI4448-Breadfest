package org.example.breadfest;

import java.util.List;

public interface FXMLCavePortion {

    String getBackgroundImage();

    List<Character> getRoomExitDirections();

    List<String> getObjectsByLocation();


    boolean clickLocation(int location);

    List<String[]> getIngredientInventory();

}

