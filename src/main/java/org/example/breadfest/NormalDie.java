package org.example.breadfest;

import java.util.Random;

public class NormalDie implements Dice{

    @Override
    public int rollDice() {
        Random random_seed = new Random();
        return random_seed.nextInt(6) + 1;
    }
}
