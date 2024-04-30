package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurAndDiceTypes;
import org.example.breadfest.dinosaurs.DinosaurFactory;
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

        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",DinosaurAndDiceTypes.Rare);
        double damage_modifier_test = dinosaur_test.getDamageModifier();
        assertEquals(damage_modifier_test, 2.0);

        Dinosaur dinosaur_test2 = new Dinosaur("mr_dinosaur_test",DinosaurAndDiceTypes.Common);
        double damage_modifier_test2 = dinosaur_test2.getDamageModifier();
        assertEquals(damage_modifier_test2, 1.0);
    }

    @Test
    void rollDie() throws Exception{
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Common;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        int dice_result_test = dinosaur_test.rollDie();
        assertTrue(dice_result_test <= 7 && dice_result_test >= 1);
    }

    @Test
    void getDinosaurType() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        assertEquals(dinosaur_test.getDinosaurType(), dinosaur_type_test);
    }

    @Test
    void getRewardDie() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Nuclear;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        Dice reward_dice_test = dinosaur_test.getRewardDie();
        assertNotNull(reward_dice_test);
    }

    @Test
    void getRewardIngredient() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        Ingredient ingredient_test = dinosaur_test.getRewardIngredient();
        assertNotNull(ingredient_test);

    }

    @Test
    void getName() throws Exception {
        DinosaurAndDiceTypes dinosaur_type_test = DinosaurAndDiceTypes.Rare;
        Dinosaur dinosaur_test = new Dinosaur("mr_dinosaur_test",dinosaur_type_test);

        String dinosaur_name_test = dinosaur_test.getName();

        assertEquals(dinosaur_name_test, "Mommy mr_dinosaur_test");
    }

    @Test
    void makeDinosaurFromDepth() throws Exception {

        DinosaurFactory dino_factory = new DinosaurFactory();
        Dinosaur dinosaur_test = dino_factory.makeADinosaurFromDepth(1);
        assertEquals(dinosaur_test.getDinosaurType(), DinosaurAndDiceTypes.Common);

        Dinosaur dinosaur_test2 = dino_factory.makeADinosaurFromDepth(4);
        assertEquals(dinosaur_test2.getDinosaurType(), DinosaurAndDiceTypes.Rare);

        Dinosaur dinosaur_test3 = dino_factory.makeADinosaurFromDepth(8);
        assertEquals(dinosaur_test3.getDinosaurType(), DinosaurAndDiceTypes.Epic);

        Dinosaur dinosaur_test4 = dino_factory.makeADinosaurFromDepth(10);
        assertEquals(dinosaur_test4.getDinosaurType(), DinosaurAndDiceTypes.Nuclear);
    }
}