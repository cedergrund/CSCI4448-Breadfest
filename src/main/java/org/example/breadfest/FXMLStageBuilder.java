package org.example.breadfest;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.util.List;
import java.util.Objects;

public class FXMLStageBuilder {

    FXMLCaveApplication application;
    private final Stage stage;
    private final AnchorPane root = new AnchorPane();

    public FXMLStageBuilder(FXMLCaveApplication application, Stage stage){
        this.application = application;
        this.stage = stage;
    }

    public FXMLStageBuilder addCookingPot(){
        ImageView cauldron_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/cauldron_template.png"));
        AnchorPane.setBottomAnchor(cauldron_image_view, 125.0);
        AnchorPane.setLeftAnchor(cauldron_image_view, 700.0);
        root.getChildren().add(cauldron_image_view);

        return this;
    }



    public FXMLStageBuilder setBackgroundGreen(){
        root.setStyle("-fx-background-color: #2E8B57;");
        return this;
    }

    public FXMLStageBuilder setCaveBackground(){
//        root.setStyle("-fx-background-color: #808080;");
        ImageView cave_background = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/cave_background_base.png"));
        AnchorPane.setTopAnchor(cave_background, 0.0);
        AnchorPane.setRightAnchor(cave_background, 0.0);
        root.getChildren().add(cave_background);
        return this;
    }

    public FXMLStageBuilder setInventoryBackground(){
        root.setStyle("-fx-background-color: #8B4513;");
        return this;
    }

    public FXMLStageBuilder setBakingSceneBackground(){
        root.setStyle("-fx-background-color: #333333;");
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

        // Inside your method where you set up your UI
        ProgressBar patience_meter = new ProgressBar();
        patience_meter.setPrefWidth(200);
        AnchorPane.setLeftAnchor(patience_meter, 20.0);
        AnchorPane.setBottomAnchor(patience_meter, 20.0);
        root.getChildren().add(patience_meter);

        FXMLCave adaptor = application.getAdaptor();
        int curr_patience = adaptor.getCurrPlayerPatience();
        double patiencePercentage = (double) curr_patience / adaptor.getMaxPlayerPatience();;
        patience_meter.setProgress(patiencePercentage);

        Label patience_label = new Label("Patience: "+String.valueOf(curr_patience));
        patience_label.setFont(Font.font("Verdana", FontWeight.BOLD, 16)); // Use "Satisfy" as the font name
        AnchorPane.setLeftAnchor(patience_label, 20.0);
        AnchorPane.setBottomAnchor(patience_label, 40.0);
        root.getChildren().add(patience_label);

        return this;
    }

    public FXMLStageBuilder addHonorMeter(){

        // Inside your method where you set up your UI
        ProgressBar honor_meter = new ProgressBar();
        honor_meter.setPrefWidth(200);
        AnchorPane.setRightAnchor(honor_meter, 20.0);
        AnchorPane.setBottomAnchor(honor_meter, 20.0);
        root.getChildren().add(honor_meter);

        FXMLCave adaptor = application.getAdaptor();
        int curr_honor = adaptor.getCurrPlayerHonor();
        double honorPercentage = (double) curr_honor / 1000;
//        honor_meter.setProgress(honorPercentage);
        honor_meter.setProgress(honorPercentage);

        Label honor_label = new Label("Honor: "+String.valueOf(curr_honor));
        honor_label.setFont(Font.font("Verdana", FontWeight.BOLD, 16)); // Use "Satisfy" as the font name
        honor_label.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(honor_label, 20.0);
        AnchorPane.setBottomAnchor(honor_label, 40.0);
        root.getChildren().add(honor_label);

        return this;
    }

