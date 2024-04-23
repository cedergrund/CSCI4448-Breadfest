package org.example.breadfest;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurAndDiceTypes;
import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CaveGameTest {

    @Test
    void playerFightsDinosaur() throws Exception {
//        String dinosaur_test_name = "dinosaur_test_name";
//        DinosaurAndDiceTypes dinosaur_test_type = DinosaurAndDiceTypes.Common;
//        Dinosaur dinosaur_test = new Dinosaur(dinosaur_test_name, dinosaur_test_type);
//
//        CaveGame cave_game_test = new CaveGame();
//        assertNotNull(cave_game_test);
//        cave_game_test.attackDinosaur(dinosaur_test);

    }

    @Test
    void getBackgroundImage() {
    }

    @Test
    void playerCollectsIngredient() {
        CaveGame cave_game_test = new CaveGame();
        assertNotNull(cave_game_test);

        String ingredient_name_test = "ingredient_name_test";
        IngredientRarity ingredient_rarity_test = IngredientRarity.Common;
        IngredientTypes ingredient_type_test = IngredientTypes.Flour;

        Ingredient ingredient_test = new Ingredient(ingredient_name_test, ingredient_type_test, ingredient_rarity_test);

        int location_test = 0;

        cave_game_test.playerCollectsIngredient(ingredient_test, location_test);
        assertNotNull(cave_game_test);
    }

    @Test
    void getRoomExitDirections() {
        CaveGame cave_game_test = new CaveGame();
        List<Character> direction_list_test = cave_game_test.getRoomExitDirections();
        assertNotNull(direction_list_test);
    }

    @Test
    void getObjectsAtAllLocations() {
        CaveGame cave_game_test = new CaveGame();
        List<String> object_list_test = cave_game_test.getObjectsAtAllLocations();
        assertNotNull(object_list_test);
    }

    @Test
    void clickLocation() {
        CaveGame cave_game_test = new CaveGame();
        int location_test = 0;
        cave_game_test.clickLocation(location_test);

        assertNotNull(cave_game_test);
    }

    @Test
    void getIngredientInventory() {
        CaveGame cave_game_test = new CaveGame();
        List<String[]> ingredient_inventory_test = cave_game_test.getIngredientInventory();

        assertNotNull(ingredient_inventory_test);
    }

    @Test
    void moveRoom() {
        CaveGame cave_game_test = new CaveGame();
        char direction_test = 'N';
        boolean move_test = cave_game_test.moveRoom(direction_test);

        assertNotNull(move_test);
    }

    @Test
    void enterRoom0() {
        CaveGame cave_game_test = new CaveGame();
        cave_game_test.enterRoom0();

        assertNotNull(cave_game_test);
    }

    @Test
    void getObjectByLocation() {
        CaveGame cave_game_test = new CaveGame();

        String[] object_string_test = cave_game_test.getObjectByLocation(0);
        assertNull(object_string_test);
    }
}