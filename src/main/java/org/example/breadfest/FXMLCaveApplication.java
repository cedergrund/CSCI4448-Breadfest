package org.example.breadfest;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Paths;


public class FXMLCaveApplication {

    private final FXMLCave cave_game_adaptor;
    private Stage stage;
    private MediaPlayer[] media_player;

    public FXMLCaveApplication(FXMLCave fxmlCave, Stage stage){
        this.cave_game_adaptor = fxmlCave;
        this.stage = stage;
        stage.setResizable(false);
        setUpMusic();
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

        stopAllSongs();

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

        startSong("cave");

        stage.show();

    }
    public void generateFightRoom() {

        stage = new FXMLStageBuilder(this, stage)
                .fightRoomSetup()
                .addFightMoves()
                .build();

        startSong("fight");

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
        stage = FXMLStageBuilder.popUpDieConflict(this, stage);
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

        startSong("baking");
    }

    public void generateInventory(String location_where_pressed){

        stage = new FXMLStageBuilder(this, stage)
                .setInventoryBackground()
                .addInventoryTable()
                .addReturnToGameButton(location_where_pressed)
                .build();

        stage.show();

        startSong("baking");
    }


    public FXMLCave getAdaptor(){
        return cave_game_adaptor;
    }

    private void setUpMusic(){
        this.media_player = new MediaPlayer[3];
        // cave: 0,
        Media cave_song = new Media(Paths.get("src/main/resources/org/example/breadfest/music/Exquisite_Corpse_Soundscape.mp3").toUri().toString());
        this.media_player[0] = new MediaPlayer(cave_song);
        media_player[0].setVolume(1);
        media_player[0].setOnEndOfMedia(() -> {
            media_player[0].seek(Duration.ZERO);
            media_player[0].play();
        });

        // fight: 1,
        Media fight_song = new Media(Paths.get("src/main/resources/org/example/breadfest/music/street_fighter.mp3").toUri().toString());
        this.media_player[1] = new MediaPlayer(fight_song);
        media_player[1].setVolume(0.4);
        media_player[1].setOnEndOfMedia(() -> {
            media_player[1].seek(Duration.ZERO);
            media_player[1].play();
        });

        // baking: 2,
        Media baking_song = new Media(Paths.get("src/main/resources/org/example/breadfest/music/Beethoven's_5th_Symphony.mp3").toUri().toString());
        this.media_player[2] = new MediaPlayer(baking_song);
        media_player[2].setVolume(0.4);
        media_player[2].setOnEndOfMedia(() -> {
            media_player[2].seek(Duration.ZERO);
            media_player[2].play();
        });
    }

    private void startSong(String place){
        int desired_song_index = getSongIndex(place);

        for (int song_index = 0; song_index < media_player.length; song_index++){
            if (song_index == desired_song_index){
                media_player[song_index].play();
            }
            else{
                media_player[song_index].stop();
            }
        }
    }

    private void stopAllSongs(){
        for (MediaPlayer mediaPlayer : media_player) {
            mediaPlayer.stop();
        }
    }

    private int getSongIndex(String song){
        return switch(song){
            case "cave" -> 0;
            case "fight" ->  1;
            case "baking" ->  2;
            default-> 3;
        };
    }

}