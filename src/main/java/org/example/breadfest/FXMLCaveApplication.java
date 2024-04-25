package org.example.breadfest;

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
                .addBillWithBakingSceneButton("Entrance")
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
    public void generateFightRoom() {

        stage = new FXMLStageBuilder(this, stage)
                .fightRoomSetup()
                .addFightMoves()
                .build();

        stage.show();

    }

    public void generateFightRoomResults(String[] results) {

        stage = new FXMLStageBuilder(this, stage)
                .fightRoomSetup()
                .addFightResults(results)
                .build();

        stage.show();


    }

    public void generateFightRoomRewards(boolean die_conflict){
        stage = FXMLStageBuilder.popUpFightResults(this, stage, die_conflict);
        stage.show();
    }

    public void generateDieSelectorForMergeConflict(){
        new FXMLStageBuilder(this, stage)
                .fightRoomSetup()
                .build();

        stage.show();
    }

    public void generateBakingScene(String location_where_pressed){
        stage = new FXMLStageBuilder(this, stage)
                .setBakingSceneBackground()
                .addBakingInventoryTable(location_where_pressed)
                .addCookingPot()
                .addReturnToGameButton(location_where_pressed)
                .addHonorMeter()
                .build();
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