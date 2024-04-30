package org.example.breadfest;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurAndDiceTypes;
import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientFactory;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

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
//        cave_game_test.fightDinosaur(7);

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

    @Test
    void testPlayerCollectsIngredient() {
        CaveGame test_game = new CaveGame();
        Boolean test_in_inventory = false;
        for (String[] ingredient: test_game.getIngredientInventory("Flour")){
            if (Objects.equals(ingredient[1], "test")) {
                test_in_inventory = true;
                break;
            }
        }
        assertFalse(test_in_inventory);

        test_game.playerCollectsIngredient(new Ingredient("test", IngredientTypes.Flour, IngredientRarity.Common), 0);
        Boolean test_in_inventory2 = false;
        for (String[] ingredient: test_game.getIngredientInventory("Flour")){
            if (Objects.equals(ingredient[1], "test")) {
                test_in_inventory2 = true;
                break;
            }
        }
        assertTrue(test_in_inventory2);

        IngredientFactory test = null;
        try {
            test = new IngredientFactory();
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Water, IngredientRarity.Nuclear), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Nuclear), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Salt, IngredientRarity.Nuclear), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Yeast, IngredientRarity.Nuclear), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Water, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Salt, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Yeast, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Water, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Salt, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Yeast, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Water, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Flour, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Salt, IngredientRarity.Champion), 0);
            test_game.playerCollectsIngredient(test.makeIngredientByType(IngredientTypes.Yeast, IngredientRarity.Champion), 0);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testGetRoomExitDirections() {

    }

    @Test
    void testGetObjectsAtAllLocations() {
        CaveGame test_game = new CaveGame();
        Boolean something_exists_in_room0 = false;
        for (String object: test_game.getObjectsAtAllLocations()){
            if (Objects.equals(object, "dinosaur")) {
                something_exists_in_room0 = true;
                break;
            }
            if (Objects.equals(object, "ingredient")) {
                something_exists_in_room0 = true;
                break;
            }
        }
        assertFalse(something_exists_in_room0);
    }

    @Test
    void testClickLocation() {

        CaveGame test_game = new CaveGame();
        Boolean something_exists_in_room0 = false;
        test_game.clickLocation(0);

        for (String object: test_game.getObjectsAtAllLocations()){
            if (Objects.equals(object, "dinosaur")) {
                something_exists_in_room0 = true;
                break;
            }
            if (Objects.equals(object, "ingredient")) {
                something_exists_in_room0 = true;
                break;
            }
        }
        assertFalse(something_exists_in_room0);
    }

    @Test
    void isValidIngredientList() {
    }

    @Test
    void bakeIngredientsFromTable() {
    }

    @Test
    void removeIngredientFromInventory() {
    }

    @Test
    void testMoveRoom() {
    }

    @Test
    void testEnterRoom0() {
    }

    @Test
    void testGetObjectByLocation() {
    }

    @Test
    void getCurrPlayerPatience() {
    }

    @Test
    void getCurrPlayerHonor() {
    }

    @Test
    void getDinosaurImageByLocation() {
    }

    @Test
    void getIngredientImageByLocation() {
    }

    @Test
    void getMaxPlayerPatience() {
    }

    @Test
    void getFightersInformation() {
    }

    @Test
    void getDieInformation() {
    }

    @Test
    void fightDinosaur() {
    }

    @Test
    void stopFight() {
    }

    @Test
    void getDinoImage() {
    }

    @Test
    void getIngredientImageByString() {
    }

    @Test
    void updateActiveDice() {
    }

    @Test
    void regenerateCaveSystem() {
    }

    @Test
    void getPreviousReward() {
    }
}