package org.example.breadfest;

import javafx.application.Application;
import javafx.stage.Stage;



public class FXMLCaveApplication extends Application {

    private final FXMLCaveGameAdaptor cave_game_adaptor = new FXMLCaveGameAdaptor(new CaveGame());

    public void generateCaveEntrance(Stage stage){

        stage = new FXMLStageBuilder(this, stage)
                .setBackgroundGreen()
                .addWelcomeToBreadfestLabel()
                .addCaveEntranceImages()
                .addPlayerWithInventoryButton("Entrance")
                .addCave0Entrance()
                .build();

        stage.show();
    }

    public void generateCaveRoom(Stage stage){

        stage = new FXMLStageBuilder(this,stage)
                .setCaveBackground()
                .addPatienceMeter()
                .addPlayerWithInventoryButton("Cave")
                .addCaveExits()
                .addCaveObjects()
                .addReturnHomeButton()
                .build();

        stage.show();
    }

    public void generateInventory(Stage stage, String location_where_pressed){

        stage = new FXMLStageBuilder(this, stage)
                .setInventoryBackground()
                .addInventoryTable()
                .addReturnToGameButton(location_where_pressed)
                .build();

        stage.show();
    }



    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // initial conditions on stage
        stage.setResizable(false);

        // load cave entrance
        generateCaveEntrance(stage);
    }

    public FXMLCaveGameAdaptor getAdaptor(){
        return cave_game_adaptor;
    }
}