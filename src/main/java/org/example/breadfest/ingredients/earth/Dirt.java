package org.example.breadfest.ingredients.earth;

import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

public class Dirt implements Ingredients {
    @Override
    public int getTastiness() {
        return 0;
    }

    @Override
    public IngredientTypes getType() {
        return IngredientTypes.Earth;
    }
}
