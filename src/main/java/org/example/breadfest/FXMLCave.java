package org.example.breadfest;

import javafx.scene.control.TableView;
import org.example.breadfest.ingredients.Ingredient;

import java.util.List;

public interface FXMLCave {

    List<Character> getRoomExitDirections();

    List<String> getObjectsAtAllLocations();

    String[] getObjectByLocation(int location);

    String getDinosaurImageByLocation(int locationIndex);

    String getIngredientImageByLocation(int locationIndex);

    void clickLocation(int location);

    String[] bakeIngredientsFromTable(List<String[]> baked_ingredients);

    boolean moveRoom(char direction);

    List<String[]> getIngredientInventory();

    List<String[]> getIngredientInventory(String type);

    int getCurrPlayerPatience();

    void enterRoom0();

    int getMaxPlayerPatience();

    int getCurrPlayerHonor();

    String[] fightDinosaur(int dice_rolled);

    void regenerateCaveSystem();

    void updateActiveDice(int die_to_switch);

    String[] getFightersInformation();

    String getDinoImage();

    String getIngredientImageByType(String type);

    String[] getDieInformation(int die_index);

    boolean stopFight(boolean won_fight);

    String[] getPreviousReward();
}

