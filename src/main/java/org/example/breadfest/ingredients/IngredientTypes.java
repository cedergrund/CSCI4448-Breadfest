package org.example.breadfest.ingredients;

import java.util.Random;

public enum IngredientTypes {
    Flour,
    Salt,
    Yeast,
    Water,
    Extra;

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
}
