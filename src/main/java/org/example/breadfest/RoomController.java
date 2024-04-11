package org.example.breadfest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomController {
    @FXML

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void move_north(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dynamic_room_test.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void return_home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home_room.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void move_south(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("room3_template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void move_east(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("room4_template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void move_west(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("room2_template.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void open_inventory(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("inventory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void return_to_room(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dynamic_room_test.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}