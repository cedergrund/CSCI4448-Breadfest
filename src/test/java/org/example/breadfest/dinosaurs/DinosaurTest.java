package org.example.breadfest.dinosaurs;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.ingredients.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinosaurTest {
    @Test
    void changeCurrPatience() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        int curr_patience_test = dinosaur_test.getCurrPatience();
        dinosaur_test.changeCurrPatience(1);
        int after_patience_test = dinosaur_test.getCurrPatience();

        assertNotEquals(curr_patience_test, after_patience_test);
    }

    @Test
    void getDamageModifier() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        double damage_modifier_test = dinosaur_test.getDamageModifier();

        assertNotNull(damage_modifier_test);
    }

    @Test
    void rollDie() throws Exception{
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        int dice_result_test = dinosaur_test.rollDie();
        assertNotEquals(dice_result_test, 0);
    }

    @Test
    void getDinosaurType() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        assertEquals(dinosaur_test.getDinosaurType(), dinosaur_type_test);
    }

    @Test
    void getRewardDie() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        Dice reward_dice_test = dinosaur_test.getRewardDie();
//
//        assertNotNull(reward_dice_test);
    }

    @Test
    void getRewardIngredient() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        Ingredient ingredient_test = dinosaur_test.getRewardIngredient();


    }

    @Test
    void getName() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        String dinosaur_name_test = dinosaur_test.getName();

        assertEquals(dinosaur_name_test, "mr_dinosaur_test");
    }
}