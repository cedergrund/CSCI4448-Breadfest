package org.example.breadfest;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class FXMLStageBuilder {

    FXMLCaveApplication application;
    private final Stage stage;
    private final AnchorPane root = new AnchorPane();

    public FXMLStageBuilder(FXMLCaveApplication application, Stage stage){
        this.application = application;
        this.stage = stage;
    }


    public FXMLStageBuilder setCaveEntranceBackground(){
        ImageView background_scene = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/cave_entrance_sceen.png"));
        AnchorPane.setTopAnchor(background_scene, 0.0);
        AnchorPane.setLeftAnchor(background_scene, 0.0);
        root.getChildren().add(background_scene);
        return this;
    }

    public FXMLStageBuilder setCaveBackground(){
//        root.setStyle("-fx-background-color: #808080;");
        ImageView cave_background = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/cave_background_base.png"));
        AnchorPane.setTopAnchor(cave_background, 0.0);
        AnchorPane.setRightAnchor(cave_background, 0.0);
        root.getChildren().add(cave_background);
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

        // Inside your method where you set up your UI
        ProgressBar patience_meter = new ProgressBar();
        patience_meter.setPrefWidth(200);
        patience_meter.setId("patience_bar");
        AnchorPane.setLeftAnchor(patience_meter, 20.0);
        AnchorPane.setBottomAnchor(patience_meter, 20.0);
        root.getChildren().add(patience_meter);

        FXMLCave adaptor = application.getAdaptor();
        int curr_patience = adaptor.getCurrPlayerPatience();
        double patiencePercentage = (double) curr_patience / adaptor.getMaxPlayerPatience();;
        patience_meter.setProgress(patiencePercentage);

        Label patience_label = new Label("Patience: "+String.valueOf(curr_patience));
        patience_label.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        patience_label.setId("patience_label");
        patience_label.setTextFill(Color.WHITE);
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
        double honor_percentage = (double) curr_honor / 5000;
        honor_meter.setProgress(honor_percentage);

        // setting progress bar color by level
        if (honor_percentage < ((double)250/5000)){
            honor_meter.setStyle("-fx-accent: yellow;");
        }
        else if (honor_percentage < ((double)750/5000)){
            honor_meter.setStyle("-fx-accent: green;");
        }
        else if (honor_percentage < ((double)2000/5000)){
            honor_meter.setStyle("-fx-accent: blue;");
        }
        else if (honor_percentage < ((double)5000/5000)){
            honor_meter.setStyle("-fx-accent: purple;");
        }
        else{
            System.out.println("max");
            honor_meter.setStyle("-fx-accent: red;");
        }

        Label honor_label = new Label("Honor: "+String.valueOf(curr_honor));
        honor_label.setFont(Font.font("Verdana", FontWeight.BOLD, 16)); // Use "Satisfy" as the font name
        honor_label.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(honor_label, 20.0);
        AnchorPane.setBottomAnchor(honor_label, 40.0);
        root.getChildren().add(honor_label);

        return this;
    }

    public FXMLStageBuilder addCaveEntranceButtonAndParticles(){

//        ImageView dinosaur_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/dino1.100x.GIF"));
//        AnchorPane.setBottomAnchor(dinosaur_image_view, 20.0);
//        AnchorPane.setRightAnchor(dinosaur_image_view, 20.0);
//        root.getChildren().add(dinosaur_image_view);

//        ImageView cave_entrance_image_view = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/cave_entrance.jpeg"));
//        AnchorPane.setTopAnchor(cave_entrance_image_view, 80.0);
//        AnchorPane.setRightAnchor(cave_entrance_image_view, 200.0);
//        root.getChildren().add(cave_entrance_image_view);

        ImageView portal_graphics_1 = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/portal_texture_big.gif"));

        AnchorPane.setLeftAnchor(portal_graphics_1, 955.0);
        AnchorPane.setTopAnchor(portal_graphics_1, 87.0 + 50.0);
        portal_graphics_1.setOpacity(0.35);
        root.getChildren().add(portal_graphics_1);



        Button enter_cave0_button = new Button();
        enter_cave0_button.setLayoutX(905.0);
        enter_cave0_button.setLayoutY(87.0);
        enter_cave0_button.setPrefWidth(300.0);
        enter_cave0_button.setPrefHeight(300.0);
        enter_cave0_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");



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
            ImageView portal_graphics_1 = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/tiny_portal_texture.gif"));
            ImageView portal_graphics_2 = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/tiny_portal_texture.gif"));
            ImageView portal_graphics_3 = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/tiny_portal_texture.gif"));
            ImageView portal_graphics_4 = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/tiny_portal_texture.gif"));
            ImageView portal_graphics_5 = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/tiny_portal_texture.gif"));

            switch (direction) { // add the button for each direction
                case 'N':
                    // Add North button
                    new_button = new Button();
                    new_button.setId("N");
                    // make the button the same size as the north_portal image
                    new_button.setPrefSize(500.0, 83.0);
                    //position the button
                    AnchorPane.setLeftAnchor(new_button, 433.0);
                    AnchorPane.setRightAnchor(new_button, 433.0);
                    AnchorPane.setBottomAnchor(new_button, 684.0);
                    //make the button transparent, so it rests on top of the image and gif
                    new_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

                    // Add the portal image and position it correctly
                    ImageView north_portal_display = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/north_portal.png"));
                    AnchorPane.setLeftAnchor(north_portal_display, 433.0);
                    AnchorPane.setRightAnchor(north_portal_display, 433.0);
                    AnchorPane.setBottomAnchor(north_portal_display, 684.0);
                    // Add the portal to the root on the bottom
                    root.getChildren().add(north_portal_display);

                    //Add the portal gif particles
                    AnchorPane.setLeftAnchor(portal_graphics_1, 488.0 + 10.0);
                    AnchorPane.setTopAnchor(portal_graphics_1, 5.0);
                    portal_graphics_1.setOpacity(0.35);
                    root.getChildren().add(portal_graphics_1);

                    AnchorPane.setLeftAnchor(portal_graphics_2, 568.0 + 5.0);
                    AnchorPane.setTopAnchor(portal_graphics_2, 15.0);
                    portal_graphics_2.setOpacity(0.35);
                    portal_graphics_2.setRotate(0.9);
                    root.getChildren().add(portal_graphics_2);

                    AnchorPane.setLeftAnchor(portal_graphics_5, 683.0 - 35.0);
                    AnchorPane.setTopAnchor(portal_graphics_5, 10.0);
                    portal_graphics_5.setOpacity(0.35);
                    root.getChildren().add(portal_graphics_5);

                    AnchorPane.setLeftAnchor(portal_graphics_3, 728.0 - 5.0);
                    AnchorPane.setTopAnchor(portal_graphics_3, 15.0);
                    portal_graphics_3.setOpacity(0.35);
                    portal_graphics_2.setRotate(0.9);
                    root.getChildren().add(portal_graphics_3);

                    AnchorPane.setLeftAnchor(portal_graphics_4, 808.0 - 5.0);
                    AnchorPane.setTopAnchor(portal_graphics_4, 5.0);
                    portal_graphics_4.setOpacity(0.35);
                    root.getChildren().add(portal_graphics_4);
                    break;
                case 'S':
                    new_button = new Button();
                    new_button.setId("S");
                    // make the button the same size as the south_portal image
                    new_button.setPrefSize(500.0, 83.0);
                    //position the button
                    AnchorPane.setLeftAnchor(new_button, 433.0);
                    AnchorPane.setRightAnchor(new_button, 433.0);
                    AnchorPane.setTopAnchor(new_button, 684.0);
                    //make the button transparent, so it rests on top of the image and gif
                    new_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

                    // Add the portal image and position it correctly
                    ImageView south_portal_display = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/south_portal.png"));
                    AnchorPane.setLeftAnchor(south_portal_display, 433.0 - 30.0);
                    AnchorPane.setRightAnchor(south_portal_display, 433.0 + 30.0);
                    AnchorPane.setTopAnchor(south_portal_display, 684.0);
                    // Add the portal to the root on the bottom
                    root.getChildren().add(south_portal_display);

                    //Add the portal gif particles
                    AnchorPane.setLeftAnchor(portal_graphics_1, 488.0 + 5.0);
                    AnchorPane.setBottomAnchor(portal_graphics_1, 5.0);
                    portal_graphics_1.setOpacity(0.35);
                    root.getChildren().add(portal_graphics_1);

                    AnchorPane.setLeftAnchor(portal_graphics_2, 568.0 + 5.0);
                    AnchorPane.setBottomAnchor(portal_graphics_2, 15.0);
                    portal_graphics_2.setOpacity(0.35);
                    portal_graphics_2.setRotate(90.0);
                    root.getChildren().add(portal_graphics_2);

                    AnchorPane.setLeftAnchor(portal_graphics_5, 683.0 - 35.0);
                    AnchorPane.setBottomAnchor(portal_graphics_5, 5.0);
                    portal_graphics_5.setOpacity(0.35);
                    root.getChildren().add(portal_graphics_5);

                    AnchorPane.setLeftAnchor(portal_graphics_3, 728.0 - 5.0);
                    AnchorPane.setBottomAnchor(portal_graphics_3, 15.0);
                    portal_graphics_3.setOpacity(0.35);
                    portal_graphics_2.setRotate(90.0);
                    root.getChildren().add(portal_graphics_3);

                    AnchorPane.setLeftAnchor(portal_graphics_4, 808.0 - 20.0);
                    AnchorPane.setBottomAnchor(portal_graphics_4, 5.0);
                    portal_graphics_4.setOpacity(0.35);
                    portal_graphics_4.setRotate(90.0);
                    root.getChildren().add(portal_graphics_4);
                    break;
                case 'E':
                    new_button = new Button();
                    new_button.setId("E");
                    // make the button the same size as the north_portal image
                    new_button.setPrefSize(81.0, 416.0);
                    //position the button
                    AnchorPane.setLeftAnchor(new_button, 1366.0 - 81.0);
                    AnchorPane.setTopAnchor(new_button, 173.5);
                    AnchorPane.setBottomAnchor(new_button, 173.5);
                    //make the button transparent, so it rests on top of the image and gif
                    new_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
                    // Add the portal image and position it correctly
                    ImageView east_portal_display = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/east_portal.png"));
                    AnchorPane.setLeftAnchor(east_portal_display, 1366.0 - 78.0);
                    AnchorPane.setTopAnchor(east_portal_display, 176.0);
                    AnchorPane.setBottomAnchor(east_portal_display, 176.0);
                    // Add the portal to the root on the bottom
                    root.getChildren().add(east_portal_display);

                    AnchorPane.setRightAnchor(portal_graphics_1, 5.0);
                    AnchorPane.setTopAnchor(portal_graphics_1, 229.0 + 20.0);
                    portal_graphics_1.setOpacity(0.35);
                    portal_graphics_1.setFitHeight(60.0);
                    portal_graphics_1.setFitWidth(60.0);
                    root.getChildren().add(portal_graphics_1);

                    AnchorPane.setRightAnchor(portal_graphics_2, 10.0);
                    AnchorPane.setTopAnchor(portal_graphics_2, 309.0);
                    portal_graphics_2.setOpacity(0.35);
                    portal_graphics_2.setRotate(90.0);
                    root.getChildren().add(portal_graphics_2);

                    AnchorPane.setRightAnchor(portal_graphics_3, 10.0);
                    AnchorPane.setTopAnchor(portal_graphics_3, 389.0);
                    portal_graphics_3.setOpacity(0.35);
                    portal_graphics_2.setRotate(180.0);
                    root.getChildren().add(portal_graphics_3);

                    AnchorPane.setRightAnchor(portal_graphics_4, 5.0);
                    AnchorPane.setTopAnchor(portal_graphics_4, 469.0 - 10.0);
                    portal_graphics_4.setOpacity(0.35);
                    portal_graphics_4.setRotate(90.0);
                    portal_graphics_4.setFitHeight(60.0);
                    portal_graphics_4.setFitWidth(60.0);
                    root.getChildren().add(portal_graphics_4);
                    break;
                case 'W':
                    new_button = new Button();
                    new_button.setId("W");
                    // make the button the same size as the north_portal image
                    new_button.setPrefSize(83.0, 421.0);
                    //position the button
                    AnchorPane.setRightAnchor(new_button, 1366.0 - 83.0);
                    AnchorPane.setTopAnchor(new_button, 173.5);
                    AnchorPane.setBottomAnchor(new_button, 173.5);
                    //make the button transparent, so it rests on top of the image and gif
                    new_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

                    // Add the portal image and position it correctly
                    ImageView west_portal_display = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/west_portal.png"));
                    AnchorPane.setRightAnchor(west_portal_display, 1366.0 - 83.0);
                    AnchorPane.setTopAnchor(west_portal_display, 173.5);
                    AnchorPane.setBottomAnchor(west_portal_display, 173.5);
                    // Add the portal to the root on the bottom
                    root.getChildren().add(west_portal_display);

                    AnchorPane.setLeftAnchor(portal_graphics_1, 5.0);
                    AnchorPane.setTopAnchor(portal_graphics_1, 229.0 + 20.0);
                    portal_graphics_1.setOpacity(0.35);
                    portal_graphics_1.setFitHeight(60.0);
                    portal_graphics_1.setFitWidth(60.0);
                    root.getChildren().add(portal_graphics_1);

                    AnchorPane.setLeftAnchor(portal_graphics_2, 10.0);
                    AnchorPane.setTopAnchor(portal_graphics_2, 309.0);
                    portal_graphics_2.setOpacity(0.35);
                    portal_graphics_2.setRotate(90.0);
                    root.getChildren().add(portal_graphics_2);

                    AnchorPane.setLeftAnchor(portal_graphics_3, 10.0);
                    AnchorPane.setTopAnchor(portal_graphics_3, 389.0);
                    portal_graphics_3.setOpacity(0.35);
                    portal_graphics_2.setRotate(180.0);
                    root.getChildren().add(portal_graphics_3);

                    AnchorPane.setLeftAnchor(portal_graphics_4, 5.0);
                    AnchorPane.setTopAnchor(portal_graphics_4, 469.0 - 10.0);
                    portal_graphics_4.setOpacity(0.35);
                    portal_graphics_4.setRotate(90.0);
                    portal_graphics_4.setFitHeight(60.0);
                    portal_graphics_4.setFitWidth(60.0);
                    root.getChildren().add(portal_graphics_4);
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

        ImageView exit_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/Transparent_X.png"));
        exit_button.setGraphic(exit_image);
        exit_button.setOnAction(event -> FXMLButtonEventHandlers.exitInventory(application, location_where_pressed));

        root.getChildren().add(exit_button);
        return this;
    }

    public FXMLStageBuilder addInventoryTable(){

        // title
        Label inventory_label = new Label("Ingredient Inventory");
        inventory_label.setLayoutX(25);
        inventory_label.setLayoutY(25);
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

    public FXMLStageBuilder bakingSceneBackground(){
        ImageView kitchen = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/kitchen_background.png"));
        kitchen.setFitWidth(1366);
        kitchen.setFitHeight(768);
        kitchen.setLayoutX(0);
        kitchen.setLayoutY(0);
        root.getChildren().add(kitchen);

        // base labels
        Label title_label = new Label("Time to Bake!");
        title_label.setLayoutX(383);
        title_label.setLayoutY(80);
        title_label.setPrefSize(600, 80);
        title_label.setFont(Font.font("Baloo 2", 40));
        title_label.setUnderline(true);
        title_label.setTextFill(Color.WHITE);
        title_label.setAlignment(Pos.CENTER);
        title_label.setStyle("-fx-background-color: rgb(0,0,0,0.5);");
        title_label.setTextAlignment(TextAlignment.CENTER);

        root.getChildren().add(title_label);


        // button
        Button return_outside = new Button("return outside");
        return_outside.setLayoutX(1201);
        return_outside.setLayoutY(15);
        return_outside.setPrefSize(150, 30);
        return_outside.setTextFill(Color.WHITE);
        return_outside.setStyle("-fx-background-color: #6225E6; ");
        return_outside.setFont(Font.font("Baloo 2 Bold", 16));
        return_outside.setId("outside");
        return_outside.setOnAction(event -> FXMLButtonEventHandlers.returnHome(application));
        root.getChildren().add(return_outside);

        // image
        ImageView cauldron = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/cauldron.gif"));
        cauldron.setFitWidth(300);
        cauldron.setFitHeight(300);
        cauldron.setLayoutX(850);
        cauldron.setLayoutY(375);
        root.getChildren().add(cauldron);

        return this;
    }

    public FXMLStageBuilder bakingSceneSetup(){

        List<String[]> flour_data = application.getAdaptor().getIngredientInventory("Flour");
        List<String[]> water_data = application.getAdaptor().getIngredientInventory("Water");
        List<String[]> yeast_data = application.getAdaptor().getIngredientInventory("Yeast");
        List<String[]> salt_data = application.getAdaptor().getIngredientInventory("Salt");

        List<String> empty_ingredient_types = new ArrayList<>();

        if (flour_data.isEmpty()){
            empty_ingredient_types.add("Flour");
        }
        if (water_data.isEmpty()){
            empty_ingredient_types.add("Water");
        }
        if (yeast_data.isEmpty()){
            empty_ingredient_types.add("Yeast");
        }
        if (salt_data.isEmpty()){
            empty_ingredient_types.add("Salt");
        }

        if (!empty_ingredient_types.isEmpty()){
            StringBuilder missing_ingredients_names = new StringBuilder();
            for (String emptyIngredientType : empty_ingredient_types) {
                missing_ingredients_names.append("\n").append(emptyIngredientType);
            }
            Label missing_ingredients_label = new Label("You don't have all the required ingredients to bake a bread in your inventory. Go to the cave and collect:" + missing_ingredients_names);
            missing_ingredients_label.setLayoutX(118);
            missing_ingredients_label.setLayoutY(235);
            missing_ingredients_label.setPrefSize(600, USE_COMPUTED_SIZE);
            missing_ingredients_label.setFont(Font.font("Baloo 2", 24));
            missing_ingredients_label.setTextFill(Color.WHITE);
            missing_ingredients_label.setWrapText(true);
            missing_ingredients_label.setAlignment(Pos.TOP_CENTER);
            missing_ingredients_label.setTextAlignment(TextAlignment.CENTER);
            missing_ingredients_label.setStyle("-fx-background-color: rgb(0,0,0,0.5);");
            root.getChildren().add(missing_ingredients_label);
        }
        else{
            Label ask_to_start_baking_label = new Label("You have all the required ingredients for bread-baking. Care to dance?");
            ask_to_start_baking_label.setLayoutX(118);
            ask_to_start_baking_label.setLayoutY(333);
            ask_to_start_baking_label.setPrefSize(600, USE_COMPUTED_SIZE);
            ask_to_start_baking_label.setFont(Font.font("Baloo 2", 24));
            ask_to_start_baking_label.setTextFill(Color.WHITE);
            ask_to_start_baking_label.setWrapText(true);
            ask_to_start_baking_label.setId("startlabel");
            ask_to_start_baking_label.setAlignment(Pos.TOP_CENTER);
            ask_to_start_baking_label.setTextAlignment(TextAlignment.CENTER);
            ask_to_start_baking_label.setStyle("-fx-background-color: rgb(0,0,0,0.5);");

            Button start_baking = new Button("Let's go!");
            start_baking.setLayoutX(343);
            start_baking.setLayoutY(446);
            start_baking.setPrefSize(150, 30);
            start_baking.setTextFill(Color.WHITE);
            start_baking.setStyle("-fx-background-color: #6225E6; ");
            start_baking.setFont(Font.font("Baloo 2 Bold", 16));
            start_baking.setId("start");
            start_baking.setOnAction(event -> FXMLButtonEventHandlers.bakingNextClicked(application, event, new ArrayList<>(), null));
            root.getChildren().addAll(ask_to_start_baking_label, start_baking);
        }

        return this;
    }

    public FXMLStageBuilder removeOutsideButton(){
        removeNodeById(root, "outside");
        return this;
    }

    public static void removeNodeById(AnchorPane root, String id){
        ObservableList<Node> children = root.getChildren();
        for (Node child: children){
            if (Objects.equals(child.getId(), id)){
                children.remove(child);
                break;
            }
        }
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

    public static TableColumn<String[], String> populateBakingColumns(int column_index) {
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


    public FXMLStageBuilder addBakingSceneButton(String location_of_button){


        ImageView baking_house_particles = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/baking_house_particles.gif"));

        AnchorPane.setLeftAnchor(baking_house_particles, 238.0);
        AnchorPane.setTopAnchor(baking_house_particles, 200.0);
        baking_house_particles.setOpacity(0.35);
        root.getChildren().add(baking_house_particles);


        Button baking_scene_button = new Button();
        baking_scene_button.setLayoutX(238);
        baking_scene_button.setLayoutY(200);
        baking_scene_button.setPrefWidth(187.0);
        baking_scene_button.setPrefHeight(222.0);
        baking_scene_button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");




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
        ImageView legoPlayerImageView = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/player.gif"));
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

        // button
        Button return_home_button = new Button("Return Home");
        return_home_button.setLayoutX(1201);
        return_home_button.setLayoutY(15);
        return_home_button.setPrefSize(150, 30);
        return_home_button.setTextFill(Color.WHITE);
        return_home_button.setStyle("-fx-background-color: #6225E6; ");
        return_home_button.setFont(Font.font("Baloo 2 Bold", 16));
        return_home_button.setId("outside");
        return_home_button.setOnAction(event -> FXMLButtonEventHandlers.returnHome(application));
        root.getChildren().add(return_home_button);

        return this;
    }

    public FXMLStageBuilder fightRoomSetup(){
        // Rectangle background
        Rectangle background = new Rectangle(1366, 768, Color.web("#d7d6d6"));
        root.getChildren().add(background);

        // background image
        ImageView backgroundImage = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/fight_background.png"));
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
        ImageView playerImage = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/player.gif"));
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
            double x = getXLocationForFight(box_number);

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

        double x = getXLocationForFight(die_index);

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

    private double getXLocationForFight(int die_index) {
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
        dino_button.setOnAction(event -> FXMLButtonEventHandlers.fightDinosaur(application,location));
        root.getChildren().add(dino_button);
    }


    private void addIngredientToLocation(Stage stage, AnchorPane root, int location, String image_url){
        Button ingredient_button = addButtonToLocation(location);
        ImageView ingredient_image = new ImageView(new Image(image_url));
        ingredient_button.setGraphic(ingredient_image);
        ingredient_button.setOnAction(event -> FXMLButtonEventHandlers.collectIngredient(application, location));
        root.getChildren().add(ingredient_button);
    }

    private Button addButtonToLocation(int location){
        Button button = new Button("");;
        switch (location) { // add the dinosaur to correct location
            case 0: // Top left spawn position
                button.setLayoutX(125);
                button.setLayoutY(80);
                break;
            case 1: // Top right spawn position
                button.setLayoutX(1041);
                button.setLayoutY(80);
                break;
            case 2: // Above left spawn point
                button.setLayoutX(350);
                button.setLayoutY(130);
                break;
            case 3:// Above right spawn point
                button.setLayoutX(816);
                button.setLayoutY(130);
                break;
            case 4: // Bottom left spawn position
                button.setLayoutX(125);
                button.setLayoutY(450);
                break;
            case 5: // Bottom right spawn position
                button.setLayoutX(1041);
                button.setLayoutY(450);
                break;
            case 6: // Below left spawn point
                button.setLayoutX(350);
                button.setLayoutY(400);
                break;
            case 7:
                button.setLayoutX(816);
                button.setLayoutY(400);
                break;
            default:
                // some sort of error here, we didn't get a valid input!
                break;
        }

        button.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        return button;
    }

    public FXMLStageBuilder generateGameWinScene(){
        // background image
        ImageView background_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/game_win_background.jpg"));
        background_image.setFitWidth(1366);
        background_image.setFitHeight(768);
        background_image.setLayoutX(0);
        background_image.setLayoutY(0);

        // entity images
        ImageView player_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/player.gif"));
        player_image.setFitWidth(360);
        player_image.setFitHeight(349);
        player_image.setPreserveRatio(true);
        player_image.setLayoutX(94);
        player_image.setLayoutY(261);

        ImageView baby_dino_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/dino1.100x.gif"));
        baby_dino_image.setFitWidth(125);
        baby_dino_image.setFitHeight(150);
        baby_dino_image.setPreserveRatio(true);
        baby_dino_image.setLayoutX(1027);
        baby_dino_image.setLayoutY(465);

        ImageView mommy_dino_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/dino2.100x.gif"));
        mommy_dino_image.setFitWidth(150);
        mommy_dino_image.setFitHeight(200);
        mommy_dino_image.setPreserveRatio(true);
        mommy_dino_image.setLayoutX(1095);
        mommy_dino_image.setLayoutY(439);

        ImageView daddy_dino_image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/dino3.100x.gif"));
        daddy_dino_image.setFitWidth(150);
        daddy_dino_image.setFitHeight(400);
        daddy_dino_image.setPreserveRatio(true);
        daddy_dino_image.setLayoutX(1215);
        daddy_dino_image.setLayoutY(436);
        root.getChildren().addAll(background_image,player_image, baby_dino_image, mommy_dino_image,daddy_dino_image);


        // text
        Ellipse text_outline = new Ellipse(0, 0, 250, 100);
        text_outline.setLayoutX(310);
        text_outline.setLayoutY(170);
        text_outline.setFill(Paint.valueOf("#ffc120"));

        Label top_text = new Label("The Quest for Breadfest");
        top_text.setLayoutX(60);
        top_text.setLayoutY(89);
        top_text.setPrefSize(500, 100);
        top_text.setFont(Font.font("Baloo 2", 30));
        top_text.setTextFill(Color.BLACK);
        top_text.setAlignment(Pos.CENTER);
        top_text.setTextAlignment(TextAlignment.CENTER);

        Label middle_text = new Label("A Game by Joseph Allred & Gustav Cedergrund");
        middle_text.setLayoutX(60);
        middle_text.setLayoutY(144);
        middle_text.setPrefSize(500, 79);
        middle_text.setFont(Font.font("Baloo 2", 23));
        middle_text.setTextFill(Color.BLACK);
        middle_text.setAlignment(Pos.CENTER);
        middle_text.setTextAlignment(TextAlignment.CENTER);

        Label bottom_text = new Label("with art/sounds from Cobi Granger and Rachel Suter");
        bottom_text.setLayoutX(60);
        bottom_text.setLayoutY(193);
        bottom_text.setPrefSize(500, 30);
        bottom_text.setFont(Font.font("Baloo 2", 16));
        bottom_text.setTextFill(Color.BLACK);
        bottom_text.setAlignment(Pos.CENTER);
        bottom_text.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().addAll(text_outline,top_text,middle_text, bottom_text);

        // back to game button
        Button button = new Button("back to game");
        button.setLayoutX(1198);
        button.setLayoutY(707);
        button.setPrefSize(150, 30);
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #6225E6; ");
        button.setFont(Font.font("Baloo 2 Bold", 16));
        button.setOnAction(event -> FXMLButtonEventHandlers.returnHome(application));
        root.getChildren().add(button);

        return this;
    }

}



