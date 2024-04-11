package org.example.breadfest;

import javafx.application.Application;
import javafx.stage.Stage;



public class FXMLCaveApplication {

    private final FXMLCave cave_game_adaptor;
    private Stage stage;

    public FXMLCaveApplication(FXMLCave fxmlCave, Stage stage){
        this.cave_game_adaptor = fxmlCave;
        this.stage = stage;
        stage.setResizable(false);
    }

    public void runGame(){
        this.generateCaveEntrance();
    }

    public void generateCaveEntrance(){

        stage = new FXMLStageBuilder(this, this.stage)
                .setBackgroundGreen()
                .addWelcomeToBreadfestLabel()
                .addCaveEntranceButtonsAndImages()
                .addPlayerWithInventoryButton("Entrance")
                .build();

        stage.show();
    }

    public void generateCaveRoom(){

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

    public void generateInventory(String location_where_pressed){

        stage = new FXMLStageBuilder(this, stage)
                .setInventoryBackground()
                .addInventoryTable()
                .addReturnToGameButton(location_where_pressed)
                .build();

        stage.show();
    }


    public FXMLCave getAdaptor(){
        return cave_game_adaptor;
    }
}