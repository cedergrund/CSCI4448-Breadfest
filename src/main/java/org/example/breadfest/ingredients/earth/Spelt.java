package org.example.breadfest.ingredients.earth;

import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

public class Spelt implements Ingredients {
    @Override
    public int getTastiness() {
        return 10;
    }

    @Override
    public IngredientTypes getType() {
        return IngredientTypes.Earth;
    }
}
