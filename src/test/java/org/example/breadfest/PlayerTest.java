package org.example.breadfest;

import org.example.breadfest.ingredients.Ingredient;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

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
    void upgradeBasePatience() {
        int upgrade_patience_test = 1;
        Player player_test = Player.getInstance();
        player_test.resetPatience();

        player_test.upgradeBasePatience(upgrade_patience_test);

        assertEquals(player_test.getCurrPatience(), 100);


    }
//
    @Test
    void rollCurrentDie() {
//        Dice epic_dice_test = new TripleNormalDie();
//        Dice rare_dice_test_1 = new DoubleNormalDie();
//        Dice rare_dice_test_2 = new TwoDice();
//        Dice nuclear_dice_test = new AtomicDie();
//
//        assertNotNull(epic_dice_test);
//        assertNotNull(rare_dice_test_1);
//        assertNotNull(rare_dice_test_2);
//        assertNotNull(nuclear_dice_test);
//        Player player_test = Player.getInstance();
//        assertNotEquals(player_test.getActiveDieInventory(), epic_dice_test);
//        assertNotEquals(player_test.getActiveDieInventory(), rare_dice_test_1);
//        assertNotEquals(player_test.getActiveDieInventory(), rare_dice_test_2);
//        assertNotEquals(player_test.getActiveDieInventory(), nuclear_dice_test);
//
//        int roll_test = player_test.rollCurrentDie();
//        assertNotNull(roll_test);

    }
//

    @Test
    void addToDamageModifier() {
        int percent_change_test = 1;
        Player player_test = Player.getInstance();
        player_test.addToDamageModifier(percent_change_test);

        assertNotNull(player_test);

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

}