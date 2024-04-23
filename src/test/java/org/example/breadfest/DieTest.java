package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.DieFactory;
import org.example.breadfest.dinosaurs.DinosaurAndDiceTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DieTest {
    @Test
    void testCreation() throws Exception {
        DieFactory factory = new DieFactory();

        Dice common_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Common);
        Dice rare_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Rare);
        Dice epic_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Epic);
        Dice nuclear_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Nuclear);

        assertNotNull(common_dice_test);
        assertNotNull(rare_dice_test);
        assertNotNull(epic_dice_test);
        assertNotNull(nuclear_dice_test);
    }

    @Test
    void testNormalDieGeneration() {
        Dice common_dice_test = DieFactory.generateBaseDie();
        assertNotNull(common_dice_test);
        assertEquals(common_dice_test.getDescription(), "Just your everyday 6-sided Die");
        assertEquals(common_dice_test.getName(), "Normal Die");
        int roll = common_dice_test.rollDice();
    }

    @Test
    void testCommonDieMethods() throws Exception {
        DieFactory factory = new DieFactory();
        Dice common_dice_test = factory.makeDieByType(DinosaurAndDiceTypes.Common);

        assertNotNull(common_dice_test);
        assertTrue("Normal Die".equals(common_dice_test.getName()) || "Bad Die".equals(common_dice_test.getName()));
        assertTrue("Just your everyday 6-sided Die".equals(common_dice_test.getDescription()) || "Do not attempt".equals(common_dice_test.getDescription()));
        assertNotNull(common_dice_test.getPDFImage());

        System.out.println(common_dice_test.getName());
        System.out.println(common_dice_test.rollDice());
    }

}
