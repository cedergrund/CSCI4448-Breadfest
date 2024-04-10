package org.example.breadfest;
import java.util.List;



public class FXMLBuilder {
    private StringBuilder FXMLContent;

    public FXMLBuilder() {
        this.FXMLContent = new StringBuilder();
    }

    public void beginFXMLContent() {
        this.FXMLContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        this.FXMLContent.append("<?import javafx.scene.control.Button?>\n");
        this.FXMLContent.append("<?import javafx.scene.control.Label?>\n");
        this.FXMLContent.append("<?import javafx.scene.layout.AnchorPane?>\n");
        this.FXMLContent.append("<?import javafx.scene.text.Font?>\n");
        this.FXMLContent.append("<?import java.lang.Double?>\n");
        // all imports are handled above this line

        //this next section builds the anchor pane with its dimensions
        this.FXMLContent.append("<AnchorPane xmlns=\"http://javafx.com/javafx\" xmlns:fx=\"http://javafx.com/fxml\" fx:controller=\"org.example.breadfest.RoomController\" minWidth=\"1700\" minHeight=\"1300\">\n\n");
        this.FXMLContent.append("<children>\n");
    }

    public void addReturnHomeButton() {
        this.FXMLContent.append("<Button text=\"Return Home\" AnchorPane.topAnchor=\"20\" AnchorPane.rightAnchor=\"20\" onAction=\"#return_home\"/>\n");
    }

    public void addRoomButtons(List<Character> direction_list) {
        if (direction_list != null) { // check if the list is null
            for (char direction : direction_list) { // loop through every direction
                switch (direction) { // add the button for each direction
                    case 'N':
                        // Add North button
                        this.FXMLContent.append("<Button text=\"North\" AnchorPane.topAnchor=\"20\" AnchorPane.leftAnchor=\"700\" AnchorPane.rightAnchor=\"700\"/>\n");
                        break;
                    case 'S':
                        this.FXMLContent.append("<Button text=\"South\" AnchorPane.bottomAnchor=\"20\" AnchorPane.leftAnchor=\"700\" AnchorPane.rightAnchor=\"700\"/>\n");
                        break;
                    case 'E':
                        this.FXMLContent.append("<Button text=\"East\" AnchorPane.topAnchor=\"300\" AnchorPane.rightAnchor=\"20\" AnchorPane.bottomAnchor=\"300\"/>\n");
                        break;
                    case 'W':
                        this.FXMLContent.append("<Button text=\"West\" AnchorPane.topAnchor=\"300\" AnchorPane.leftAnchor=\"20\" AnchorPane.bottomAnchor=\"300\"/>\n");
                        break;
                    default:
                        // some sort of error here, we didn't get a valid input!
                        break;
                }
            }
        }
    }

    public void addRoomName(String name) {
        this.FXMLContent.append("<Label layoutX=\"200.0\" layoutY=\"200.0\" prefHeight=\"200.0\" prefWidth=\"200.0\" text=\"" + name + "\" AnchorPane.leftAnchor=\"270.0\" AnchorPane.rightAnchor=\"270.0\" AnchorPane.topAnchor=\"90.0\" AnchorPane.bottomAnchor=\"90.0\">\n");
        this.FXMLContent.append("<font>\n");
        this.FXMLContent.append("<Font size=\"48.0\" />\n");
        this.FXMLContent.append("</font>\n");
        this.FXMLContent.append("</Label>\n");
    }

    public void endFXMLContent() {
        this.FXMLContent.append("</children>\n");
        this.FXMLContent.append("</AnchorPane>");
    }

    public StringBuilder generateFXMLContent(List<Character> room_directions) {
        this.beginFXMLContent();
        this.addRoomButtons(room_directions);
        this.addReturnHomeButton();
        this.addRoomName("Testing Name");
        this.endFXMLContent();
        return this.FXMLContent;
    }
}
