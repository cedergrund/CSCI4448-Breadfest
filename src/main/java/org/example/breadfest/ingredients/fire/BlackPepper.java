package org.example.breadfest.ingredients.fire;

import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

public class BlackPepper implements Ingredients {
    @Override
    public int getTastiness() {
        return 10;
    }

    @Override
    public IngredientTypes getType() {
        return IngredientTypes.Fire;
    }
}
