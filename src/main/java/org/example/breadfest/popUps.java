package org.example.breadfest;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Objects;

public class popUps {

    static private void setUpBase(AnchorPane root){
        // scroll background image
        ImageView scroll_background = new ImageView(new Image("file:src/main/resources/org/example/breadfest/Images/scroll.png"));
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
        description.setPrefSize(500, 75);
        description.setFont(Font.font("Baloo 2", 20));
        description.setWrapText(true);
        description.setTextFill(Color.BLACK);
        description.setAlignment(Pos.CENTER);
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
        label.setAlignment(Pos.BOTTOM_LEFT);
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
        label.setAlignment(Pos.TOP_RIGHT);
        label.setTextAlignment(TextAlignment.RIGHT);

        root.getChildren().addAll(image, label);
    }

    static public Stage popUpFightResults(FXMLCaveApplication application, Stage stage, boolean die_conflict){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();
        String[] rewards = application.getAdaptor().getPreviousReward();

        setUpBase(root);

        // button
        Button button = addBottomButton("Say thank you...");
        if (die_conflict){
            button.setOnAction(event -> FXMLButtonEventHandlers.switchDieScene(application));
        }
        else {
            button.setOnAction(event -> FXMLButtonEventHandlers.exitFight(application));
        }
        root.getChildren().add(button);

        // adding text
        addTitleAndDescription(root, "You Win!", rewards[0] + " got bored of gambling and left. But, as a reward for beating him, he left you something...");

        addCenterBoxWithTwoSlots(root);

        // ingredient
        addTopImageForTwoSlots(root, application.getAdaptor().getIngredientImageByType(rewards[3]), rewards[2] + " Ingredient:\n" + rewards[1]);

        if (Objects.equals(rewards[4], "")){
            return stage;
        }

        // die
        addBottomImageForTwoSlots(root, "file:src/main/resources/org/example/breadfest/Images/reward_die.png", rewards[5] + " Die:\n" + rewards[4]);

        return stage;

    }

    static public Stage popUpPlayerUpgrade(FXMLCaveApplication application, Stage stage, int upgrade){

        if (upgrade <= 1){
            return stage;
        }
        if (upgrade == 5){
            System.out.println("Game won!");
            return stage;
        }

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();

        setUpBase(root);

        // button
        Button button = addBottomButton("How Great!");
        button.setOnAction(event -> FXMLButtonEventHandlers.openBakingScene(application, 0));
        root.getChildren().add(button);

        // adding text
        addTitleAndDescription(root, "Your Glory is Growing!", "Your baking skills are on-the-up, and people are taking notice! You get sent some gifts in the mail...");

        addCenterBoxWithTwoSlots(root);

        String upper_text;
        String lower_text;

        switch (upgrade){
            case 2:{
                upper_text = "Cool Glass of Water\n";
                lower_text = "Menacing Stare:\nDinos lose patience faster";
                break;
            }
            case 3:{
                upper_text = "Mediation Book\n";
                lower_text = "Light-up Shoes:\nDinos lose patience faster";
                break;
            }
            case 4:{
                upper_text = "A Positive Perspective:\nYour patience increases.";
                lower_text = "Really Smelly Farts:\nDinos lose patience faster";
                break;
            }
            default:{
                upper_text = "";
                lower_text = "";
            }
        }

        // ingredient
        addTopImageForTwoSlots(root, "file:src/main/resources/org/example/breadfest/Images/calm_boy.png", upper_text);

        // die
        addBottomImageForTwoSlots(root, "file:src/main/resources/org/example/breadfest/Images/menacing_bear.png", lower_text);

        return stage;

    }

    static public Stage popUpDieConflict(FXMLCaveApplication application, Stage stage){

        AnchorPane root = (AnchorPane)  stage.getScene().getRoot();
        String[] rewards = application.getAdaptor().getPreviousReward();

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

        return stage;

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
}
