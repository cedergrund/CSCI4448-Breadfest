package org.example.breadfest.dice.Epic;

import org.example.breadfest.dice.Dice;

import java.util.Random;

public class TripleNormalDie implements Dice {

    @Override
    public int rollDice() {
        Random random_seed = new Random();
        return random_seed.nextInt(6) + 13;
    }
}
