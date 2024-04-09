package org.example.breadfest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;

public class RoomAdaptor implements FXMLRoom {
    private Room adapted_room;

    public RoomAdaptor(Room adapted_room) {
        this.adapted_room = adapted_room;
    }

    public String getBackgroundImage(){
        return this.adapted_room.getBackgroundImage();
    }




//    // Method to load FXML content dynamically for the room
//    public void loadRoomFXML() {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Room.fxml"));
//        fxmlLoader.setControllerFactory(controllerClass -> new RoomController(room));
//        try {
//            Parent root = fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Room " + room.getRoomNumber()); // hypothetical room number function? Perhaps room name? Talk to Gus about this
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to generate FXML content dynamically for the room
//    public String generateFXMLContent() {
//        String fxmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
//        fxmlContent += "<AnchorPane maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"2000.0\" prefWidth=\"2000.0\" xmlns=\"http://javafx.com/javafx/21\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"org.example.breadfest.RoomController\">\n";
//        fxmlContent += "<children>\n";
//        fxmlContent += "<Label layoutX=\"903.0\" layoutY=\"832.0\" prefHeight=\"167.0\" prefWidth=\"170.0\" text=\"" + room.getRoomNumber() + "\">\n";
//        fxmlContent += "<font>\n";
//        fxmlContent += "<Font size=\"48.0\" />\n";
//        fxmlContent += "</font>\n";
//        fxmlContent += "</Label>\n";
//        List<Room> neighboringRooms = room.getNeighboringRooms();
//        if(this.existNorthNeighbor())
//        fxmlContent += generateButton("Move North", neighboringRooms.contains(room.getNorthNeighbor()));
//        fxmlContent += generateButton("Move South", neighboringRooms.contains(room.getSouthNeighbor()));
//        fxmlContent += generateButton("Move West", neighboringRooms.contains(room.getWestNeighbor()));
//        fxmlContent += generateButton("Move East", neighboringRooms.contains(room.getEastNeighbor()));
//        fxmlContent += "</children>\n";
//        fxmlContent += "</AnchorPane>";
//        return fxmlContent;
//    }

//    // Method to generate button XML for each direction
//    private String generateButton(String direction, boolean enabled) {
//        String buttonXML = "<Button layoutX=\"875.0\" layoutY=\"103.0\" mnemonicParsing=\"false\" prefHeight=\"80.0\" prefWidth=\"189.0\" text=\"" + direction + "\"";
//        if (!enabled) {
//            buttonXML += " disabled=\"true\"";
//        }
//        buttonXML += ">\n";
//        buttonXML += "<font>\n";
//        buttonXML += "<Font size=\"24.0\" />\n";
//        buttonXML += "</font>\n";
//        buttonXML += "</Button>\n";
//        return buttonXML;
//    }
//
    public boolean existNorthNeighbor(){
        return this.adapted_room.getRoomNeighbor('N') != null;
    }
    public boolean existSouthNeighbor(){
        return this.adapted_room.getRoomNeighbor('S') != null;
    }
    public boolean existEastNeighbor(){
        return this.adapted_room.getRoomNeighbor('E') != null;
    }
    public boolean existWestNeighbor(){
        return this.adapted_room.getRoomNeighbor('W') != null;
    }
}
