package org.example.breadfest;

import java.util.List;

public interface FXMLCavePortion {

    String getBackgroundImage();

    List<Character> getRoomExitDirections();

    List<String> getObjectsAtAllLocations();

    String[] getObjectByLocation(int location);

    boolean clickLocation(int location);

    boolean moveRoom(char direction);

}

