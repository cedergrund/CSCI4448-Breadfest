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

    public void generateFightRoomResults(int player_roll, int dinosaur_roll, int result) {

        stage = new FXMLStageBuilder(this,stage)
                .setCaveBackground()
                .addPatienceMeter()
                .addPlayerWithInventoryButton("Cave")
                .addCaveExits()
                .addCaveObjects()
                .addReturnHomeButton()
                .build();

        stage.show();

        try {
            Thread.sleep(3 * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        switch (result){
            case 0:{
                generateFightRoom();
                break;
            }
            case 1: {
                // add popup saying player died
                try {
                    Thread.sleep(5 * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                generateCaveEntrance();
                break;
            }
            case 2: {
                // add popup saying dino died
                try {
                    Thread.sleep(5 * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }

                if (cave_game_adaptor.dinosaurBeaten()){
                    // remove dino died popup
                    // add change die popup

                }
                else {
                    generateCaveRoom();
                }
                break;
            }
        }

    }

    public void generateBakingScene(String location_where_pressed){
        stage = new FXMLStageBuilder(this, stage)
                .setBakingSceneBackground()
                .addBakingInventoryTable()
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