    public FXMLStageBuilder addCaveEntranceButtonsAndImages(){

        ImageView dinosaur_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/dino1.100x.GIF"));
        AnchorPane.setBottomAnchor(dinosaur_image_view, 20.0);
        AnchorPane.setRightAnchor(dinosaur_image_view, 20.0);
        root.getChildren().add(dinosaur_image_view);

        ImageView cave_entrance_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/cave_entrance.jpeg"));
        AnchorPane.setTopAnchor(cave_entrance_image_view, 80.0);
        AnchorPane.setRightAnchor(cave_entrance_image_view, 200.0);
        root.getChildren().add(cave_entrance_image_view);

        Button enter_cave0_button = new Button("Enter Cave!");
        AnchorPane.setTopAnchor(enter_cave0_button, 20.0);
        AnchorPane.setLeftAnchor(enter_cave0_button, 630.0);
        AnchorPane.setRightAnchor(enter_cave0_button, 630.0);
        enter_cave0_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.enterMaze(application);
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
                    new_button = new Button();
                    new_button.setId("N");
                    AnchorPane.setLeftAnchor(new_button, 630.0);
                    AnchorPane.setRightAnchor(new_button, 630.0);
                    AnchorPane.setBottomAnchor(new_button, 680.0);
                    new_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
                    ImageView north_portal_display = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/north_portal.png"));
                    new_button.setGraphic(north_portal_display);
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

                    FXMLButtonEventHandlers.moveRoom(application, event);
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
                    String image_url = application.getAdaptor().getDinosaurImageByLocation(location_index);
                    addDinosaurToLocation(stage, root, location_index, image_url);
                    break;
                }
                case "ingredient":{
                    String image_url = application.getAdaptor().getIngredientImageByLocation(location_index);
                    addIngredientToLocation(stage, root, location_index, image_url);
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
        exit_button.setOnAction(event -> FXMLButtonEventHandlers.exitInventory(application, location_where_pressed));

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
            int numColumns = ingredients_data.get(0).length - 1;
            for (int column_index = 0; column_index < numColumns; column_index++) {
                TableColumn<String[], String> column = populateInventoryColumns(column_index);
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

    public FXMLStageBuilder addBakingInventoryTable(){

        Label inventory_label = new Label("Let's Bake!");
        inventory_label.setLayoutX(25);
        inventory_label.setLayoutY(25);
        inventory_label.setTextFill(javafx.scene.paint.Color.WHITE);
        inventory_label.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 48px;");
        root.getChildren().add(inventory_label);

        StackPane centerPane = new StackPane();
        centerPane.setLayoutX(42); // Center horizontally
        centerPane.setLayoutY(84); // Center vertically
        root.getChildren().add(centerPane);

        TableView<String[]> table_view = new TableView<>();
        table_view.setPrefSize(600, 600);
        table_view.setEditable(true);

        table_view.setRowFactory(tableView -> new TableRow<>() {
            @Override
            protected void updateItem(String[] item, boolean empty) {
                super.updateItem(item, empty);
                if (getIndex() % 2 == 0) {
                    // Set background color for even rows
                    setStyle("-fx-background-color: #7F95B7;"); // light grey
                } else {
                    // Set background color for odd rows
                    setStyle("-fx-background-color: #5F79A5;"); // light blue
                }
            }
        });

        Button removeSelectedButton = new Button("Bake Ingredients");
        removeSelectedButton.setLayoutX(800);
        removeSelectedButton.setLayoutY(650);
        removeSelectedButton.setOnAction(event -> {
            int upgrade = FXMLButtonEventHandlers.bakeIngredients(application, table_view);
            FXMLButtonEventHandlers.openBakingScene(application, upgrade);
        });
        root.getChildren().add(removeSelectedButton);


        List<String[]> ingredients_data = application.getAdaptor().getIngredientInventory();

        ObservableList<String[]> data = FXCollections.observableArrayList();

        data.addAll(ingredients_data);


        if (!data.isEmpty()) {
            int numColumns = ingredients_data.get(0).length - 1;

            // Add checkbox column
            TableColumn<String[], Boolean> checkBoxColumn = new TableColumn<>("Select");
            checkBoxColumn.setEditable(true);
            checkBoxColumn.setCellValueFactory(param -> {
                final String[] rowData = param.getValue();
                BooleanProperty selected = new SimpleBooleanProperty(Boolean.parseBoolean(rowData[0]));
                selected.addListener((observable, oldValue, newValue) -> {
                    // Update rowData at index 4 with the new value
                    rowData[4] = newValue.toString();
                });
                return selected;
            });


            checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
            table_view.getColumns().add(checkBoxColumn);

            for (int column_index = 0; column_index < numColumns; column_index++) {
                TableColumn<String[], String> column = populateBakingColumns(column_index);
                table_view.getColumns().add(column);
            }

//            // Update the structure of the data array to accommodate the additional checkbox boolean value
//            for (String[] row : data) {
//                String[] newRow = new String[row.length + 1];
//                newRow[0] = "false"; // Initialize the checkbox boolean to false
//                System.arraycopy(row, 0, newRow, 1, row.length); // Copy the original contents of the row
//                row = newRow; // Update the reference to the modified row
//            }

            table_view.setItems(data);
            centerPane.getChildren().add(table_view);
            table_view.setStyle("-fx-border-color: #0E0A06; -fx-border-width: 2px;");
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

    private static TableColumn<String[], String> populateInventoryColumns(int column_index) {
        TableColumn<String[], String> column = null;
        switch (column_index){
            case 0: {
                column = new TableColumn<>("Count");
                column.setPrefWidth(50);
                break;
            }
            case 1: {
                column = new TableColumn<>("Ingredient Name");
                column.setPrefWidth(244); // was 244, going to try to change it to 194
                break;
            }
            case 2: {
                column = new TableColumn<>("Ingredient Type");
                column.setPrefWidth(150);
                break;
            }
            case 3: {
                column = new TableColumn<>("Ingredient Rarity");
                column.setPrefWidth(150);
                break;
            }
//            case 4: {
//                column = new TableColumn<>("");
//                column.setPrefWidth(150);
//                break;
//            }
        }
        if (column != null) {
            column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[column_index]));
        }
        return column;

    }

    private static TableColumn<String[], String> populateBakingColumns(int column_index) {
        TableColumn<String[], String> column = null;
        switch (column_index){
            case 0: {
                column = new TableColumn<>("Count");
                column.setPrefWidth(50);
                break;
            }
            case 1: {
                column = new TableColumn<>("Ingredient Name");
                column.setPrefWidth(189); // was 244, going to try to change it to 194
                break;
            }
            case 2: {
                column = new TableColumn<>("Ingredient Type");
                column.setPrefWidth(150);
                break;
            }
            case 3: {
                column = new TableColumn<>("Ingredient Rarity");
                column.setPrefWidth(150);
                break;
            }
//            case 4: {
//                column = new TableColumn<>("");
//                column.setPrefWidth(150);
//                break;
//            }
        }
        if (column != null) {
            column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[column_index]));
        }
        return column;

    }


