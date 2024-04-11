package org.example.breadfest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javafx.scene.control.Button;

import java.io.IOException;

public class RoomApplication extends Application {

    private static final CaveGameAdaptor cave_game_adaptor = new CaveGameAdaptor(new CaveExplorationPortionGame());

    public void generateCaveEntrance(Stage stage){
        // Create an AnchorPane as the root
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #2E8B57;");

        // Create label
        Label welcomeLabel = new Label("Welcome to Breadfest!");
        welcomeLabel.setFont(new Font(32));
        AnchorPane.setLeftAnchor(welcomeLabel, 80.0);
        AnchorPane.setTopAnchor(welcomeLabel, 20.0);

        // Create image view for dinosaur sample
        ImageView dinosaur_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/dinosaur_sample.jpeg"));
        AnchorPane.setBottomAnchor(dinosaur_image_view, 20.0);
        AnchorPane.setRightAnchor(dinosaur_image_view, 20.0);

        // Create button with lego player image
        Button inventory_button = new Button();
        inventory_button.setLayoutX(621);
        inventory_button.setLayoutY(288);
        inventory_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/lego_player.png"));
        inventory_button.setGraphic(legoPlayerImageView);
        inventory_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openInventoryFromCaveEntrance(stage);
            }
        });

        // Create image view for cave entrance
        ImageView caveEntranceImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/cave_entrance.jpeg"));
        AnchorPane.setTopAnchor(caveEntranceImageView, 80.0);
        AnchorPane.setRightAnchor(caveEntranceImageView, 200.0);

        // Create button to enter cave
        Button enterCaveButton = new Button("Enter Cave!");
        AnchorPane.setTopAnchor(enterCaveButton, 20.0);
        AnchorPane.setLeftAnchor(enterCaveButton, 630.0);
        AnchorPane.setRightAnchor(enterCaveButton, 630.0);
        enterCaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterMaze(event, stage);
            }
        });

//         Add nodes to root
        root.getChildren().addAll(welcomeLabel, dinosaur_image_view, inventory_button, caveEntranceImageView, enterCaveButton);

        // Create the scene
        Scene scene = new Scene(root, 1366, 768);

        // Set the scene to the stage
        stage.setScene(scene);
        stage.setTitle("The Quest for Breadfest");
        stage.show();
    }

    public void generateCaveRoom(Stage stage){

        // Create an AnchorPane as the root
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #808080;");

        List<Character> direction_list = cave_game_adaptor.getRoomExitDirections();
        System.out.println(direction_list);
//        System.out.println(cave_game_adaptor.getRoomExitDirections());
        // check if the list is null
        for (char direction : direction_list) { // loop through every direction
            Button new_button = null;
            switch (direction) { // add the button for each direction
                case 'N':
                    // Add North button
                    new_button = new Button("North");
                    new_button.setId("N");
                    AnchorPane.setTopAnchor(new_button, 20.0);
                    AnchorPane.setLeftAnchor(new_button, 630.0);
                    AnchorPane.setRightAnchor(new_button, 630.0);
                    break;
                case 'S':
                    new_button = new Button("South");
                    new_button.setId("S");
                    AnchorPane.setBottomAnchor(new_button, 20.0);
                    AnchorPane.setLeftAnchor(new_button, 630.0);
                    AnchorPane.setRightAnchor(new_button, 630.0);
                    break;
                case 'E':
                    new_button = new Button("East");
                    new_button.setId("E");
                    AnchorPane.setTopAnchor(new_button, 300.0);
                    AnchorPane.setRightAnchor(new_button, 20.0);
                    AnchorPane.setBottomAnchor(new_button, 300.0);
                    break;
                case 'W':
                    new_button = new Button("West");
                    new_button.setId("W");
                    AnchorPane.setTopAnchor(new_button, 300.0);
                    AnchorPane.setLeftAnchor(new_button, 20.0);
                    AnchorPane.setBottomAnchor(new_button, 300.0);
                    break;
                default:
                    // some sort of error here, we didn't get a valid input!
                    break;
            }
            assert new_button != null;
            new_button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    moveRoom(event, stage);
                }
            });
            root.getChildren().add(new_button);

        }

        List<String> cave_object_locations = cave_game_adaptor.getObjectsAtAllLocations();
