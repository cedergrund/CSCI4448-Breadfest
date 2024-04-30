package org.example.breadfest;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.example.breadfest.FXMLStageBuilder.populateBakingColumns;
import static org.example.breadfest.FXMLStageBuilder.removeNodeById;


public class popUps {

    static private void setUpBase(AnchorPane root){
        // scroll background image
        ImageView scroll_background = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/scroll.png"));
        scroll_background.setFitWidth(600);
        scroll_background.setFitHeight(650);
        scroll_background.setLayoutX(383);
        scroll_background.setLayoutY(59);
        scroll_background.setPreserveRatio(false);
        root.getChildren().add(scroll_background);
    }

    static private Button addBottomButton(String text){
        Button button = new Button(text);
        button.setLayoutX(606);
        button.setLayoutY(560);
        button.setPrefSize(150, 30);
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #6225E6; ");
        button.setFont(Font.font("Baloo 2 Bold", 16));
        return button;
    }

    static private void addTitleAndDescription(AnchorPane root, String title_text, String description_text){
        Label title = new Label(title_text);
        title.setLayoutX(383);
        title.setLayoutY(172);
        title.setPrefSize(600, 47);
        title.setFont(Font.font("Baloo 2", 30));
        title.setUnderline(true);
        title.setTextFill(Color.BLACK);
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);

        Label description = new Label(description_text);
        description.setLayoutX(433);
        description.setLayoutY(225);
        description.setPrefSize(500, 200);
        description.setFont(Font.font("Baloo 2", 20));
        description.setWrapText(true);
        description.setTextFill(Color.BLACK);
        description.setAlignment(Pos.TOP_CENTER);
        description.setTextAlignment(TextAlignment.CENTER);

