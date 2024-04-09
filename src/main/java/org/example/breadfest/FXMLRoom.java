package org.example.breadfest;

public interface FXMLRoom {

    String getBackgroundImage();

    boolean existNorthNeighbor();
    boolean existSouthNeighbor();
    boolean existEastNeighbor();
    boolean existWestNeighbor();
}