//        System.out.println(cave_game_adaptor.getRoomExitDirections());
        // check if the list is null

        assert cave_object_locations.size() == 8;
        for (int location_index = 0; location_index <8; location_index++){
            switch (cave_object_locations.get(location_index)){
                case "dinosaur":{
                    addDinosaurToLocation(stage, root, location_index);
                    break;
                }
                case "ingredient":{
                    addIngredientToLocation(stage, root, location_index);
                    break;
                }
            }
        }

        // Create button with lego player image
        Button inventory_button = new Button();
        inventory_button.setLayoutX(621);
        inventory_button.setLayoutY(288);
        inventory_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/lego_player.png"));
        inventory_button.setGraphic(legoPlayerImageView);
        inventory_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openInventoryFromCave(stage);
            }
        });

        Button return_home_button = new Button("Return Home");
        AnchorPane.setTopAnchor(return_home_button, 20.0);
        AnchorPane.setRightAnchor(return_home_button, 50.0);
        return_home_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                returnHome(stage);
            }
        });

        // Add nodes to root
        root.getChildren().addAll(inventory_button, return_home_button);

        // Create the scene
        Scene scene = new Scene(root, 1366, 768);

        // Set the scene to the stage
        stage.setScene(scene);
        stage.setTitle("The Quest for Breadfest");
        stage.show();
    }

    public void generateInventory(Stage stage, String location_where_pressed){

        // Create an AnchorPane as the root
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #8B4513;");

        // Create label for inventory
        Label inventoryLabel = new Label("Ingredient Inventory");
        inventoryLabel.setLayoutX(25);
        inventoryLabel.setLayoutY(100);
        inventoryLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        inventoryLabel.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 48px;");

        // Create button to return home
        Button exit_button = new Button("");
        exit_button.setLayoutX(1291);
        exit_button.setLayoutY(20);
        exit_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/Transparent_X.png"));
        exit_button.setGraphic(legoPlayerImageView);
        exit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exitInventory(stage, location_where_pressed);
            }
        });
        // Add nodes to root
        root.getChildren().addAll(inventoryLabel, exit_button);

        // Create a StackPane to center the table
        StackPane centerPane = new StackPane();
        centerPane.setLayoutX((double) (1366 - 600) / 2); // Center horizontally
        centerPane.setLayoutY((double) (768 - 400) / 2); // Center vertically
        root.getChildren().add(centerPane);

        // Create a TableView
        TableView<String[]> tableView = new TableView<>();
        tableView.setPrefSize(600, 400);

        // Get data from the function
        List<String[]> ingredientsData = cave_game_adaptor.getIngredientInventory();

        // Create ObservableList to hold data for TableView
        ObservableList<String[]> data = FXCollections.observableArrayList();

        // Populate data
        data.addAll(ingredientsData);

        // Define columns based on the size of the first row
        if (!data.isEmpty()) {
            int numColumns = ingredientsData.get(0).length;
            for (int column_index = 0; column_index < numColumns; column_index++) {
                TableColumn<String[], String> column = null;
                switch (column_index){
                    case 0: {
                        column = new TableColumn<>("Count");
                        column.setPrefWidth(50);
                        break;
                    }
                    case 1: {
                        column = new TableColumn<>("Ingredient Name");
                        column.setPrefWidth(246);
                        break;
                    }
                    case 2: {
                        column = new TableColumn<>("Ingredient Type");
                        column.setPrefWidth(150);
                        break;
                    }
                    default: {
                        column = new TableColumn<>("Ingredient Rarity");
                        column.setPrefWidth(150);
                    }
                }
                int finalColumn_index = column_index;
                column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[finalColumn_index]));

                tableView.getColumns().add(column);
            }
        }
        else {
            // If data is empty, display a message in the table
            System.out.println();
            Label noDataLabel = new Label("No ingredients to display");
            centerPane.getChildren().add(noDataLabel);
        }

        // Set data to TableView
        tableView.setItems(data);

        // Add TableView to StackPane
        centerPane.getChildren().add(tableView);

        // Apply table style
        tableView.setStyle("-fx-border-color: #2E8B57; -fx-border-width: 2px;");

        // Create the scene
        Scene scene = new Scene(root, 1366, 768);

        // Set the scene to the stage
        stage.setScene(scene);
        stage.setTitle("The Quest for Breadfest");
        stage.show();
    }

    private void addDinosaurToLocation(Stage stage, AnchorPane root, int location){
        Button dino_button = new Button("");;
        switch (location) { // add the dinosaur to correct location
            case 0:
                dino_button.setLayoutX(233);
                dino_button.setLayoutY(69);
                break;
            case 1:
                dino_button.setLayoutX(233);
                dino_button.setLayoutY(619);
                break;
            case 2:
                dino_button.setLayoutX(383);
                dino_button.setLayoutY(169);
                break;
            case 3:
                dino_button.setLayoutX(383);
                dino_button.setLayoutY(519);
                break;
            case 4:
                dino_button.setLayoutX(883);
                dino_button.setLayoutY(169);
                break;
            case 5:
                dino_button.setLayoutX(883);
                dino_button.setLayoutY(519);
                break;
            case 6:
                dino_button.setLayoutX(1033);
                dino_button.setLayoutY(69);
                break;
            case 7:
                dino_button.setLayoutX(1033);
                dino_button.setLayoutY(619);
                break;
            default:
                // some sort of error here, we didn't get a valid input!
                break;
        }

        dino_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/dino_button_image.png"));
        dino_button.setGraphic(legoPlayerImageView);
        dino_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fightDinosaur(stage, location);
            }
        });
        root.getChildren().add(dino_button);
    }


    private void addIngredientToLocation(Stage stage, AnchorPane root, int location){
        Button ingredient_button = new Button("");;
        switch (location) { // add the dinosaur to correct location
            case 0:
                ingredient_button.setLayoutX(233);
                ingredient_button.setLayoutY(69);
                break;
            case 1:
                ingredient_button.setLayoutX(233);
                ingredient_button.setLayoutY(619);
                break;
            case 2:
                ingredient_button.setLayoutX(383);
                ingredient_button.setLayoutY(169);
                break;
            case 3:
                ingredient_button.setLayoutX(383);
                ingredient_button.setLayoutY(519);
                break;
            case 4:
                ingredient_button.setLayoutX(883);
                ingredient_button.setLayoutY(169);
                break;
            case 5:
                ingredient_button.setLayoutX(883);
                ingredient_button.setLayoutY(519);
                break;
            case 6:
                ingredient_button.setLayoutX(1033);
                ingredient_button.setLayoutY(69);
                break;
            case 7:
                ingredient_button.setLayoutX(1033);
                ingredient_button.setLayoutY(619);
                break;
            default:
                // some sort of error here, we didn't get a valid input!
                break;
        }

        ingredient_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/Flour-Transparent.png"));
        ingredient_button.setGraphic(legoPlayerImageView);
        ingredient_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                collectIngredient(stage, location);
            }
        });
        root.getChildren().add(ingredient_button);
    }

    private void collectIngredient(Stage stage, int location) {
        System.out.println("Collect ingredient " + Arrays.toString(cave_game_adaptor.getObjectByLocation(location)));
        cave_game_adaptor.clickLocation(location);
        generateCaveRoom(stage);
    }

    private void fightDinosaur(Stage stage, int location) {
        System.out.println("Fight Dinosaur " + Arrays.toString(cave_game_adaptor.getObjectByLocation(location)));
//        cave_game_adaptor.clickLocation(location);
    }


    private void returnHome(Stage stage) {
        generateCaveEntrance(stage);
    }

    // Event handler for moving room
    private void moveRoom(Event event, Stage stage) {

        System.out.println("Moving to a new room...");
        char direction = ((Button) event.getSource()).getId().charAt(0);
        cave_game_adaptor.moveRoom(direction);
        generateCaveRoom(stage);
    }

    // Event handler for exiting inventory
    private void exitInventory(Stage stage, String location_to_return_to) {
        if (Objects.equals(location_to_return_to, "Cave")){
            generateCaveRoom(stage);
        }
        else{
            generateCaveEntrance(stage);
        }
    }

    // Event handler for opening inventory
    private void openInventoryFromCaveEntrance(Stage stage) {
        generateInventory(stage, "Entrance");
    }

    private void openInventoryFromCave(Stage stage) {
        generateInventory(stage, "Cave");
    }

    // Event handler for entering cave
    private void enterMaze(Event event, Stage stage) {
        System.out.println("Entering cave...");
        cave_game_adaptor.enterRoom0();
        generateCaveRoom(stage);
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

    public static CaveGameAdaptor getAdaptor(){
        return cave_game_adaptor;
    }
}