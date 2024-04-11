//package org.example.breadfest;
//
//import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Group;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Control;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Objects;
//
//public class GameController {
//    @FXML
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//
//
////    private final CaveGameAdaptor cave_game;
//
////    public GameController(Stage stage, CaveGameAdaptor curr_adaptor){
////        this.stage = stage;
////        this.scene = stage.getScene();
////        this.root = scene.getRoot();
////        this.cave_game = curr_adaptor;
////    }
//
//    public void moveRoom(Event event) throws IOException {
//        char direction = ((Control)event.getSource()).getId().charAt(0);
//        if (!RoomApplication.getAdaptor().moveRoom(direction)){
//            System.out.println("moving not successful");
//        }
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dynamic_room_test.fxml")));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }
//
//
//    public void enterMaze(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("dynamic_room_test.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void moveRoom(ActionEvent event) throws IOException {
//
//        char direction = ((Control)event.getSource()).getId().charAt(0);
//        if (!RoomApplication.getAdaptor().moveRoom(direction)){
//            System.out.println("moving not successful");
//        }
//
//        reloadRoom(RoomApplication.getAdaptor());
//
//        RoomApplication reloaded_app = new RoomApplication();
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        reloaded_app.populateAndLoadIntoDynamicRoom(stage);
////
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dynamic_room_test.fxml")));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private void reloadRoom(CaveGameAdaptor adapted_game){
//        Group group = new Group();
//        Scene new_scene = new Scene(group);
//    }
//
//    public void return_home(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("home_room.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//    public void move_south(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("room3_template.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void move_east(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("room4_template.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void move_west(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("room2_template.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void open_inventory(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void return_to_room(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("dynamic_room_test.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//}