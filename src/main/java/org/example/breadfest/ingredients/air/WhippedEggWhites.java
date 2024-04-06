package org.example.breadfest.ingredients.air;

import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

public class WhippedEggWhites implements Ingredients {
    @Override
    public int getTastiness() {
        return 10;
    }

    @Override
    public IngredientTypes getType() {
        return IngredientTypes.Air;
    }
}
