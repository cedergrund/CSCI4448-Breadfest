package org.example.breadfest.dice.Rare;

import org.example.breadfest.dice.Dice;

import java.util.Random;

public class TwoDice implements Dice {
    @Override
    public int rollDice() {
        Random random_seed = new Random();
        int dice_roll1 = random_seed.nextInt(6) + 1;
        int dice_roll2 = random_seed.nextInt(6) + 1;
        return dice_roll1 + dice_roll2;
    }
}
