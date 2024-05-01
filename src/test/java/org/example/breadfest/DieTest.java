package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.DieFactory;
import org.example.breadfest.dinosaurs.DinosaurAndDiceTypes;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DieTest {
    @Test
    void testCreation() throws Exception {
        DieFactory factory = new DieFactory();

        Dice common_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Common.toString(), "dino");
        Dice rare_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Rare.toString(), "dino");
        Dice epic_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Epic.toString(), "dino");
        Dice nuclear_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Nuclear.toString(), "dino");

        assertNotNull(common_dice_test);
        assertNotNull(rare_dice_test);
        assertNotNull(epic_dice_test);
        assertNotNull(nuclear_dice_test);
    }

    @Test
    void testNormalDieGeneration() {
        Dice common_dice_test = DieFactory.generateBaseDie();
        assertNotNull(common_dice_test);
        assertEquals(common_dice_test.getDescription(), "Just your everyday 6-sided Die. Nothing to it really. Kinda boring to be honest...");
        assertEquals(common_dice_test.getName(), "Normal Die");
        assertEquals(common_dice_test.getRarity(), "Common");
        assertEquals(common_dice_test.getPDFImage(), "file:src/main/resources/org/example/breadfest/images/dice_pdf/normal.png");
        int roll = common_dice_test.rollDice();
        assertTrue(roll <= 6 && roll >=1);
    }

    @Test
    void makeDieByType() throws Exception {
        DieFactory die_factory = new DieFactory();
        Dice test_die = die_factory.makeDieByType("Common", "dino");

        assertNotNull(test_die);
        assertNotNull(test_die.getName());
        assertNotNull(test_die.getDescription());
        assertEquals(test_die.getRarity(), "Common");
        assertNotNull(test_die.getPDFImage());
        int roll = test_die.rollDice();
        assertTrue(roll <= 7 && roll >=1);

        Dice test_die2 = die_factory.makeDieByType("Rare", "dino");
        assertNotNull(test_die2);
        int roll2 = test_die2.rollDice();
        assertTrue(roll2 <= 20 && roll2 >=-10);
    }

    @Test
    void testMagicDie() throws Exception {
        DieFactory die_factory = new DieFactory();
        Dice test_die = die_factory.makeDieByType("EndGame", "reward");

        assertNotNull(test_die);
        assertEquals(test_die.getRarity(), "EndGame");
        assertTrue((Objects.equals(test_die.getName(), "Mirror Cube Deluxe")) || (Objects.equals(test_die.getName(), "Insta-win")) );
        assertTrue((Objects.equals(test_die.getPDFImage(), "file:src/main/resources/org/example/breadfest/images/dice_pdf/mirror.png")) || (Objects.equals(test_die.getPDFImage(), "file:src/main/resources/org/example/breadfest/images/dice_pdf/win.png")) );
        assertTrue((Objects.equals(test_die.getDescription(), "Doubles the result of the your last roll. Nothing could go wrong here...")) || (Objects.equals(test_die.getDescription(), "Takes your opponents roll and adds 1. Fairly simply, but a little unfair.")) );
    }

}
