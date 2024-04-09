package org.example.breadfest.ingredients;

import java.util.Random;

public enum IngredientRarity {
    Common(10),
    Rare(25),
    Epic(50),
    Legendary(100),
    Champion(500),
    Nuclear(-1);

    private final int ingredient_score;

    IngredientRarity(int ingredient_score) {
        this.ingredient_score = ingredient_score;
    }

    public int getIngredientScore() {
        return this.ingredient_score;
    }

    public static IngredientRarity getRandomIngredientRarity(){

        Random random_seed = new Random();
        double random_roll = random_seed.nextDouble();

        if (random_roll < 0.01){        // 1%
            return Nuclear;
        }
        else if (random_roll < 0.02){   // 1%
            return Champion;
        }
        else if (random_roll < 0.05){   // 3%
            return Legendary;
        }
        else if (random_roll < 0.2){    // 15%
            return Epic;
        }
        else if (random_roll < 0.5){    // 30%
            return Rare;
        }
        else{                           // 50%
            return Common;
        }

    }

}
