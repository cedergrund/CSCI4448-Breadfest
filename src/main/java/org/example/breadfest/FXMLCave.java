package org.example.breadfest;

import javafx.scene.control.TableView;
import org.example.breadfest.ingredients.Ingredient;

import java.util.List;

public interface FXMLCave {

    String getBackgroundImage();

    List<Character> getRoomExitDirections();

    List<String> getObjectsAtAllLocations();

    String[] getObjectByLocation(int location);

    String getDinosaurImageByLocation(int locationIndex);

    String getIngredientImageByLocation(int locationIndex);

    void clickLocation(int location);

    boolean isValidIngredientList(List<String> ingredient_type_list);

    void bakeIngredientsFromTable(TableView<String[]> table);

    boolean moveRoom(char direction);

    List<String[]> getIngredientInventory();

    Ingredient removeIngredientFromInventory(String ingredient_name);

    int getCurrPlayerPatience();

    int changeCurrHonor(int honor_change);

    void enterRoom0();

    int getMaxPlayerPatience();

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

