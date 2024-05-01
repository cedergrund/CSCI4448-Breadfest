package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.DieFactory;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurFactory;
import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientFactory;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class APlayerTest {

    @Test
    void getInstance() {
        Player player_test_1 = Player.getInstance();
        Player player_test_2 = Player.getInstance();
        assertEquals(player_test_2, player_test_1);
    }

    @Test
    void changeCurrPatience() {
        Player player_test = Player.getInstance();
        int curr_patience_test = player_test.getCurrPatience();
        player_test.changeCurrPatience(10);
        int curr_patience_after_test = player_test.getCurrPatience();
        assertNotEquals(curr_patience_test, curr_patience_after_test);
        player_test.resetPatience();
        int curr_patience_reset_test = player_test.getCurrPatience();
        assertEquals(curr_patience_reset_test, 100);

    }


    @Test
    void rollCurrentDie() throws IOException {

        // checking that you can roll base die
        Player player_test = Player.getInstance();
        int roll_test = player_test.rollDie(0);
        assertTrue(roll_test>=1 && roll_test<=6);

    }
//
    @Test
    void getBasePatience() {

        // checking base patience equals 100
        Player player_test = Player.getInstance();
        int base_patience = player_test.getBasePatience();
        assertEquals(base_patience, 100);
    }

    @Test
    void getCurrHonor() {

        // checking base honor equals 0
        Player player_test = Player.getInstance();
        int curr_honor = player_test.getCurrHonor();
        assertEquals(curr_honor, 0);

        // updating honor and checking it changed
        player_test.changeCurrHonor(1);
        curr_honor = player_test.getCurrHonor();
        assertEquals(curr_honor, 1);

        player_test.changeCurrHonor(-1);
    }


    @Test
    void getIngredientInventory() {
        Player player_test = Player.getInstance();

        String ingredient_name_test = "ingredient_name_test";
        IngredientRarity ingredient_rarity_test = IngredientRarity.Common;
        IngredientTypes ingredient_type_test = IngredientTypes.Flour;

        Ingredient ingredient_test = new Ingredient(ingredient_name_test, ingredient_type_test, ingredient_rarity_test);
        player_test.addIngredientToInventory(ingredient_test);

        List<String[]> player_inventory_test = player_test.getIngredientInventory();

        assertNotNull(player_inventory_test);
    }

    @Test
    void changingIngredientInventory() throws Exception {

        Ingredient test = new IngredientFactory().makeIngredient();
        String name_of_ingredient = test.getName();
        int starting_count = Player.getInstance().getIngredientInventory(test.getType().toString()).size();
        Player.getInstance().addIngredientToInventory(test);
        assertEquals(Player.getInstance().getIngredientInventory(test.getType().toString()).size(), starting_count+1);

        Player.getInstance().removeIngredientFromInventory(name_of_ingredient);
        assertEquals(Player.getInstance().getIngredientInventory(test.getType().toString()).size(), starting_count);
    }

    @Test
    void changingDie() throws Exception {

        Dice test_die = DieFactory.generateBaseDie();
        String name_of_die = test_die.getName();
        assertEquals(Player.getInstance().getActiveDieInventoryInformation(1)[0], "null");

        Player.getInstance().addDieToInventory(test_die);
        assertEquals(Player.getInstance().getActiveDieInventoryInformation(1)[0], test_die.getName());
    }

    @Test
    void setFightingDinosaur() throws Exception {
        Dinosaur test_dino = new DinosaurFactory().makeADinosaurFromDepth(1);
//        assertRunt
//                (Player.getInstance().getFightersInformation());
//        Player.getInstance().setFightingDinosaur(test_dino);

    }

    @Test
    void fightDinosaur() throws Exception {
//        String dinosaur_test_name = "dinosaur_test_name";
//        DinosaurAndDiceTypes dinosaur_test_type = DinosaurAndDiceTypes.Common;
//        Dinosaur dinosaur_test = new Dinosaur(dinosaur_test_name, dinosaur_test_type);
//
//        assertNotNull(dinosaur_test);
//
//        Player player_test = Player.getInstance();
//
//
//        for (int i = 0; i < 5; i++) {
//            // Execute the line of code 5 times
//            player_test.attackDinosaur(dinosaur_test);
//        }
//
//        assertNotEquals(100, player_test.getCurrPatience());
//
//        player_test.resetPatience();

    }

}