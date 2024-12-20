package org.example.breadfest;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurAndDiceTypes;
import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientFactory;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CaveGameTest {


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
        cave_game_test.wipeInventory();
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

        Player.getInstance().resetPatience();
        assertFalse(move_test);
    }

    @Test
    void enterRoom0() {
        CaveGame cave_game_test = new CaveGame();
        cave_game_test.enterRoom0();

        assertNotNull(cave_game_test);
        assertEquals(Player.getInstance().getBasePatience(), Player.getInstance().getCurrPatience());
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
        boolean test_in_inventory = false;
        for (String[] ingredient: test_game.getIngredientInventory("Flour")){
            if (Objects.equals(ingredient[1], "test")) {
                test_in_inventory = true;
                break;
            }
        }
        assertFalse(test_in_inventory);

        test_game.playerCollectsIngredient(new Ingredient("test", IngredientTypes.Flour, IngredientRarity.Common), 0);
        boolean test_in_inventory2 = false;
        for (String[] ingredient: test_game.getIngredientInventory("Flour")){
            if (Objects.equals(ingredient[1], "test")) {
                test_in_inventory2 = true;
                break;
            }
        }
        assertTrue(test_in_inventory2);

        test_game.wipeInventory();

    }

    @Test
    void testGetObjectsAtAllLocations() {
        CaveGame test_game = new CaveGame();
        boolean something_exists_in_room0 = false;
        for (String object: test_game.getObjectsAtAllLocations()){
            if (Objects.equals(object, "dinosaur")) {
                something_exists_in_room0 = true;
                break;
            }
        }
        assertFalse(something_exists_in_room0);
    }

    @Test
    void testClickLocation() {

        CaveGame test_game = new CaveGame();
        boolean something_exists_in_room0 = false;
        test_game.clickLocation(0);

        for (String object: test_game.getObjectsAtAllLocations()){
            if (Objects.equals(object, "dinosaur")) {
                something_exists_in_room0 = true;
                break;
            }
        }
        assertFalse(something_exists_in_room0);
    }

    @Test
    void isValidIngredientList() {
        CaveGame test_game = new CaveGame();
        List<String> valid_ingredients_list_test = new ArrayList<>();

        // Adding ingredients to the list
        valid_ingredients_list_test.add("Flour");
        valid_ingredients_list_test.add("Water");
        valid_ingredients_list_test.add("Salt");
        valid_ingredients_list_test.add("Yeast");

        assertTrue(test_game.isValidIngredientList(valid_ingredients_list_test));

        List<String> non_valid_ingredients_list_test = new ArrayList<>();

        non_valid_ingredients_list_test.add("Flour");
        non_valid_ingredients_list_test.add("Water");

        assertFalse(test_game.isValidIngredientList(non_valid_ingredients_list_test));
        test_game.wipeInventory();

    }

    @Test
    void bakeIngredientsFromTable() {
        CaveGame cave_game_test = new CaveGame();
        assertNotNull(cave_game_test);

        cave_game_test.playerCollectsIngredient(new Ingredient("name_test_1", IngredientTypes.Flour, IngredientRarity.Common), 0);
        cave_game_test.playerCollectsIngredient(new Ingredient("name_test_2", IngredientTypes.Water, IngredientRarity.Rare), 1);

        String[] common_flour_string_test = {"1", "name_test_1", "Flour"}; // we only need the name to test the function
        String[] rare_water_string_test = {"1", "name_test_2", "Water"};
        List<String[]> string_ingredients_test = new ArrayList<>();
        string_ingredients_test.add(common_flour_string_test);
        string_ingredients_test.add(rare_water_string_test);

        String[] baked_values_test = cave_game_test.bakeIngredientsFromTable(string_ingredients_test);

        assertEquals(baked_values_test[0], String.valueOf(0)); // assert that the function is capable of recognizing this as an invalid bake
        cave_game_test.wipeInventory();
    }

    @Test
    void removeIngredientFromInventory() {
        CaveGame cave_game_test = new CaveGame();
        assertNotNull(cave_game_test);
        cave_game_test.wipeInventory();

        cave_game_test.playerCollectsIngredient(new Ingredient("name_test_1", IngredientTypes.Flour, IngredientRarity.Common), 0);
        List<String[]> flour_inventory_test = cave_game_test.getIngredientInventory("Flour");
        assertEquals(flour_inventory_test.size(),1);
//
        cave_game_test.removeIngredientFromInventory("name_test_1");
        flour_inventory_test = cave_game_test.getIngredientInventory("Flour");
        assertEquals(flour_inventory_test.size(),0);
        cave_game_test.wipeInventory();
    }

    @Test
    void testMoveRoom(){
        CaveGame cave_game_test = new CaveGame();
        assertNotNull(cave_game_test);

        int original_patience_test = cave_game_test.getCurrPlayerPatience();

        cave_game_test.moveRoom('N');

        int moved_patience_test = cave_game_test.getCurrPlayerPatience();

        assertNotEquals(original_patience_test, moved_patience_test);
    }

    @Test
    void testEnterRoom0() {
        CaveGame cave_game_test = new CaveGame();
        assertNotNull(cave_game_test);

        int curr_patience_test = cave_game_test.getCurrPlayerPatience();

        assertNotEquals(curr_patience_test, 0);

    }

    @Test
    void testGetObjectByLocation() {
        CaveGame test_game = new CaveGame();

        String[] object = test_game.getObjectByLocation(0);
        // this should be null since we didn't put anything at location 0
        assertNull(object);
    }

    @Test
    void getCurrPlayerHonor() {
        CaveGame test_game = new CaveGame();

        int curr_honor_test = test_game.getCurrPlayerHonor();
        assertEquals(curr_honor_test, 0);
    }

    @Test
    void getMaxPlayerPatience() {
        CaveGame test_game = new CaveGame();

        int max_patience_test = test_game.getMaxPlayerPatience();

        assertNotEquals(max_patience_test, 0);
    }

    @Test
    void getFightersInformation() throws Exception {
        CaveGame test_game = new CaveGame();

        String dino_name_test = "dino_test";
        DinosaurAndDiceTypes dino_type_test = DinosaurAndDiceTypes.Common;
        Dinosaur dino_test = new Dinosaur(dino_name_test, dino_type_test);

        String[] fighter_info_test = test_game.getFightersInformation();
        assertEquals(fighter_info_test[0], "error");
        test_game.setFightingDinosaur(dino_test);
        assertEquals("file:src/main/resources/org/example/breadfest/images/dino1.100x.gif", test_game.getDinoImage());

        fighter_info_test = test_game.getFightersInformation();

        assertEquals(fighter_info_test[0], "Player");
//
        String[] fight_result_test = test_game.fightDinosaur(0);

        test_game.stopFight(false);

    }


    @Test
    void updateActiveDice() {
        CaveGame test_game = new CaveGame();
        test_game.updateActiveDice(1);
        assertNotNull(test_game);
    }

    @Test
    void regenerateCaveSystem() {
        CaveGame test_game = new CaveGame();
        test_game.regenerateCaveSystem();
        assertNotNull(test_game);
    }

}