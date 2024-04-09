package org.example.breadfest.dice;

import java.util.Random;

public class DoubleNormalDie implements Dice {

    @Override
    public int rollDice() {
        Random random_seed = new Random();
        return random_seed.nextInt(6) + 7;
    }
}