        root.getChildren().addAll(title, description);
    }

    static private void addCenterBoxWithTwoSlots(AnchorPane root){
        Rectangle outline = new Rectangle(500, 215, Color.web("#941d1d"));
        outline.setLayoutX(437);
        outline.setLayoutY(312);
        outline.setStrokeWidth(2);
        outline.setStroke(Color.BLACK);
        outline.setFill(Color.TRANSPARENT);
        root.getChildren().add(outline);

        Line horizontal_divider = new Line(437, 419.5, 937, 419.5);
        horizontal_divider.setStrokeWidth(1);
        horizontal_divider.setStroke(Color.BLACK);
        root.getChildren().add(horizontal_divider);
    }

    static private void addTopImageForTwoSlots(AnchorPane root, String image_file, String label_text){
        ImageView image = new ImageView(new Image(image_file));
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setLayoutX(458);
        image.setLayoutY(312);

        Label label = new Label(label_text);
        label.setLayoutX(608);
        label.setLayoutY(312);
        label.setPrefSize(300, 100);
        label.setFont(Font.font("Baloo 2", 25));
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setTextAlignment(TextAlignment.LEFT);

        root.getChildren().addAll(image, label);
    }

    static private void addBottomImageForTwoSlots(AnchorPane root, String image_file, String label_text){
        ImageView image = new ImageView(new Image(image_file));
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setLayoutX(804);
        image.setLayoutY(427);

        Label label = new Label(label_text);
        label.setLayoutX(454);
        label.setLayoutY(427);
        label.setPrefSize(300, 100);
        label.setFont(Font.font("Baloo 2", 25));
        label.setTextFill(Color.BLACK);
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setTextAlignment(TextAlignment.RIGHT);

        root.getChildren().addAll(image, label);
    }

    static public void popUpFightResults(FXMLCaveApplication application, Stage stage, boolean die_conflict){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();
        String[] rewards = application.getAdaptor().getPreviousReward();

        setUpBase(root);

        // adding text
        addTitleAndDescription(root, "You Win!", rewards[0] + " got bored of gambling and left. But, as a reward for beating him, he left you something...");

        addCenterBoxWithTwoSlots(root);

        // ingredient
        addTopImageForTwoSlots(root, application.getAdaptor().getIngredientImageByType(rewards[3]), rewards[2] + " Ingredient:\n" + rewards[1]);

        // button
        Button button = addBottomButton("Say thank you...");
        if (die_conflict){
            button.setOnAction(event -> FXMLButtonEventHandlers.switchDieScene(application));
        }
        else {
            button.setOnAction(event -> FXMLButtonEventHandlers.exitFight(application));
        }
        root.getChildren().add(button);

        if (Objects.equals(rewards[4], "")){
            return;
        }

        // die
        addBottomImageForTwoSlots(root, "file:src/main/resources/org/example/breadfest/images/reward_die.png", rewards[5] + " Die:\n" + rewards[4]);
    }

    public static void popUpNuclear(FXMLCaveApplication application, Stage stage){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();

        removeNodeById(root, "outside");
        removeNodeById(root, "start");

        setUpBase(root);

        // adding text
        addTitleAndDescription(root, "Hold on...", "You didn't just bake with a nuclear ingredient, RIGHT?!?\n" +
                "oh no.................................\n" +
                "WHAT HAVE YOU DONE!!!");


        ImageView image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/shocked_turtle.png"));
        image.setFitWidth(130);
        image.setFitHeight(210);
        image.setPreserveRatio(true);
        image.setLayoutX(618);
        image.setLayoutY(342);
        root.getChildren().add(image);

        // button
        Button button = addBottomButton("what is it?");
        button.setOnAction(event -> FXMLButtonEventHandlers.rawrdoughValleyExplodes(application));
        root.getChildren().add(button);

    }

    public static void popUpPatienceExhausted(FXMLCaveApplication application, Stage stage){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();

        setUpBase(root);

        // adding text
        addTitleAndDescription(root, "Patience Exhausted...", "All this cave exploration and dinosaur gambling is taxing work, and your patience has run all the way out. How about you go home and take the rest of the day off - you deserve it!");

        ImageView image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/sweeepy_kitty.png"));
        image.setFitWidth(200);
        image.setFitHeight(134);
        image.setPreserveRatio(true);
        image.setLayoutX(583);
        image.setLayoutY(400);
        root.getChildren().add(image);

        // button
        Button button = addBottomButton("good idea!");
        button.setOnAction(event -> FXMLButtonEventHandlers.returnHome(application));
        root.getChildren().add(button);

        removeNodeById(root, "patience_bar");
        removeNodeById(root, "patience_label");

        getNewPatienceBar(application, root);
    }

    public static void popUpNextBakingIngredient(FXMLCaveApplication application, Stage stage, String curr_ingredient, List<String[]> baked_ingredients){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();
        removeNodeById(root, "top_text");

        Label top_text;
        Button next_ingredient;
        switch (curr_ingredient){
            case "Flour":{
                top_text = new Label("First, choose a flour...");
                next_ingredient = new Button("next...");
                break;
            }
            case "Water":{
                top_text = new Label("Next, choose a water...");
                next_ingredient = new Button("next...");
                break;
            }
            case "Yeast":{
                top_text = new Label("Then, choose a yeast...");
                next_ingredient = new Button("next...");
                break;
            }
            case "Salt":{
                top_text = new Label("Now, choose a salt...");
                next_ingredient = new Button("next...");
                break;
            }
            case "Topping":{
                top_text = new Label("Finally, you may choose a topping...");
                next_ingredient = new Button("Bake bread...");
                break;
            }
            default:{
                top_text = new Label("");
                next_ingredient = new Button("");
            }
        }
        top_text.setLayoutX(118);
        top_text.setLayoutY(200);
        top_text.setPrefSize(600, 74);
        top_text.setFont(Font.font("Baloo 2", 25));
        top_text.setTextFill(Color.WHITE);
        top_text.setAlignment(Pos.TOP_CENTER);
        top_text.setId("top_text");
        top_text.setTextAlignment(TextAlignment.CENTER);
        top_text.setStyle("-fx-background-color: rgb(0,0,0,0.5);");
        root.getChildren().add(top_text);

        TableView<String[]> table_view = new TableView<>();
        table_view.setPrefSize(600, 400);
        table_view.setLayoutX(118);
        table_view.setLayoutY(235);
        table_view.setId("table"+curr_ingredient);
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

        List<String[]> ingredients_data = application.getAdaptor().getIngredientInventory(curr_ingredient);
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(ingredients_data);

        if (data.isEmpty()){
            Label missing_toppings = new Label("You don't have any toppings in your inventory, but that's okay - you don't need toppings to bake a bread!");
            missing_toppings.setLayoutX(118);
            missing_toppings.setLayoutY(300);
            missing_toppings.setWrapText(true);
            missing_toppings.setPrefSize(600, USE_COMPUTED_SIZE);
            missing_toppings.setFont(Font.font("Baloo 2", 22));
            missing_toppings.setTextFill(Color.WHITE);
            missing_toppings.setAlignment(Pos.TOP_CENTER);
            missing_toppings.setTextAlignment(TextAlignment.CENTER);
            missing_toppings.setStyle("-fx-background-color: rgb(0,0,0,0.5);");
            top_text.setPrefSize(600, USE_COMPUTED_SIZE);
            root.getChildren().add(missing_toppings);
            removeNodeById(root, "tableFlour");
            removeNodeById(root, "tableWater");
            removeNodeById(root, "tableYeast");
            removeNodeById(root, "tableSalt");
            removeNodeById(root, "start");
            removeNodeById(root, "startlabel");
        }
        else{
            int num_columns = ingredients_data.get(0).length - 1;

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

            for (int column_index = 0; column_index < num_columns; column_index++) {
                TableColumn<String[], String> column = populateBakingColumns(column_index);
                table_view.getColumns().add(column);
            }
            table_view.setItems(data);
            table_view.setStyle("-fx-border-color: #0E0A06; -fx-border-width: 2px;");
            root.getChildren().add(table_view);
        }


        next_ingredient.setLayoutX(343);
        next_ingredient.setLayoutY(645);
        next_ingredient.setPrefSize(150, 30);
        next_ingredient.setTextFill(Color.WHITE);
        next_ingredient.setStyle("-fx-background-color: #6225E6; ");
        next_ingredient.setFont(Font.font("Baloo 2 Bold", 16));
        next_ingredient.setId(curr_ingredient);

        next_ingredient.setOnAction(event -> FXMLButtonEventHandlers.bakingNextClicked(application, event, baked_ingredients, table_view));
        root.getChildren().add(next_ingredient);

    }

    public static void popUpGameWin(FXMLCaveApplication application, Stage stage){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();

        setUpBase(root);

        // adding text
        addTitleAndDescription(root, "A Quest Complete...", "After finishing your bake, you see a note was slipped under your door. You pick it up, and it reads:");

        Label letter = new Label("To whom it may concern,                                   \n" +
                "     As a result of your tremendous success in baking, the 'Bosses of Rawrdough-valley's Edible Assemblages and Delights' (B.R.E.A.D.) cordially request your attendance at this year's Breadfest. Congrats on this acccomplishment, Master Baker!");
        letter.setLayoutX(433);
        letter.setLayoutY(312);
        letter.setPrefSize(500, 160);
        letter.setFont(Font.font("Verdana", 19));
        letter.setWrapText(true);
        letter.setTextFill(Color.BLACK);
        letter.setAlignment(Pos.TOP_CENTER);
        letter.setTextAlignment(TextAlignment.CENTER);
        root.getChildren().add(letter);


        // bread image
        ImageView image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/bread.GIF"));
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setLayoutX(633);
        image.setLayoutY(458);
        root.getChildren().add(image);

        // button
        Button button = addBottomButton("wow...");
        button.setOnAction(event -> FXMLButtonEventHandlers.creditsScreen(application));
        root.getChildren().add(button);

    }

    public static void popUpBreadResult(FXMLCaveApplication application, Stage stage, String[] bread_results){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();
        int upgrade = Integer.parseInt(bread_results[0]);
        if (upgrade == -1){
            application.nuclearIngredientUsed();
            return;
        }

        Label results = new Label("Congratulations! You've made:\n" +
                bread_results[2] + "\n" +
                "and it earned you " + bread_results[1] + " honor.\n" +
                "Good Work!");
        results.setLayoutX(58);
        results.setLayoutY(275);
        results.setPrefSize(720, USE_COMPUTED_SIZE);
        results.setFont(Font.font("Baloo 2", 24));
        results.setTextFill(Color.WHITE);
        results.setAlignment(Pos.CENTER);
        results.setWrapText(true);
        results.setTextAlignment(TextAlignment.CENTER);
        results.setStyle("-fx-background-color: rgb(0,0,0,0.5);");

        Button continue_button = new Button("Let's go!");
        continue_button.setLayoutX(343);
        continue_button.setLayoutY(454);
        continue_button.setPrefSize(150, 30);
        continue_button.setTextFill(Color.WHITE);
        continue_button.setStyle("-fx-background-color: #6225E6; ");
        continue_button.setFont(Font.font("Baloo 2 Bold", 16));
        continue_button.setOnAction(event -> FXMLButtonEventHandlers.openBakingScene(application, upgrade));
        root.getChildren().addAll(results, continue_button);

        // bread image
        ImageView image = new ImageView(new Image("file:src/main/resources/org/example/breadfest/images/bread.GIF"));
        image.setFitWidth(170);
        image.setFitHeight(170);
        image.setLayoutX(915);
        image.setLayoutY(246);
        root.getChildren().add(image);
    }

    public static void popUpPlayerUpgrade(FXMLCaveApplication application, Stage stage, int upgrade){

        if (upgrade == 1 || upgrade == 0){
            // returns 0 if you made dust, 1 if no upgrade
            return;
        }
        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();
        removeNodeById(root, "outside");
        removeNodeById(root, "start");

        if (upgrade == 5){
            // game is won!!
            application.maxUpgradeReached();
            return;
        }


        setUpBase(root);

        // adding text
        addTitleAndDescription(root, "Your Glory is Growing!", "Your baking skills are on-the-up, and people are taking notice! You get sent some gifts in the mail...");

        addCenterBoxWithTwoSlots(root);

        String upper_text;
        String lower_text;

        switch (upgrade){
            case 2:{
                upper_text = "A Cool Glass of Water\n" +
                        "━━━━━━━━━━━━━━━♡";
                lower_text = "A Menacing Stare\n" +
                        "★━━━━━━━━━━━━━━━━━━";
                break;
            }
            case 3:{
                upper_text = "Scented Candles\n" +
                        "━━━━━━━━━━━━━━━♡";
                lower_text = "Light-up Shoes\n" +
                        "★━━━━━━━━━━━━━━━━━━";
                break;
            }
            case 4:{
                upper_text = "A Positive Perspective\n" +
                        "━━━━━━━━━━━━━━━♡";
                lower_text = "Really Smelly Farts\n" +
                        "★━━━━━━━━━━━━━━━━━━";
                break;
            }
            default:{
                upper_text = "";
                lower_text = "";
            }
        }

        // ingredient
        addTopImageForTwoSlots(root, "file:src/main/resources/org/example/breadfest/images/calm_boy.png", upper_text);
        Label patience_increase_explanation = new Label("Your patience increases...");
        patience_increase_explanation.setLayoutX(608);
        patience_increase_explanation.setLayoutY(320);
        patience_increase_explanation.setPrefSize(300, 100);
        patience_increase_explanation.setFont(Font.font("Baloo 2", 20));
        patience_increase_explanation.setTextFill(Color.BLACK);
        patience_increase_explanation.setAlignment(Pos.CENTER_LEFT);

        // die
        addBottomImageForTwoSlots(root, "file:src/main/resources/org/example/breadfest/images/menacing_bear.png", lower_text);
        Label damage_multiplier_explanation = new Label("Dinosaurs lose patience faster...");
        damage_multiplier_explanation.setLayoutX(454);
        damage_multiplier_explanation.setLayoutY(435);
        damage_multiplier_explanation.setPrefSize(300, 100);
        damage_multiplier_explanation.setFont(Font.font("Baloo 2", 20));
        damage_multiplier_explanation.setTextFill(Color.BLACK);
        damage_multiplier_explanation.setAlignment(Pos.CENTER_RIGHT);
        damage_multiplier_explanation.setTextAlignment(TextAlignment.RIGHT);

        root.getChildren().addAll(patience_increase_explanation, damage_multiplier_explanation);

        // button
        Button button = addBottomButton("How Great!");
        button.setOnAction(event -> FXMLButtonEventHandlers.openBakingScene(application, 0));
        root.getChildren().add(button);


    }

    public static void popUpDieConflict(FXMLCaveApplication application, Stage stage){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();

        setUpBase(root);

        addTitleAndDescription(root, "Max Dice Inventory Reached!", "Pick a Die to give up");

        Label quote = new Label("\"There is no progress or accomplishment without sacrifice.\" -William Warren Wright");
        quote.setLayoutX(433);
        quote.setLayoutY(530);
        quote.setPrefSize(500, 75);
        quote.setFont(Font.font("Verdana Italic", 18));
        quote.setWrapText(true);
        quote.setTextFill(Color.BLACK);
        quote.setAlignment(Pos.CENTER);
        quote.setTextAlignment(TextAlignment.CENTER);

        Rectangle outline = new Rectangle(500, 230, Color.web("#941d1d"));
        outline.setLayoutX(433);
        outline.setLayoutY(295);
        outline.setStrokeWidth(2);
        outline.setStroke(Color.BLACK);
        outline.setFill(Color.TRANSPARENT);
        root.getChildren().add(outline);

        Line horizontal_divider = new Line(433, 410, 933, 410);
        horizontal_divider.setStrokeWidth(1);
        horizontal_divider.setStroke(Color.BLACK);
        Line vertical_divider = new Line(683, 295, 683, 525);
        vertical_divider.setStrokeWidth(1);
        vertical_divider.setStroke(Color.BLACK);
        root.getChildren().addAll(horizontal_divider, vertical_divider, quote);


        addDieForRemoving(application,root,0);
        addDieForRemoving(application,root,1);
        addDieForRemoving(application,root,2);
        addDieForRemoving(application,root,3);
    }

    // Methods to add dice buttons
    private static void addDieForRemoving(FXMLCaveApplication application, AnchorPane root, int die_index) {

        double x;
        double y;
        switch (die_index){
            case 0:{
                x = 433;
                y = 295;
                break;
            }
            case 1:{
                x = 683;
                y = 295;
                break;
            }
            case 2:{
                x = 433;
                y = 410;
                break;
            }
            default:{
                x = 683;
                y = 410;
                break;
            }
        }

        String[] die_information = application.getAdaptor().getDieInformation(die_index);
        Label die_name = new Label(die_information[0]);
        Label description = new Label(die_information[1]);

        die_name.setLayoutX(x+10);
        die_name.setLayoutY(y+5);
        die_name.setPrefSize(160,31);
        die_name.setAlignment(Pos.CENTER_LEFT);
        die_name.setTextAlignment(TextAlignment.LEFT);
        die_name.setUnderline(true);
        die_name.setFont(Font.font("Baloo 2 Regular", 15));

        description.setLayoutX(x+10);
        description.setLayoutY(y+40);
        description.setPrefSize(220, 70);
        description.setFont(Font.font("Baloo 2 Regular", 12));
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.LEFT);
        description.setAlignment(Pos.TOP_LEFT);

        root.getChildren().addAll(die_name, description);

        // add button
        Button button = new Button("Remove");
        button.setLayoutX(x+170);
        button.setLayoutY(y+6);
        button.setPrefSize(70, 26);
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #c71e12");
        button.setId(String.valueOf(die_index));
        button.setFont(Font.font("Baloo 2 Bold", 14));
        button.setOnAction(event -> FXMLButtonEventHandlers.switchDieAndExit(application, event));
        root.getChildren().add(button);
    }

    private static void getNewPatienceBar(FXMLCaveApplication application, AnchorPane root) {
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
        patience_label.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        patience_label.setTextFill(Color.WHITE);
        AnchorPane.setLeftAnchor(patience_label, 20.0);
        AnchorPane.setBottomAnchor(patience_label, 40.0);
        root.getChildren().add(patience_label);
    }
}
