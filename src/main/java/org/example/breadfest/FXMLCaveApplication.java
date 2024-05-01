package org.example.breadfest;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.util.List;


public class FXMLCaveApplication {

    private final FXMLCave cave_game_adaptor;
    private Stage stage;
    private MediaPlayer[] media_player;

    public FXMLCaveApplication(FXMLCave fxmlCave, Stage stage){
        this.cave_game_adaptor = fxmlCave;
        this.stage = stage;
        stage.setResizable(false);
        setUpMedia();
    }

    public void runGame(){
        this.generateCaveEntrance();
    }

    public void generateCaveEntrance(){

        stage = new FXMLStageBuilder(this, this.stage)
                .setCaveEntranceBackground()
                .addCaveEntranceButtonAndParticles()
                .addPlayerWithInventoryButton("Entrance")
                .addBakingSceneButton("Entrance")
                .build();

        startSong("entrance");

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
        popUps.popUpFightResults(this, stage, die_conflict);
        stage.show();
    }

    public void generateDieSelectorForMergeConflict(){
        popUps.popUpDieConflict(this, stage);
        stage.show();
    }

    public void nuclearIngredientUsed() {
        stopAllSongs();
        popUps.popUpNuclear(this, stage);
        stage.show();
    }

    public void maxUpgradeReached() {
        stopAllSongs();
        popUps.popUpGameWin(this, stage);
        stage.show();
    }

    public void popUpPatienceExhausted() {
        stopAllSongs();
        popUps.popUpPatienceExhausted(this, stage);
        stage.show();
    }

    public void generateBaseBaking(int upgrade){
        startSong("baking");

        stage = new FXMLStageBuilder(this, stage)
                .bakingSceneBackground()
                .bakingSceneSetup()
                .addHonorMeter()
                .build();
        stage.show();

        startSong("baking");

        popUps.popUpPlayerUpgrade(this, stage, upgrade);
        stage.show();
    }

    public void popUpBakingNextTable(String next_ingredient, List<String[]> baked_ingredients){
        popUps.popUpNextBakingIngredient(this, stage, next_ingredient, baked_ingredients);
        stage.show();
    }

    public void generateBreadResults(String[] bread_result){
        stage = new FXMLStageBuilder(this, stage)
                .bakingSceneBackground()
                .addHonorMeter()
                .removeOutsideButton()
                .build();
        stage.show();

        popUps.popUpBreadResult(this, stage, bread_result);
        stage.show();
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

    public void playExplodeVideo() {
        MediaView media_view = new MediaView(this.media_player[4]);
        this.media_player[4].setAutoPlay(true);
        AnchorPane root = (AnchorPane) stage.getScene().getRoot();
        root.getChildren().add(media_view);
        stage.setTitle("RawrDough Valley went boom :0");
        stage.show();
    }

    public void playCreditsScreen() {

        stage = new FXMLStageBuilder(this, stage)
                .generateGameWinScene()
                .build();

        stage.setTitle("You did it!");

        AnchorPane root = (AnchorPane) stage.getScene().getRoot();

        for (int video_index = 5; video_index < 9; video_index++){
            this.media_player[video_index].setAutoPlay(true);
            this.media_player[video_index].setVolume(0.7);
            MediaView video = getMediaView(this.media_player[video_index], video_index);
            root.getChildren().add(video);
        }
        stage.show();
    }

    private MediaView getMediaView(MediaPlayer raw_video, int video_index) {
        MediaView video = new MediaView(raw_video);
        video.setFitHeight(100);
        double x;
        double y;
        switch (video_index){
            case 5:{
                x = 1171;
                y = 27;
                break;
            }
            case 6:{
                x = 962;
                y = 21;
                break;
            }
            case 7:{
                x = 759;
                y = 34;
                break;
            }
            default:{
                x = 558;
                y = 18;
            }
        }
        video.setLayoutX(x);
        video.setLayoutY(y);
        return video;
    }

    private void setUpMedia(){
        this.media_player = new MediaPlayer[9];
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
        media_player[1].setVolume(0.35);
        media_player[1].setOnEndOfMedia(() -> {
            media_player[1].seek(Duration.ZERO);
            media_player[1].play();
        });

        // baking: 2,
        Media baking_song = new Media(Paths.get("src/main/resources/org/example/breadfest/music/baking_music.mp3").toUri().toString());
        this.media_player[2] = new MediaPlayer(baking_song);
        media_player[2].setVolume(0.35);
        media_player[2].setOnEndOfMedia(() -> {
            media_player[2].seek(Duration.ZERO);
            media_player[2].play();
        });

        // cave entrance: 3,
        Media cave_entrance_song = new Media(Paths.get("src/main/resources/org/example/breadfest/music/on_my_way.mp3").toUri().toString());
        this.media_player[3] = new MediaPlayer(cave_entrance_song);
        media_player[3].setVolume(0.35);
        media_player[3].setOnEndOfMedia(() -> {
            media_player[3].seek(Duration.ZERO);
            media_player[3].play();
        });

        // explosion video: 4,
        Media explosion_video = new Media(Paths.get("src/main/resources/org/example/breadfest/videos/game_over_video.mp4").toUri().toString());
        this.media_player[4] = new MediaPlayer(explosion_video);

        // victory videos: 5-8
        this.media_player[5] = new MediaPlayer(new Media(Paths.get("src/main/resources/org/example/breadfest/videos/dancing.mp4").toUri().toString()));
        this.media_player[6] = new MediaPlayer(new Media(Paths.get("src/main/resources/org/example/breadfest/videos/dancing1.mp4").toUri().toString()));
        this.media_player[7] = new MediaPlayer(new Media(Paths.get("src/main/resources/org/example/breadfest/videos/dancing2.mp4").toUri().toString()));
        this.media_player[8] = new MediaPlayer(new Media(Paths.get("src/main/resources/org/example/breadfest/videos/dancing3.mp4").toUri().toString()));

    }

    private void startSong(String place){
        int desired_song_index = getSongIndex(place);

        for (int song_index = 0; song_index < media_player.length; song_index++){
            if (song_index == desired_song_index){
                media_player[song_index].play();
            }
            else{
                if (song_index == 3){
                    media_player[song_index].stop();
                }
                else {
                    media_player[song_index].pause();
                }
            }
        }
    }

    private void stopAllSongs(){
        for (MediaPlayer mediaPlayer : media_player) {
            mediaPlayer.pause();
        }
        media_player[3].stop();
    }

    private int getSongIndex(String song) {
        return switch (song) {
            case "cave" -> 0;
            case "fight" -> 1;
            case "baking" -> 2;
            case "entrance" -> 3;
            default -> 4;
        };
    }
}