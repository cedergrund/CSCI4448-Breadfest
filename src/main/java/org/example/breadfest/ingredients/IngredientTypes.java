package org.example.breadfest.ingredients;

import java.util.Random;

public enum IngredientTypes {
    Flour(5),
    Salt(1),
    Yeast(3),
    Water(4),
    Extra(1);

    private final double ingredient_score_multiplier;

    IngredientTypes(double ingredient_score_multiplier) {
        this.ingredient_score_multiplier = ingredient_score_multiplier;
    }

    public static IngredientTypes getRandomIngredientType(){

        Random random_seed = new Random();

        return switch (random_seed.nextInt(5)) {
            case 0 -> Flour;
            case 1 -> Salt;
            case 2 -> Yeast;
            case 3 -> Water;
            case 4 -> Extra;
            default -> null;
        };

    }

    public double getIngredientMultiplier() {
        return this.ingredient_score_multiplier;
    }
}
