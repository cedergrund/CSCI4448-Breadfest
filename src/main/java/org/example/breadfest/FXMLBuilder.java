//package org.example.breadfest;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
//public class FXMLBuilder {
//
//    private Room room;
//
//    public FXMLBuilder(Room room) {
//        this.room = room;
//    }
//
//    public static void writeToFxmlFile(String filePath, String content) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            writer.write(content);
//        }
//    }
//
//    public void generateFXMLFile(String fileName) throws IOException {
//        // Generate FXML content
//        String fxmlContent = generateFXMLContent();
//
//        // Write the content to a file
//        try (FileWriter writer = new FileWriter(fileName)) {
//            writer.write(fxmlContent);
//        }
//    }
//
//    public String generateFXMLContent() {
//        StringBuilder fxmlContent = new StringBuilder();
//        fxmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//        fxmlContent.append("<AnchorPane maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"2000.0\" prefWidth=\"2000.0\" xmlns=\"http://javafx.com/javafx/21\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"org.example.breadfest.RoomController\">\n\n");
//        fxmlContent.append("<children>\n");
//        fxmlContent.append("<Label layoutX=\"903.0\" layoutY=\"832.0\" prefHeight=\"167.0\" prefWidth=\"170.0\" text=\"TESTINGROOM!\">\n");
//        fxmlContent.append("<font>\n");
//        fxmlContent.append("<Font size=\"48.0\" />\n");
//        fxmlContent.append("</font>\n");
//        fxmlContent.append("</Label>\n");
//
////        Boolean has_neighbors = room.areThereNeighbors();
////        if (has_neighbors != null) {
////            List<Character> directions = List.of('N', 'S', 'E', 'W');
////            for (char direction : directions) {
////
////                Room neighbor = room.getRoomNeighbor(direction);
////                if (neighbor != null) {
////
////                    if (true) {
////                        // build the north button at the correct location with correct label and correct onAction
////                        fxmlContent.append("<Button layoutX=\"875.0\" layoutY=\"103.0\" mnemonicParsing=\"false\" prefHeight=\"80.0\" prefWidth=\"189.0\" text=\"Move North\">\n");
////                        fxmlContent.append("</Button>\n");
////
////                    }
////                    if (direction == 'S') {
////                        // build the north button at the correct location with correct label and correct onAction
////                        fxmlContent.append("<Button layoutX=\"900.0\" layoutY=\"1800.0\" mnemonicParsing=\"false\" prefHeight=\"80.0\" prefWidth=\"201.0\" text=\"Move South\">\n");
////                        fxmlContent.append("</Button>\n");
////
////                    }
////                    if (direction == 'E') {
////                        // build the north button at the correct location with correct label and correct onAction
////                        fxmlContent.append("\n");
////                        fxmlContent.append("</Button>\n");
////
////                    }
////                    if (direction == 'W') {
////                        // build the north button at the correct location with correct label and correct onAction
////                        fxmlContent.append("\n");
////                        fxmlContent.append("</Button>");
////                    }
////
////                    fxmlContent.append("<Button layoutX=\"10.0\" layoutY=\"");
////                    fxmlContent.append(direction == 'N' ? 40 : direction == 'S' ? 70 : 55); // Adjust Y position
////                    fxmlContent.append("\" text=\"Move ");
////                    fxmlContent.append(direction == 'N' ? "North" : direction == 'S' ? "South" : direction == 'E' ? "East" : "West");
////                    fxmlContent.append("\"/>\n");
////                }
////            }
////        }
//
//
//        fxmlContent.append("</children>\n");
//        fxmlContent.append("</AnchorPane>");
//        return fxmlContent.toString();
//    }
//}