    public FXMLStageBuilder addBillWithBakingSceneButton(String location_of_button){

        Button baking_scene_button = new Button();
        baking_scene_button.setLayoutX(50);
        baking_scene_button.setLayoutY(50);
        baking_scene_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView bill_wright_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/bill_wright_npc.jpg"));
        baking_scene_button.setGraphic(bill_wright_image);

        baking_scene_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.openBakingScene(application, 0);
            }
        });
        root.getChildren().add(baking_scene_button);

        return this;
    }


    public FXMLStageBuilder addPlayerWithInventoryButton(String location_of_button){

        Button inventory_button = new Button();
        inventory_button.setLayoutX(533);
        inventory_button.setLayoutY(234);
        inventory_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/character.100x-ezgif.com-resize.gif"));
        inventory_button.setGraphic(legoPlayerImageView);

        inventory_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.openInventory(application, location_of_button);
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
                FXMLButtonEventHandlers.returnHome(application);
            }
        });

        // Add nodes to root
        root.getChildren().addAll(return_home_button);
        return this;
    }

    public FXMLStageBuilder fightRoomSetup(){
        // Rectangle background
        Rectangle background = new Rectangle(1366, 768, Color.web("#d7d6d6"));
        root.getChildren().add(background);

        // background image
        ImageView backgroundImage = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/fight_background.png"));
        backgroundImage.setFitWidth(1366);
        backgroundImage.setFitHeight(449);
        backgroundImage.setLayoutX(0);
        backgroundImage.setLayoutY(0);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setOpacity(0.9);
        root.getChildren().add(backgroundImage);

        // dino/player divider
        Line divider = new Line(683, 0, 683, 512);
        divider.setStrokeWidth(2);
        divider.setStroke(Color.BLACK);
        root.getChildren().add(divider);

        // result rectangle
        Rectangle results = new Rectangle(300, 120, Color.web("#adb6bf"));
        results.setLayoutX(533);
        results.setLayoutY(30);
        results.setStrokeWidth(1);
        results.setStroke(Color.BLACK);
        root.getChildren().add(results);

        // floor rectangle
        Rectangle floor = new Rectangle(1366, 128, Color.web("#941d1d"));
        floor.setLayoutX(0);
        floor.setLayoutY(384);
        floor.setStrokeWidth(0);
        root.getChildren().add(floor);

        // horizontal dividers
        Line horizontal_divider1 = new Line(0, 512, 1366, 512);
        horizontal_divider1.setStrokeWidth(5);
        horizontal_divider1.setStroke(Color.BLACK);
        Line horizontal_divider2 = new Line(0, 384, 1366, 384);
        horizontal_divider2.setStrokeWidth(2);
        horizontal_divider2.setStroke(Color.BLACK);
        root.getChildren().addAll(horizontal_divider1, horizontal_divider2);

        String[] player_dino_info = application.getAdaptor().getFightersInformation();

        // Player image
        ImageView playerImage = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/character.100x-ezgif.com-resize.gif"));
        playerImage.setFitWidth(450);
        playerImage.setFitHeight(420);
        playerImage.setLayoutX(116.5);
        playerImage.setLayoutY(68);
        root.getChildren().add(playerImage);

        // Dinosaur image
        ImageView dinoImage = new ImageView(new Image(application.getAdaptor().getDinoImage()));
        dinoImage.setFitWidth(375);
        dinoImage.setFitHeight(372);
        dinoImage.setLayoutX(837);
        dinoImage.setLayoutY(76);
        root.getChildren().add(dinoImage);

        // Player label + patience bar
        Label player_name = new Label("Player");
        player_name.setLayoutX(50);
        player_name.setLayoutY(20);
        player_name.setPrefSize(309, 45);
        player_name.setFont(Font.font("Baloo 2 Bold", 25));
        player_name.setTextFill(Color.WHITE);
        root.getChildren().add(player_name);
//
        int curr_patience = Integer.parseInt(player_dino_info[1]);
        int max_patience = Integer.parseInt(player_dino_info[2]);

        ProgressBar patience_meter_player = new ProgressBar();
        patience_meter_player.setPrefSize(200, 20);
        patience_meter_player.setLayoutX(50);
        patience_meter_player.setLayoutY(60);
        double patience_percentage_player = (double) curr_patience / max_patience;
        patience_meter_player.setProgress(patience_percentage_player);
        root.getChildren().add(patience_meter_player);

        Label patience_label = new Label("Patience: "+ curr_patience +"/"+ max_patience);
        patience_label.setFont(Font.font("Baloo 2 Regular", 15));
        patience_label.setLayoutX(50);
        patience_label.setLayoutY(80);
        patience_label.setTextFill(Color.WHITE);
        root.getChildren().add(patience_label);

        // Dinosaur label + bar
        Label dino_name_label = new Label(player_dino_info[3]);
        dino_name_label.setLayoutX(916);
        dino_name_label.setLayoutY(20);
        dino_name_label.setPrefSize(400, 45);
        dino_name_label.setFont(Font.font("Baloo 2 Bold", 25));
        dino_name_label.setTextFill(Color.WHITE);
        dino_name_label.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        root.getChildren().add(dino_name_label);

        ProgressBar patience_meter_dinosaur = new ProgressBar();
        patience_meter_dinosaur.setPrefSize(200, 20);
        patience_meter_dinosaur.setLayoutX(1116);
        patience_meter_dinosaur.setLayoutY(60);
        patience_meter_dinosaur.setRotate(180);
        patience_meter_dinosaur.setProgress((double) Integer.parseInt(player_dino_info[4]) / Integer.parseInt(player_dino_info[5]));
        root.getChildren().add(patience_meter_dinosaur);


        // boxes along bottom
        for (int box_number = 0; box_number < 4; box_number++){
            double x = getXLocation(box_number);

            // box outline
            Rectangle box = new Rectangle(341.5, 256, Color.web("#c9cccd"));
            box.setLayoutX(x);
            box.setLayoutY(512);
            box.setStrokeWidth(1);
            box.setStroke(Color.BLACK);
            root.getChildren().add(box);
        }


        return this;
    }

    public FXMLStageBuilder addFightMoves(){

        Label instruction_text = new Label("Pick a Die to Gamble!");
        instruction_text.setLayoutX(533);
        instruction_text.setLayoutY(30);
        instruction_text.setPrefSize(300, 120);
        instruction_text.setFont(Font.font("Baloo 2 Bold", 21));
        instruction_text.setTextFill(Color.BLACK);
        instruction_text.setAlignment(Pos.CENTER);
        root.getChildren().add(instruction_text);

        // dice
        addDieForRolling(0);
        addDieForRolling(1);
        addDieForRolling(2);
        addDieForRolling(3);

        return this;
    }

    public FXMLStageBuilder addFightResults(String[] results){

//       pop up text for later: "Uh-oh! Player ran out of Patience!\nThey don't want to play anymore"

        Label instruction_text = new Label("Player Roll: " + results[1] + " | Dino Roll: " + results[2] + "\n"+results[3]);
        instruction_text.setLayoutX(533);
        instruction_text.setLayoutY(30);
        instruction_text.setPrefSize(300, 120);
        instruction_text.setFont(Font.font("Baloo 2 Bold", 21));
        instruction_text.setTextFill(Color.BLACK);
        instruction_text.setAlignment(Pos.CENTER);
        instruction_text.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(instruction_text);

        Button button = new Button("Continue...");
        button.setLayoutX(606);
        button.setLayoutY(240);
        button.setPrefSize(150, 30);
        button.setTextFill(Color.WHITE);
        button.getStyleClass().add("cta");
        button.setStyle("-fx-background-color: #6225E6; ");
        button.setFont(Font.font("Baloo 2 Bold", 20));
        button.setOnAction(event -> FXMLButtonEventHandlers.startNextFightTurn(application, results[0]));

        root.getChildren().add(button);

        return this;
    }

    private void addDieForRolling(int die_index) {

        double x = getXLocation(die_index);

        // labels
        Label die_name;
        Label description;
        String[] die_information;

        if (die_index == 3){
            die_information = application.getAdaptor().getDieInformation(die_index+1);
            die_name = new Label("Run Away");
            description = new Label(die_information[1]);
            die_information[0] = "";
        }
        else{
            die_information = application.getAdaptor().getDieInformation(die_index);
            if (Objects.equals(die_information[0], "null")){
                die_name = new Label("No Dice");
                description = new Label("Collect more dice to populate.");
            }
            else{
                die_name = new Label(die_information[0]);
                description = new Label(die_information[1]);
            }
        }

        die_name.setLayoutX(x+85);
        die_name.setLayoutY(534);
        die_name.setPrefSize(172,30);
        die_name.setAlignment(Pos.CENTER);
        die_name.setFont(Font.font("Baloo 2 Bold", 18));

        description.setLayoutX(x+42);
        description.setLayoutY(558);
        description.setFont(Font.font("Baloo 2 Regular", 13));
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.CENTER);
        description.setAlignment(Pos.TOP_CENTER);

        if (die_index == 3){
            description.setPrefSize(258, 150);
        }
        else{
            description.setPrefSize(258,100);
        }

        root.getChildren().addAll(die_name, description);


        // return if no die
        if (Objects.equals(die_information[0], "null")){
            return;
        }

        // add button
        Button button;

        if (die_index == 3){
            button = new Button("Flee");
            button.setStyle("-fx-background-color: #309e41");
        }
        else{
            button = new Button("Roll");
            button.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400),        linear-gradient(#ffef84, #f2ba44),        linear-gradient(#ffea6a, #efaa22),        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));;");
        }

        button.setLayoutX(x+83);
        button.setLayoutY(718);
        button.setPrefSize(172, 32);
        button.setTextFill(Color.BLACK);
        button.setId(String.valueOf(die_index));
        button.setFont(Font.font("Baloo 2 Bold", 14));
        button.setOnAction(event -> FXMLButtonEventHandlers.fightButtonPushed(application, event));

        root.getChildren().add(button);

        if (!Objects.equals(die_information[2], "")){
            ImageView pdf_image = new ImageView(new Image(die_information[2]));
            pdf_image.setFitWidth(210);
            pdf_image.setFitHeight(105);
            pdf_image.setLayoutX(x + 65.75);
            pdf_image.setLayoutY(606);

            Label pdf_label = new Label("Die PDF:");
            pdf_label.setLayoutX(x+65);
            pdf_label.setLayoutY(600);
            pdf_label.setUnderline(true);
            pdf_label.setFont(Font.font("Baloo 2 Regular", 13));
            root.getChildren().addAll(pdf_image, pdf_label);
        }

    }

    private double getXLocation(int die_index) {
        double x;
        switch (die_index){
            case 0: {
                x = 0;
                break;
            }
            case 1:{
                x = 341.5;
                break;
            }
            case 2:{
                x = 2*341.5;
                break;
            }
            default:{
                x = 3*341.5;
                break;
            }
        }
        return x;
    }

    public Stage build(){
        Scene scene = new Scene(root, 1366, 768);
        stage.setScene(scene);
        stage.setTitle("The Quest for Breadfest");
        return stage;
    }


    // Helper functions below:

    private void addDinosaurToLocation(Stage stage, AnchorPane root, int location, String image_url){

        Button dino_button = addButtonToLocation(location);
        ImageView dino_image = new ImageView(new Image(image_url));
        dino_button.setGraphic(dino_image);
        dino_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.fightDinosaur(application,location);
            }
        });
        root.getChildren().add(dino_button);
    }


    private void addIngredientToLocation(Stage stage, AnchorPane root, int location, String image_url){
        Button ingredient_button = addButtonToLocation(location);
        ImageView ingredient_image = new ImageView(new Image(image_url));
        ingredient_button.setGraphic(ingredient_image);
        ingredient_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLButtonEventHandlers.collectIngredient(application, location);
            }
        });
        root.getChildren().add(ingredient_button);
    }

    private Button addButtonToLocation(int location){
        Button button = new Button("");;
        switch (location) { // add the dinosaur to correct location
            case 0:
                button.setLayoutX(233-50);
                button.setLayoutY(69-50);
                break;
            case 1:
                button.setLayoutX(233-50);
                button.setLayoutY(619-50);
                break;
            case 2:
                button.setLayoutX(383-50);
                button.setLayoutY(169-50);
                break;
            case 3:
                button.setLayoutX(383-50);
                button.setLayoutY(519-50);
                break;
            case 4:
                button.setLayoutX(883-50);
                button.setLayoutY(169-50);
                break;
            case 5:
                button.setLayoutX(883-50);
                button.setLayoutY(519-50);
                break;
            case 6:
                button.setLayoutX(1033-50);
                button.setLayoutY(69-50);
                break;
            case 7:
                button.setLayoutX(1033-50);
                button.setLayoutY(619-50);
                break;
            default:
                // some sort of error here, we didn't get a valid input!
                break;
        }

        button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        return button;
    }

}



