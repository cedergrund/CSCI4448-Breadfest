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
    // Method to load FXML content dynamically for the room
    public void loadRoomFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Room.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> new RoomController(room));
        try {
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Room " + room.getRoomNumber()); // hypothetical room number function? Perhaps room name? Talk to Gus about this
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to generate FXML content dynamically for the room
    public String generateFXMLContent() {
        String fxmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        fxmlContent += "<AnchorPane maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"2000.0\" prefWidth=\"2000.0\" xmlns=\"http://javafx.com/javafx/21\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"org.example.breadfest.RoomController\">\n";
        fxmlContent += "<children>\n";
        fxmlContent += "<Label layoutX=\"903.0\" layoutY=\"832.0\" prefHeight=\"167.0\" prefWidth=\"170.0\" text=\"" + room.getRoomNumber() + "\">\n";
        fxmlContent += "<font>\n";
        fxmlContent += "<Font size=\"48.0\" />\n";
        fxmlContent += "</font>\n";
        fxmlContent += "</Label>\n";
        List<Room> neighboringRooms = room.getNeighboringRooms();
        fxmlContent += generateButton("Move North", neighboringRooms.contains(room.getNorthNeighbor()));
        fxmlContent += generateButton("Move South", neighboringRooms.contains(room.getSouthNeighbor()));
        fxmlContent += generateButton("Move West", neighboringRooms.contains(room.getWestNeighbor()));
        fxmlContent += generateButton("Move East", neighboringRooms.contains(room.getEastNeighbor()));
        fxmlContent += "</children>\n";
        fxmlContent += "</AnchorPane>";
        return fxmlContent;
    }

    // Method to generate button XML for each direction
    private String generateButton(String direction, boolean enabled) {
        String buttonXML = "<Button layoutX=\"875.0\" layoutY=\"103.0\" mnemonicParsing=\"false\" prefHeight=\"80.0\" prefWidth=\"189.0\" text=\"" + direction + "\"";
        if (!enabled) {
            buttonXML += " disabled=\"true\"";
        }
        buttonXML += ">\n";
        buttonXML += "<font>\n";
        buttonXML += "<Font size=\"24.0\" />\n";
        buttonXML += "</font>\n";
        buttonXML += "</Button>\n";
        return buttonXML;
    }
}
