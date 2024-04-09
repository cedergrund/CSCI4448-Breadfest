package org.example.breadfest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;

public class RoomAdaptor {
    private Room room;

    public RoomAdaptor(Room room) {
        this.room = room;
    }
    public boolean NorthNeighborexists(){
        return this.room.getRoomNeighbor('N') != null;
    }
    public boolean SouthNeighborexists(){
        return this.room.getRoomNeighbor('S') != null;
    }
    public boolean EastNeighborexists(){
        return this.room.getRoomNeighbor('E') != null;
    }
    public boolean WestNeighborexists(){
        return this.room.getRoomNeighbor('W') != null;
    }
}
