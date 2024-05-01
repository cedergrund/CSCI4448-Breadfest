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

class FXMLCaveGameAdaptorTest {

    @Test
    void getRoomExitDirections() {

        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        assertNotNull(adaptor_test);
        List<Character> direction_list_test = adaptor_test.getRoomExitDirections();
        assertNotNull(direction_list_test);
    }

    @Test
    void getObjectsAtAllLocations() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        List<String> object_list_test = adaptor_test.getObjectsAtAllLocations();
        assertNotNull(object_list_test);
    }

    @Test
    void clickLocation() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        int location_test = 0;
        adaptor_test.clickLocation(location_test);
        assertNotNull(cave_game_test);
    }

    @Test
    void getIngredientInventory() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        List<String[]> ingredient_inventory_test = adaptor_test.getIngredientInventory();

        assertNotNull(ingredient_inventory_test);
    }

    @Test
    void moveRoom() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        char direction_test = 'N';
        boolean move_test = adaptor_test.moveRoom(direction_test);
        Player.getInstance().resetPatience();

        // check that player doesn't run out of patience
        assertFalse(move_test);
    }

    @Test
    void enterRoom0() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        adaptor_test.enterRoom0();

        // should reset patience when re-entering maze
        assertEquals(Player.getInstance().getBasePatience(), Player.getInstance().getCurrPatience());
    }

    @Test
    void getObjectByLocation() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);

        String[] object_string_test = adaptor_test.getObjectByLocation(0);
        assertNull(object_string_test);
    }

    @Test
    void testPlayerCollectsIngredient() {
        CaveGame test_game = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(test_game);
        Boolean test_in_inventory = false;
        for (String[] ingredient: adaptor_test.getIngredientInventory("Flour")){
            if (Objects.equals(ingredient[1], "test")) {
                test_in_inventory = true;
                break;
            }
        }
        assertFalse(test_in_inventory);

        test_game.playerCollectsIngredient(new Ingredient("test", IngredientTypes.Flour, IngredientRarity.Common), 0);
        Boolean test_in_inventory2 = false;
        for (String[] ingredient: adaptor_test.getIngredientInventory("Flour")){
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
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(test_game);
        boolean something_exists_in_room0 = false;
        for (String object: adaptor_test.getObjectsAtAllLocations()){
            if (Objects.equals(object, "dinosaur")) {
                something_exists_in_room0 = true;
                break;
            }
        }
        assertFalse(something_exists_in_room0);
    }

    @Test
    void bakeIngredientsFromTable() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        assertNotNull(cave_game_test);

        cave_game_test.playerCollectsIngredient(new Ingredient("name_test_1", IngredientTypes.Flour, IngredientRarity.Common), 0);
        cave_game_test.playerCollectsIngredient(new Ingredient("name_test_2", IngredientTypes.Water, IngredientRarity.Rare), 1);

        String[] common_flour_string_test = {"1", "name_test_1", "Flour"}; // we only need the name to test the function
        String[] rare_water_string_test = {"1", "name_test_2", "Water"};
        List<String[]> string_ingredients_test = new ArrayList<>();
        string_ingredients_test.add(common_flour_string_test);
        string_ingredients_test.add(rare_water_string_test);

        String[] baked_values_test = adaptor_test.bakeIngredientsFromTable(string_ingredients_test);

        assertEquals(baked_values_test[0], String.valueOf(0)); // assert that the function is capable of recognizing this as an invalid bake
        cave_game_test.wipeInventory();
    }

    @Test
    void removeIngredientFromInventory() {
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        assertNotNull(cave_game_test);
        cave_game_test.wipeInventory();

        cave_game_test.playerCollectsIngredient(new Ingredient("name_test_1", IngredientTypes.Flour, IngredientRarity.Common), 0);
        List<String[]> flour_inventory_test = adaptor_test.getIngredientInventory("Flour");
        assertEquals(flour_inventory_test.size(),1);
//
        cave_game_test.removeIngredientFromInventory("name_test_1");
        flour_inventory_test = adaptor_test.getIngredientInventory("Flour");
        assertEquals(flour_inventory_test.size(),0);
        cave_game_test.wipeInventory();
    }

    @Test
    void testMoveRoom(){
        CaveGame cave_game_test = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(cave_game_test);
        assertNotNull(cave_game_test);

        int original_patience_test = adaptor_test.getCurrPlayerPatience();

        adaptor_test.moveRoom('N');

        int moved_patience_test = adaptor_test.getCurrPlayerPatience();

        assertNotEquals(original_patience_test, moved_patience_test);
    }


    @Test
    void testGetObjectByLocation() {
        CaveGame test_game = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(test_game);

        String[] object = adaptor_test.getObjectByLocation(0);
        // this should be null since we didn't put anything at location 0
        assertNull(object);
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
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(test_game);

        String dino_name_test = "dino_test";
        DinosaurAndDiceTypes dino_type_test = DinosaurAndDiceTypes.Common;
        Dinosaur dino_test = new Dinosaur(dino_name_test, dino_type_test);

        String[] fighter_info_test = adaptor_test.getFightersInformation();
        assertEquals(fighter_info_test[0], "error");
        test_game.setFightingDinosaur(dino_test);
        assertEquals("file:src/main/resources/org/example/breadfest/images/dino1.100x.gif", adaptor_test.getDinoImage());

        fighter_info_test = adaptor_test.getFightersInformation();

        assertEquals(fighter_info_test[0], "Player");
//
        String[] fight_result_test = adaptor_test.fightDinosaur(0);
//
        adaptor_test.stopFight(false);

    }


    @Test
    void updateActiveDice() {
        CaveGame test_game = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(test_game);
        adaptor_test.updateActiveDice(1);
        assertNotNull(test_game);
    }

    @Test
    void regenerateCaveSystem() {
        CaveGame test_game = new CaveGame();
        FXMLCave adaptor_test = new FXMLCaveGameAdaptor(test_game);
        adaptor_test.regenerateCaveSystem();
        assertNotNull(test_game);
    }
}