package org.example.breadfest;

import java.util.List;

public interface FXMLCave {

    String getBackgroundImage();

    List<Character> getRoomExitDirections();

    List<String> getObjectsAtAllLocations();

    String[] getObjectByLocation(int location);

    void clickLocation(int location);

    boolean moveRoom(char direction);

}

