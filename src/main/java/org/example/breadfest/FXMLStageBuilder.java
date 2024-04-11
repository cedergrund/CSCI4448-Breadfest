package org.example.breadfest;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class FXMLStageBuilder {

    FXMLCaveApplication application;
    private final Stage stage;
    private final AnchorPane root = new AnchorPane();

    public FXMLStageBuilder(FXMLCaveApplication application, Stage stage){
        this.application = application;
        this.stage = stage;
    }

    public FXMLStageBuilder setBackgroundGreen(){
        root.setStyle("-fx-background-color: #2E8B57;");
        return this;
    }

    public FXMLStageBuilder setCaveBackground(){
        root.setStyle("-fx-background-color: #808080;");
        return this;
    }

    public FXMLStageBuilder setInventoryBackground(){
        root.setStyle("-fx-background-color: #8B4513;");
        return this;
    }

    public FXMLStageBuilder addWelcomeToBreadfestLabel(){

        Label welcome_label = new Label("Welcome to Breadfest!");
        welcome_label.setFont(new Font(32));
        AnchorPane.setLeftAnchor(welcome_label, 80.0);
        AnchorPane.setTopAnchor(welcome_label, 20.0);
        root.getChildren().add(welcome_label);
        return this;
    }

    public FXMLStageBuilder addPatienceMeter(){
        Label patience_meter = new Label("Player Patience: " + String.valueOf(application.getAdaptor().getCurrPlayerPatience()));
        patience_meter.setFont(new Font(32));
        AnchorPane.setLeftAnchor(patience_meter, 20.0);
        AnchorPane.setBottomAnchor(patience_meter, 20.0);
        root.getChildren().add(patience_meter);
        return this;
    }

    public FXMLStageBuilder addCaveEntranceImages(){

        ImageView dinosaur_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/dinosaur_sample.jpeg"));
        AnchorPane.setBottomAnchor(dinosaur_image_view, 20.0);
        AnchorPane.setRightAnchor(dinosaur_image_view, 20.0);
        root.getChildren().add(dinosaur_image_view);

        ImageView cave_entrance_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/cave_entrance.jpeg"));
        AnchorPane.setTopAnchor(cave_entrance_image_view, 80.0);
        AnchorPane.setRightAnchor(cave_entrance_image_view, 200.0);
        root.getChildren().add(cave_entrance_image_view);

        return this;
    }

    public FXMLStageBuilder addCave0Entrance(){
        Button enter_cave0_button = new Button("Enter Cave!");
        AnchorPane.setTopAnchor(enter_cave0_button, 20.0);
        AnchorPane.setLeftAnchor(enter_cave0_button, 630.0);
        AnchorPane.setRightAnchor(enter_cave0_button, 630.0);
        enter_cave0_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.enterMaze(application, stage);
            }
        });

        root.getChildren().add(enter_cave0_button);
        return this;
    }

    public FXMLStageBuilder addCaveExits(){

        List<Character> direction_list = application.getAdaptor().getRoomExitDirections();

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

                    FXMLButtonEventHandlers.moveRoom(application, event, stage);
                }
            });
            root.getChildren().add(new_button);

        }

        return this;
    }

    public FXMLStageBuilder addCaveObjects(){
        List<String> cave_object_locations = application.getAdaptor().getObjectsAtAllLocations();


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
        return this;
    }

    public FXMLStageBuilder addReturnToGameButton(String location_where_pressed){
        // Create button to return home
        Button exit_button = new Button("");
        exit_button.setLayoutX(1291);
        exit_button.setLayoutY(20);
        exit_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

        ImageView exit_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/Transparent_X.png"));
        exit_button.setGraphic(exit_image);
        exit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.exitInventory(application, stage, location_where_pressed);
            }
        });

        root.getChildren().add(exit_button);
        return this;
    }

    public FXMLStageBuilder addInventoryTable(){

        // title
        Label inventory_label = new Label("Ingredient Inventory");
        inventory_label.setLayoutX(25);
        inventory_label.setLayoutY(100);
        inventory_label.setTextFill(javafx.scene.paint.Color.WHITE);
        inventory_label.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 48px;");
        root.getChildren().add(inventory_label);

        // center the table
        StackPane centerPane = new StackPane();
        centerPane.setLayoutX((double) (1366 - 600) / 2); // Center horizontally
        centerPane.setLayoutY((double) (768 - 400) / 2); // Center vertically
        root.getChildren().add(centerPane);

        // table view create
        TableView<String[]> table_view = new TableView<>();
        table_view.setPrefSize(600, 400);

        // Get data from the function
        List<String[]> ingredients_data = application.getAdaptor().getIngredientInventory();

        // Create ObservableList to hold data for TableView
        ObservableList<String[]> data = FXCollections.observableArrayList();

        // Populate data
        data.addAll(ingredients_data);

        // Define columns based on the size of the first row
        if (!data.isEmpty()) {
            int numColumns = ingredients_data.get(0).length;
            for (int column_index = 0; column_index < numColumns; column_index++) {
                TableColumn<String[], String> column = populateColumn(column_index);
                table_view.getColumns().add(column);
            }
            table_view.setItems(data);
            centerPane.getChildren().add(table_view);
            table_view.setStyle("-fx-border-color: #2E8B57; -fx-border-width: 2px;");
        }
        else {
            // If data is empty, display a message
            System.out.println();
            Label noDataLabel = new Label("No ingredients to display");
            noDataLabel.setFont(new Font(32));
            AnchorPane.setLeftAnchor(noDataLabel, 80.0);
            AnchorPane.setTopAnchor(noDataLabel, 20.0);
            centerPane.getChildren().add(noDataLabel);
        }

        return this;
    }


    public FXMLStageBuilder addPlayerWithInventoryButton(String location_of_button){

        Button inventory_button = new Button();
        inventory_button.setLayoutX(621);
        inventory_button.setLayoutY(288);
        inventory_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/lego_player.png"));
        inventory_button.setGraphic(legoPlayerImageView);

        inventory_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.openInventory(application, stage, location_of_button);
            }
        });
        root.getChildren().add(inventory_button);

        return this;
    }

    public FXMLStageBuilder addReturnHomeButton(){

        Button return_home_button = new Button("Return Home");
        AnchorPane.setTopAnchor(return_home_button, 20.0);
        AnchorPane.setRightAnchor(return_home_button, 50.0);
        return_home_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.returnHome(application, stage);
            }
        });

        // Add nodes to root
        root.getChildren().addAll(return_home_button);
        return this;
    }

    public Stage build(){
        Scene scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.setTitle("The Quest for Breadfest");
        return stage;
    }


    // Helper functions below:

    private void addDinosaurToLocation(Stage stage, AnchorPane root, int location){

        Button dino_button = addButtonToLocation(location);
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/dino_button_image.png"));
        dino_button.setGraphic(legoPlayerImageView);
        dino_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.fightDinosaur(application, stage, location);
            }
        });
        root.getChildren().add(dino_button);
    }


    private void addIngredientToLocation(Stage stage, AnchorPane root, int location){
        Button ingredient_button = addButtonToLocation(location);
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/Flour-Transparent.png"));
        ingredient_button.setGraphic(legoPlayerImageView);
        ingredient_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.collectIngredient(application, stage, location);
            }
        });
        root.getChildren().add(ingredient_button);
    }

    private Button addButtonToLocation(int location){
        Button button = new Button("");;
        switch (location) { // add the dinosaur to correct location
            case 0:
                button.setLayoutX(233);
                button.setLayoutY(69);
                break;
            case 1:
                button.setLayoutX(233);
                button.setLayoutY(619);
                break;
            case 2:
                button.setLayoutX(383);
                button.setLayoutY(169);
                break;
            case 3:
                button.setLayoutX(383);
                button.setLayoutY(519);
                break;
            case 4:
                button.setLayoutX(883);
                button.setLayoutY(169);
                break;
            case 5:
                button.setLayoutX(883);
                button.setLayoutY(519);
                break;
            case 6:
                button.setLayoutX(1033);
                button.setLayoutY(69);
                break;
            case 7:
                button.setLayoutX(1033);
                button.setLayoutY(619);
                break;
            default:
                // some sort of error here, we didn't get a valid input!
                break;
        }

        button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        return button;
    }

    private static TableColumn<String[], String> populateColumn(int column_index) {
        TableColumn<String[], String> column = null;
        switch (column_index){
            case 0: {
                column = new TableColumn<>("Count");
                column.setPrefWidth(50);
                break;
            }
            case 1: {
                column = new TableColumn<>("Ingredient Name");
                column.setPrefWidth(244);
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
        column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[column_index]));
        return column;
    }

}