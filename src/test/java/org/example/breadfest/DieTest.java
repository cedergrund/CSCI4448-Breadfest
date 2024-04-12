package org.example.breadfest;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.Epic.TripleNormalDie;
import org.example.breadfest.dice.Nuclear.AtomicDie;
import org.example.breadfest.dice.Rare.DoubleNormalDie;
import org.example.breadfest.dice.Rare.TwoDice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DieTest {
    @Test
    void checkRolls() {
        Dice epic_dice_test = new TripleNormalDie();
        Dice rare_dice_test_1 = new DoubleNormalDie();
        Dice rare_dice_test_2 = new TwoDice();
        Dice nuclear_dice_test = new AtomicDie();

        assertNotNull(epic_dice_test);
        assertNotNull(rare_dice_test_1);
        assertNotNull(rare_dice_test_2);
        assertNotNull(nuclear_dice_test);
    }
    @Test
    void assertNonZeroRare() {
        Dice rare_dice_test_1 = new DoubleNormalDie();
        Dice rare_dice_test_2 = new TwoDice();

        int roll_test_1 = rare_dice_test_1.rollDice();
        assertNotEquals(roll_test_1, 0);

        int roll_test_2 = rare_dice_test_2.rollDice();
        assertNotEquals(roll_test_2, 0);
    }
    @Test
    void assertNonZeroEpic() {
        Dice epic_dice_test = new TripleNormalDie();
        int roll_test = epic_dice_test.rollDice();
        assertNotEquals(roll_test, 0);
    }
    @Test
    void assertNonZeroNuclear() {
        Dice nuclear_dice_test = new AtomicDie();
        int roll_test = nuclear_dice_test.rollDice();
        assertNotEquals(roll_test, 0);
    }
}
